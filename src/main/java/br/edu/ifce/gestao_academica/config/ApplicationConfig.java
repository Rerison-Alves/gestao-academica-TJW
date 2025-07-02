package br.edu.ifce.gestao_academica.config;

import br.edu.ifce.gestao_academica.auditing.ApplicationAuditAware;
import br.edu.ifce.gestao_academica.user.UserRepository;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository repository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuditorAware<Integer> auditorAware() {
    return new ApplicationAuditAware();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public OpenApiCustomizer globalResponseCustomiser() {
    return openApi -> {
      openApi.getPaths().forEach((path, pathItem) -> {
        for (PathItem.HttpMethod method : pathItem.readOperationsMap().keySet()) {
          Operation operation = pathItem.readOperationsMap().get(method);
          ApiResponses responses = operation.getResponses();

          responses.addApiResponse("401", new ApiResponse().description("Not authenticated agent (missing or invalid credentials)"));
          responses.addApiResponse("403", new ApiResponse().description("Ops! You do not have permission to access this feature! :("));
          responses.addApiResponse("404", new ApiResponse().description("Resource not found"));
          responses.addApiResponse("500", new ApiResponse()
                  .description("Internal server error")
                  .content(new Content().addMediaType("application/json",
                          new MediaType().schema(new ObjectSchema()
                                  .addProperties("timestamp", new StringSchema().description("Momento do erro"))
                                  .addProperties("message", new StringSchema().description("Mensagem de erro"))
                                  .addProperties("details", new StringSchema().description("Caminho da requisição"))
                          )
                  ))
          );
        }
      });
    };
  }
}