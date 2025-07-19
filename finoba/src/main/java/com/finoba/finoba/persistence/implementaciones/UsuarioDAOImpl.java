package com.finoba.finoba.persistence.implementaciones;

import com.finoba.finoba.entities.Usuario;
import com.finoba.finoba.persistence.IUsuarioDAO;
import com.finoba.finoba.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioDAOImpl implements IUsuarioDAO {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
       Usuario nuevoUsuario = usuarioRepository.save(usuario);

        return nuevoUsuario;
    }

    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> lista = usuarioRepository.findAll();

        return lista;
    }

    @Override
    public Optional<Usuario> usuario(Long id) {

        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> usuarioByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        Usuario usuarioModificado = usuarioRepository.save(usuario);

        return usuarioModificado;
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
