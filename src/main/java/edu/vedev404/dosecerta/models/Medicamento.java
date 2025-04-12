package edu.vedev404.dosecerta.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamentos")
@Getter
@Setter
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId; // Apenas um Long, sem relacionamento com Usuario

    @NotBlank(message = "O nome do medicamento é obrigatório.")
    private String nomeMedicamento;

    @NotBlank(message = "A dosagem é obrigatória.")
    private String dosagem;

    @NotBlank(message = "A hora do medicamento é obrigatória.")
    private String hora;

    private LocalDate inicio;
    private LocalDate fim;

    private String messageAlt;
}
