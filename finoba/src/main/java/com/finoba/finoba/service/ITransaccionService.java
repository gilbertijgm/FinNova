package com.finoba.finoba.service;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITransaccionService {

    TransaccionResponseDTO crearTransaccion(Long usuarioId, TransaccionRequestDTO transaccionDTO);

    TransaccionResponseDTO obtenerTransaccion(String referenciaExterna);

    Page<TransRespUserDTO> obtenerTransPorUsuario(Long idUsuario, Pageable pageable);

    Page<TransaccionResponseDTO> obtenerTransPorUsuarioFiltro(LocalDate fechaDesde,
                                                        LocalDate fechaHasta,
                                                        String categoria,
                                                        String tipo,
                                                        String estado,
                                                        Long idUsuario,
                                                        Pageable pageable);
}
