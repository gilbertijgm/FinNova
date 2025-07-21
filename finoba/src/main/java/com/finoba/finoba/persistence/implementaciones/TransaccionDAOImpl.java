package com.finoba.finoba.persistence.implementaciones;

import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.persistence.ITransaccionDAO;
import com.finoba.finoba.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransaccionDAOImpl implements ITransaccionDAO {

    private final TransaccionRepository transaccionRepository;

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        Transaccion trans = transaccionRepository.save(transaccion);

        return trans;
    }

    @Override
    public Optional<Transaccion> obtenerTransaccion(String referenciaExterna) {
        Optional<Transaccion> transaccion = transaccionRepository.findByReferenciaExterna(referenciaExterna);

        return transaccion;
    }

    @Override
    public Page<Transaccion> obtenerTransPorUsuario(Long usuarioId, Pageable pageable) {
        Page<Transaccion> transaccionesPorUsuario = transaccionRepository.obtenerTransPorUsuario(usuarioId, pageable);

        return transaccionesPorUsuario;
    }

    @Override
    public Page<Transaccion> obtenerTransPorUsuarioFiltros(LocalDate fechaDesde, LocalDate fechaHasta, String categoria, String tipo, String estado, Long idUsuario, Pageable pageable) {
        Page<Transaccion> transaccionesPorUsuario = transaccionRepository.buscarTransConFiltros(fechaDesde,fechaHasta,categoria,tipo,estado,idUsuario, pageable);

        return transaccionesPorUsuario;
    }
}
