package com.finoba.finoba.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NombreDniUsuarioDTO {

    private Long idUsuario;

    private String nombreApellido;

    private String dni;
}
