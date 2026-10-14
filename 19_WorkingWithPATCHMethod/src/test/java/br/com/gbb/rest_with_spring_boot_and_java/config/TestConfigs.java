package br.com.gbb.rest_with_spring_boot_and_java.config;

import groovy.lang.GString;

public interface TestConfigs {
    int SERVER_PORT = 8888;

    //VARIAVEIS PARA FACILITAR O USO DAS CONSTANTES NO TESTE DE CORS
    String HEADER_PARAM_AUTHORIZATION = "Authorization";
    String HEADER_PARAM_ORIGIN = "Origin";
    String ORIGIN_LOCALHOST_TRUE = "http://localhost:8080";
    String ORIGIN_LOCALHOST_FALSE = "http://localhost:8887";

}
