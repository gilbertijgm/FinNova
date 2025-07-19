package com.finoba.finoba.service;

import com.finoba.finoba.dtos.usuario.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuario);

    List<UsuarioDTO> listaUsuarios();

    UsuarioDTO usuario(Long id);

    UsuarioDTO usuarioByDni(String dni);

    UsuarioDTO modificarUsuario(Long id, UsuarioDTO usuario);

    void eliminarUsuario(Long id);
}
