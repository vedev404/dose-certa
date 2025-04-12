package edu.vedev404.dosecerta.service;

import edu.vedev404.dosecerta.dto.MedicamentoDTO;
import edu.vedev404.dosecerta.mapper.MedicamentoMapper;
import edu.vedev404.dosecerta.models.Medicamento;
import edu.vedev404.dosecerta.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    @Autowired
    private MedicamentoMapper mapper;

    public List<MedicamentoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Medicamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<List<Medicamento>> listarMedicamento() {
        return Optional.of(repository.findAll());
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Horário de medicamento não encontrado");
        }
        repository.deleteById(id);
    }

    public MedicamentoDTO create(MedicamentoDTO medicamento) {
        Medicamento entity = mapper.toEntity(medicamento);
        return mapper.toDto(repository.save(mapper.toEntity(medicamento)));
    }

}
