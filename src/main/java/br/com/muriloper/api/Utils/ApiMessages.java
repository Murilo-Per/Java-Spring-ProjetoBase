package br.com.muriloper.api.Utils;

import lombok.Getter;

@Getter
public enum ApiMessages {
    OBJECT_NOT_FOUND("Objeto Não Encontrado!"),
    EMAIL_EXISTS("Email já cadastrado!");

    private String mensagem;

    ApiMessages(String mensagem) {
        this.mensagem = mensagem;
    }


}
