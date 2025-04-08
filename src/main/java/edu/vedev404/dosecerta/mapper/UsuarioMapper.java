package edu.vedev404.dosecerta.mapper;

import edu.vedev404.dosecerta.dto.UsuarioDTO;
import edu.vedev404.dosecerta.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.id());
        usuario.setNome((usuarioDTO.nome()));
        usuario.setTelefone(usuarioDTO.telefone());
        usuario.setMessageKey(usuarioDTO.messageKey());

        return usuario;
    }

    public UsuarioDTO  usuarioToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getMessageKey()
                );

    }
}
