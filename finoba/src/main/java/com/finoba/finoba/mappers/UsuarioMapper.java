package com.finoba.finoba.mappers;

import com.finoba.finoba.dtos.usuario.UsuarioDTO;
import com.finoba.finoba.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    //metodo para convertir entidad a DTO
    UsuarioDTO toDto(Usuario usuario);
    //metodo para convertir DTO a entidad
    Usuario toEntity(UsuarioDTO usuarioDTO);
    //metodo para convertir listas de entidad a DTO
    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);
    //metodo para convertir listas de DTO a entidad
    List<Usuario> toEntityList(List<UsuarioDTO> usuarios);

    @Mapping(target = "idUsuario", ignore = true) //evita sobreescritura del ID  u otros campos sensibles
    void updateEntityFromDto(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuarioEntity);

}
