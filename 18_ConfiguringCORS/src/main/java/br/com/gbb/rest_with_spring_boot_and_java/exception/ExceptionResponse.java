package br.com.gbb.rest_with_spring_boot_and_java.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String details) implements Serializable {}
