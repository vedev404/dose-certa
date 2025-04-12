package edu.vedev404.dosecerta.repository;

import edu.vedev404.dosecerta.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
