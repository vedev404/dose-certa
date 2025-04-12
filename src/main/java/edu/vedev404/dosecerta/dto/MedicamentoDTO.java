package edu.vedev404.dosecerta.dto;

import java.time.LocalDate;

public record MedicamentoDTO(
        Long id,
        Long usuarioId,
        String nomeMedicamento,
        String dosagem,
        String hora,
        LocalDate inicio,
        LocalDate fim,
        String messageAlt
) {
}
