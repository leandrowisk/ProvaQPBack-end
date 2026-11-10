package com.qpLeandro.QPProva.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;


@Entity
@Data
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Campo documento é obrigatório")
    private String documento;

    @Column(nullable = false)
    @NotBlank(message = "Campo Nome é obrigatório")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Campo Sobrenome é obrigatório")
    private String sobrenome;

    @Column(nullable = false, unique = true)
    @Email(message = "E-mail com formato inválido. Ajuste-o e tente novamente.")
    @NotBlank(message = "Campo E-mail é obrigatório")
    private String email;
}

