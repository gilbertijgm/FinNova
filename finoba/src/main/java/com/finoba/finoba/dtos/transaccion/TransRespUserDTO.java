package com.finoba.finoba.dtos.transaccion;

import com.finoba.finoba.entities.enums.Categoria;
import com.finoba.finoba.entities.enums.EstadoTransaccion;
import com.finoba.finoba.entities.enums.TipoDeTrans;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class TransRespUserDTO {

    private Long idTransaccion;

    private BigDecimal monto;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoDeTrans tipoDeTrans;

    @Enumerated(EnumType.STRING)
    private EstadoTransaccion estadoTransaccion;


    private String referenciaExterna;
}
