package edu.vedev404.dosecerta.service;

import edu.vedev404.dosecerta.dto.UsuarioDTO;
import edu.vedev404.dosecerta.mapper.UsuarioMapper;
import edu.vedev404.dosecerta.models.Usuario;
import edu.vedev404.dosecerta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    public UsuarioDTO create(UsuarioDTO usuario) {
        return mapper.usuarioToDTO(repository.save(mapper.toEntity((usuario))));
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id ) {
        return repository.findById(id);
    }

    public void removerUsuario(Long id) {
        repository.deleteById(id);
    }
}
