package com.finoba.finoba.entities;

import com.finoba.finoba.entities.enums.Categoria;
import com.finoba.finoba.entities.enums.EstadoTransaccion;
import com.finoba.finoba.entities.enums.TipoDeTrans;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    private Long idTransaccion;

    private BigDecimal monto;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transaccion")
    private TipoDeTrans tipoDeTrans;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_transaccion")
    private EstadoTransaccion estadoTransaccion;

    @Column(name = "referencia_externa",nullable = false, unique = true, updatable = false)
    private String referenciaExterna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
