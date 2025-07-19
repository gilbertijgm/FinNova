package com.finoba.finoba.persistence.implementaciones;

import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.persistence.ITransaccionDAO;
import com.finoba.finoba.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public List<Transaccion> obtenerTransPorUsuario(Long usuarioId) {
        List<Transaccion> transaccionesPorUsuario = transaccionRepository.obtenerTransPorUsuario(usuarioId);

        return transaccionesPorUsuario;
    }
}
