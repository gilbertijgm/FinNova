package com.finoba.finoba.controller;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.response.ApiResponse;
import com.finoba.finoba.service.ITransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaccion")
public class TransaccionController {

    private final ITransaccionService transaccionService;

    @PostMapping("/crearTrans/{usuarioId}")
    public ResponseEntity<?> crearTransaccion(@PathVariable Long usuarioId ,@RequestBody TransaccionRequestDTO transaccionDTO) throws URISyntaxException {

        TransaccionResponseDTO transaccion = transaccionService.crearTransaccion(usuarioId, transaccionDTO);

        ApiResponse<TransaccionResponseDTO> response = new ApiResponse<>(201, "Transaccion creada con Exito", transaccion);

        return ResponseEntity.created(new URI("/api/transaccion/")).body(response);
    }

    @GetMapping("/usuario/{usuarioId}/transacciones")
    public ResponseEntity<?> transPorUsuario(@PathVariable Long usuarioId

    ){

        TransPorUsuarioDTO transPorUsuarioDTO = transaccionService.obtenerTransPorUsuario(usuarioId);

        ApiResponse<TransPorUsuarioDTO> response = new ApiResponse<>(200, "Listado de transacciones por usuario", transPorUsuarioDTO);

        return ResponseEntity.ok(response);
    }

}
