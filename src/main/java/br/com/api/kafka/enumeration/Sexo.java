package br.com.api.kafka.enumeration;

import lombok.Getter;

public enum Sexo {
    M("Masculino"),
    F("Feminino");

    @Getter
    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }
}