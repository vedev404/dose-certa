package edu.vedev404.dosecerta.controller;

import edu.vedev404.dosecerta.dto.MedicamentoDTO;
import edu.vedev404.dosecerta.models.Medicamento;
import edu.vedev404.dosecerta.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @PostMapping
    public ResponseEntity<MedicamentoDTO> createMedicamento(@RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO createdMedicamento = service.create(medicamentoDTO);
        return new ResponseEntity<>(createdMedicamento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> getAllMedicamento() {
        List<MedicamentoDTO> medicamento = service.listarTodos();
        return new ResponseEntity<>(medicamento, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Medicamento buscarMedicamentoPorId (@PathVariable Long id) {
        Optional<Medicamento> hora = service.buscarPorId(id);
        return (Medicamento) ((Optional<?>) hora).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
