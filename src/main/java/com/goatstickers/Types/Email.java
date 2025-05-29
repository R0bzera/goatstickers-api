package com.goatstickers.Types;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Email {

    @NotBlank(message = "O e-mail não pode estar vazio ou com espaços")
    @jakarta.validation.constraints.Email (message = "Formato do e-mail inválido")
    private String value;

    protected Email(){}

    public Email(String value){
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("O e-mail não pode ser vazio");
        }
        if (!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalArgumentException("Formato do e-mail inválido");
        }
        this.value = value.toLowerCase();
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}