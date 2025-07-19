package com.finoba.finoba.dtos.transaccion;

import com.finoba.finoba.entities.enums.Categoria;
import com.finoba.finoba.entities.enums.TipoDeTrans;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaccionRequestDTO {

    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoDeTrans tipoDeTrans;
}
