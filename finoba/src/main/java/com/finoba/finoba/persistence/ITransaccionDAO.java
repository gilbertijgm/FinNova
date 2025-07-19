package com.finoba.finoba.persistence;

import com.finoba.finoba.entities.Transaccion;

import java.util.List;
import java.util.Optional;

public interface ITransaccionDAO {

    Transaccion crearTransaccion(Transaccion transaccion);

    Optional<Transaccion> obtenerTransaccion(String referenciaExterna);

    List<Transaccion> obtenerTransPorUsuario(Long idUsuario);
}
