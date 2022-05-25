package xyz.sandersonsa.amqbrokerspringbootproducerjson.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    private String nome;
    private String sobrenome;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dtNascimento;
    
    private Endereco endereco;

    @Override
    public String toString(){
        return String.format("Pessoa -> Nome :: %s, Logradouro :: %s", nome, endereco.getLogradouro());
    }
}
