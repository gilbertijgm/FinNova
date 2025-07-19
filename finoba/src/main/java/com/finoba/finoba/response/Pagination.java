package com.finoba.finoba.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private int paginaActual;
    private int tamanioDePagina;
    private int totalDePaginas;
    private long totalElements;
    private boolean pagSiguiente;
    private boolean pagAnterior;
}
