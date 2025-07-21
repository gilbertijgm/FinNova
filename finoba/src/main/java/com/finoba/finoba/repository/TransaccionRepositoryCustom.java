package com.finoba.finoba.repository;

import com.finoba.finoba.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransaccionRepositoryCustom {

    Page<Transaccion> buscarTransConFiltros(
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            String categoria,
            String tipo,
            String estado,
            Long usuarioId,
            Pageable pageable
    );
}
