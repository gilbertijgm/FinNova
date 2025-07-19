package com.finoba.finoba.service;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.entities.Transaccion;

import java.util.List;
import java.util.Optional;

public interface ITransaccionService {

    TransaccionResponseDTO crearTransaccion(Long usuarioId, TransaccionRequestDTO transaccionDTO);

    Optional<TransaccionResponseDTO> obtenerTransaccion(String referenciaExterna);

    TransPorUsuarioDTO obtenerTransPorUsuario(Long idUsuario);
}
