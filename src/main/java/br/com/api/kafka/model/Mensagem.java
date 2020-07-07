package br.com.api.kafka.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.kafka.enumeration.Sexo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mensagem {

    private Integer id;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="America/Sao_Paulo")
    private Date dataNascimento;
    
    private Sexo sexo;
    
}