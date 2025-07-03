package br.edu.ifce.gestao_academica.auth;

import br.edu.ifce.gestao_academica.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String nome;

  private String email;

  private String password;
}