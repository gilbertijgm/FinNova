package com.finoba.finoba.controller;

import com.finoba.finoba.dtos.usuario.UsuarioDTO;
import com.finoba.finoba.response.ApiResponse;
import com.finoba.finoba.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        UsuarioDTO usuario = usuarioService.crearUsuario(usuarioDTO);

        ApiResponse<UsuarioDTO> response = new ApiResponse<>(201, "Usuario creado con exito", usuario);

        return ResponseEntity.created(new URI("/api/usuario/crear" + usuarioDTO.getIdUsuario())).body(response);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> listaUsuarios(){
        List<UsuarioDTO> lista = usuarioService.listaUsuarios();

        ApiResponse<List<UsuarioDTO>> response = new ApiResponse<>(200, "Listado de usuarios", lista);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> usuarioById(@PathVariable Long id){
       UsuarioDTO usuarioDTO = usuarioService.usuario(id);

        ApiResponse<UsuarioDTO> response = new ApiResponse<>(200, "Usuario Encontrado", usuarioDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarioPorDni/{dni}")
    public ResponseEntity<?> usuarioByDni(@PathVariable String dni){
        UsuarioDTO usuarioDTO = usuarioService.usuarioByDni(dni);

        ApiResponse<UsuarioDTO> response = new ApiResponse<>(200, "Usuario encontrado", usuarioDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        UsuarioDTO usuario = usuarioService.modificarUsuario(id, usuarioDTO);

        ApiResponse<UsuarioDTO> response = new ApiResponse<>(201, "Usuario creado con exito", usuario);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id)  {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con exito");
    }
}
