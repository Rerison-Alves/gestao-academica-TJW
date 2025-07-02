package br.edu.ifce.gestao_academica.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}