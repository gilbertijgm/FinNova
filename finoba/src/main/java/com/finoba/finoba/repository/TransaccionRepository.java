package com.finoba.finoba.repository;

import com.finoba.finoba.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>, TransaccionRepositoryCustom {


    Optional<Transaccion> findByReferenciaExterna(String referenciaExterna);

    //busca las transacciones de un usuario por id
    //List<Transaccion> findByUsuarioId(Long usuarioId);

    @Query("SELECT t FROM Transaccion t WHERE t.usuario.id = :usuarioId")
    Page<Transaccion> obtenerTransPorUsuario(Long usuarioId, Pageable pageable);


}
