package edu.vedev404.dosecerta.mapper;

import edu.vedev404.dosecerta.dto.MedicamentoDTO;
import edu.vedev404.dosecerta.models.Medicamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicamentoMapper {

    public MedicamentoDTO toDto(Medicamento entity) {
        if (entity == null) {
            return null;
        }
        return new MedicamentoDTO(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getNomeMedicamento(),
                entity.getDosagem(),
                entity.getHora(),
                entity.getInicio(),
                entity.getFim(),
                entity.getMessageAlt()
        );
    }

    public Medicamento toEntity(MedicamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        Medicamento entity = new Medicamento();
        entity.setId(dto.id());
        entity.setUsuarioId(dto.usuarioId());
        entity.setNomeMedicamento(dto.nomeMedicamento());
        entity.setDosagem(dto.dosagem());
        entity.setHora(dto.hora());
        entity.setInicio(dto.inicio());
        entity.setFim(dto.fim());
        entity.setMessageAlt(dto.messageAlt());
        return entity;
    }

    public List<MedicamentoDTO> toDtoList(List<Medicamento> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
