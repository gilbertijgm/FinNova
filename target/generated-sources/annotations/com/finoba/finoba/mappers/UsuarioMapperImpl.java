package com.finoba.finoba.mappers;

import com.finoba.finoba.dtos.usuario.UsuarioDTO;
import com.finoba.finoba.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-17T22:31:32-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO.UsuarioDTOBuilder usuarioDTO = UsuarioDTO.builder();

        usuarioDTO.idUsuario( usuario.getIdUsuario() );
        usuarioDTO.nombreApellido( usuario.getNombreApellido() );
        usuarioDTO.dni( usuario.getDni() );
        usuarioDTO.direccion( usuario.getDireccion() );

        return usuarioDTO.build();
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.idUsuario( usuarioDTO.getIdUsuario() );
        usuario.nombreApellido( usuarioDTO.getNombreApellido() );
        usuario.dni( usuarioDTO.getDni() );
        usuario.direccion( usuarioDTO.getDireccion() );

        return usuario.build();
    }

    @Override
    public List<UsuarioDTO> toDtoList(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }

    @Override
    public List<Usuario> toEntityList(List<UsuarioDTO> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( usuarios.size() );
        for ( UsuarioDTO usuarioDTO : usuarios ) {
            list.add( toEntity( usuarioDTO ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(UsuarioDTO usuarioDTO, Usuario usuarioEntity) {
        if ( usuarioDTO == null ) {
            return;
        }

        usuarioEntity.setNombreApellido( usuarioDTO.getNombreApellido() );
        usuarioEntity.setDni( usuarioDTO.getDni() );
        usuarioEntity.setDireccion( usuarioDTO.getDireccion() );
    }
}
