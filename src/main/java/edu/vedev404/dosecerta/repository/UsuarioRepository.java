package edu.vedev404.dosecerta.repository;

import edu.vedev404.dosecerta.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
