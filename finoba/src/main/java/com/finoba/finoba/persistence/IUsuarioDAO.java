package com.finoba.finoba.persistence;

import com.finoba.finoba.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {

    Usuario crearUsuario(Usuario usuario);

    List<Usuario> listaUsuarios();

    Optional<Usuario> usuario(Long id);

    Optional<Usuario> usuarioByDni(String dni);

    Usuario modificarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);
}
