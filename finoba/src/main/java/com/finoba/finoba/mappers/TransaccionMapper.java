package com.finoba.finoba.mappers;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface TransaccionMapper {

    //metodo para convertir entidad a DTO
    TransaccionRequestDTO toDto(Transaccion transaccion);

    //metodo para convertir entidad a DTO
    TransRespUserDTO toTransRespUserDTO(Transaccion transaccion);

    //metodo para convertir DTO a entidad
    Transaccion toEntity(TransaccionRequestDTO transaccionDto);

    @Mapping(source = "usuario", target = "usuarioResponsable")
    TransaccionResponseDTO toResponse(Transaccion transaccion);

    @Mapping(target = "idUsuario", source = "usuario.idUsuario")
    @Mapping(target = "nombreApellido", expression = "java(usuario.getNombreApellido())")
    @Mapping(target = "dni", source = "usuario.dni")
    @Mapping(target = "transacciones", source = "listaTransacciones")
    TransPorUsuarioDTO toTransPorUsuarioDTO(Usuario usuario, List<Transaccion> listaTransacciones);


}
