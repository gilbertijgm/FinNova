package com.finoba.finoba.service.implementaciones;

import com.finoba.finoba.dtos.usuario.UsuarioDTO;
import com.finoba.finoba.entities.Usuario;
import com.finoba.finoba.exceptions.ResourceNotFoundException;
import com.finoba.finoba.mappers.UsuarioMapper;
import com.finoba.finoba.persistence.IUsuarioDAO;
import com.finoba.finoba.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuario) {

        Usuario user = usuarioMapper.toEntity(usuario);

        user = usuarioDAO.crearUsuario(user);

        return usuarioMapper.toDto(user);
    }

    @Override
    public List<UsuarioDTO> listaUsuarios() {

        List<Usuario> lista = usuarioDAO.listaUsuarios();

        return usuarioMapper.toDtoList(lista);
    }

    @Override
    public UsuarioDTO usuario(Long id) {
        Usuario usuario = usuarioDAO.usuario(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " +id));

        UsuarioDTO usu = usuarioMapper.toDto(usuario);

        return usu;
    }

    @Override
    public UsuarioDTO usuarioByDni(String dni) {
        Usuario usuario = usuarioDAO.usuarioByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el DNI: " +dni));

        UsuarioDTO usu = usuarioMapper.toDto(usuario);

        return usu;
    }

    @Override
    public UsuarioDTO modificarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioDAO.usuario(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " +id));

        usuarioMapper.updateEntityFromDto(usuarioDTO, usuario);

        Usuario usuarioModificado = usuarioDAO.modificarUsuario(usuario);

        return usuarioMapper.toDto(usuarioModificado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioDAO.eliminarUsuario(id);
    }
}
