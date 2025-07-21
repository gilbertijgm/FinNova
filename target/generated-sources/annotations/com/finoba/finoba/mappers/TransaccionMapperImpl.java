package com.finoba.finoba.mappers;

import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.dtos.usuario.NombreDniUsuarioDTO;
import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.entities.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-21T18:45:26-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TransaccionMapperImpl implements TransaccionMapper {

    @Override
    public TransaccionRequestDTO toDto(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransaccionRequestDTO.TransaccionRequestDTOBuilder transaccionRequestDTO = TransaccionRequestDTO.builder();

        transaccionRequestDTO.monto( transaccion.getMonto() );
        transaccionRequestDTO.categoria( transaccion.getCategoria() );
        transaccionRequestDTO.descripcion( transaccion.getDescripcion() );
        transaccionRequestDTO.tipoDeTrans( transaccion.getTipoDeTrans() );

        return transaccionRequestDTO.build();
    }

    @Override
    public TransRespUserDTO toTransRespUserDTO(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransRespUserDTO.TransRespUserDTOBuilder transRespUserDTO = TransRespUserDTO.builder();

        transRespUserDTO.idTransaccion( transaccion.getIdTransaccion() );
        transRespUserDTO.monto( transaccion.getMonto() );
        transRespUserDTO.fecha( transaccion.getFecha() );
        transRespUserDTO.categoria( transaccion.getCategoria() );
        transRespUserDTO.descripcion( transaccion.getDescripcion() );
        transRespUserDTO.tipoDeTrans( transaccion.getTipoDeTrans() );
        transRespUserDTO.estadoTransaccion( transaccion.getEstadoTransaccion() );
        transRespUserDTO.referenciaExterna( transaccion.getReferenciaExterna() );

        return transRespUserDTO.build();
    }

    @Override
    public Transaccion toEntity(TransaccionRequestDTO transaccionDto) {
        if ( transaccionDto == null ) {
            return null;
        }

        Transaccion.TransaccionBuilder transaccion = Transaccion.builder();

        transaccion.monto( transaccionDto.getMonto() );
        transaccion.categoria( transaccionDto.getCategoria() );
        transaccion.descripcion( transaccionDto.getDescripcion() );
        transaccion.tipoDeTrans( transaccionDto.getTipoDeTrans() );

        return transaccion.build();
    }

    @Override
    public TransaccionResponseDTO toResponse(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransaccionResponseDTO.TransaccionResponseDTOBuilder transaccionResponseDTO = TransaccionResponseDTO.builder();

        transaccionResponseDTO.usuarioResponsable( usuarioToNombreDniUsuarioDTO( transaccion.getUsuario() ) );
        transaccionResponseDTO.idTransaccion( transaccion.getIdTransaccion() );
        transaccionResponseDTO.monto( transaccion.getMonto() );
        transaccionResponseDTO.fecha( transaccion.getFecha() );
        transaccionResponseDTO.categoria( transaccion.getCategoria() );
        transaccionResponseDTO.descripcion( transaccion.getDescripcion() );
        transaccionResponseDTO.tipoDeTrans( transaccion.getTipoDeTrans() );
        transaccionResponseDTO.estadoTransaccion( transaccion.getEstadoTransaccion() );
        transaccionResponseDTO.referenciaExterna( transaccion.getReferenciaExterna() );

        return transaccionResponseDTO.build();
    }

    protected NombreDniUsuarioDTO usuarioToNombreDniUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        NombreDniUsuarioDTO.NombreDniUsuarioDTOBuilder nombreDniUsuarioDTO = NombreDniUsuarioDTO.builder();

        nombreDniUsuarioDTO.idUsuario( usuario.getIdUsuario() );
        nombreDniUsuarioDTO.nombreApellido( usuario.getNombreApellido() );
        nombreDniUsuarioDTO.dni( usuario.getDni() );

        return nombreDniUsuarioDTO.build();
    }
}
