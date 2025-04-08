package edu.vedev404.dosecerta.controller;

import edu.vedev404.dosecerta.dto.UsuarioDTO;
import edu.vedev404.dosecerta.models.Usuario;
import edu.vedev404.dosecerta.repository.UsuarioRepository;
import edu.vedev404.dosecerta.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    UsuarioService service;

    @PostMapping
    @ResponseStatus( code = HttpStatus.CREATED)
    public UsuarioDTO create(@RequestBody UsuarioDTO usuario) {
        return service.create((usuario));
    }

    @GetMapping
    public List<Usuario> listarUsuario() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId (@PathVariable Long id) {
        Optional<Usuario> usuario = service.buscarUsuarioPorId(id);
        return usuario.orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removerUsuaruio(@PathVariable Long id) {
        service.removerUsuario(id);
    }
}
