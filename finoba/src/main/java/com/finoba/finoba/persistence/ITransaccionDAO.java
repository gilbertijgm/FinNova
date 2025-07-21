package com.finoba.finoba.persistence;

import com.finoba.finoba.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITransaccionDAO {

    Transaccion crearTransaccion(Transaccion transaccion);

    Optional<Transaccion> obtenerTransaccion(String referenciaExterna);

    Page<Transaccion> obtenerTransPorUsuario(Long idUsuario, Pageable pageable);

    Page<Transaccion> obtenerTransPorUsuarioFiltros(LocalDate fechaDesde,
                                                    LocalDate fechaHasta,
                                                    String categoria,
                                                    String tipo,
                                                    String estado,
                                                    Long idUsuario,
                                                    Pageable pageable);
}
