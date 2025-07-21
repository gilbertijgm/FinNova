package com.finoba.finoba.service.implementaciones;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.entities.Usuario;
import com.finoba.finoba.entities.enums.EstadoTransaccion;
import com.finoba.finoba.entities.enums.TipoDeTrans;
import com.finoba.finoba.exceptions.BadRequestException;
import com.finoba.finoba.exceptions.ResourceNotFoundException;
import com.finoba.finoba.mappers.TransaccionMapper;
import com.finoba.finoba.persistence.ITransaccionDAO;
import com.finoba.finoba.persistence.IUsuarioDAO;
import com.finoba.finoba.service.ITransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements ITransaccionService {

    private final ITransaccionDAO transaccionDAO;
    private final TransaccionMapper transaccionMapper;
    private final IUsuarioDAO usuarioDAO;

    @Override
    public TransaccionResponseDTO crearTransaccion(Long usuarioId, TransaccionRequestDTO transaccionDTO) {
        BigDecimal saldo = BigDecimal.valueOf(500000.00);
        Usuario usuario = usuarioDAO.usuario(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " +usuarioId));

        //validar regla de negocios
        validarMonto(transaccionDTO, saldo);

        Transaccion transaccion = transaccionMapper.toEntity(transaccionDTO);

//        LocalDateTime hoy = LocalDateTime.now();
//        String referencia = "TRX-" + UUID.randomUUID();
//
//        transaccion.setFecha(hoy);
//        transaccion.setReferenciaExterna(referencia);
//        transaccion.setEstadoTransaccion(EstadoTransaccion.PENDIENTE);
//        transaccion.setUsuario(usuario);

        prepararTransaccion(transaccion, usuario);

        transaccion = transaccionDAO.crearTransaccion(transaccion);

        return transaccionMapper.toResponse(transaccion);
    }

    @Override
    public TransaccionResponseDTO obtenerTransaccion(String referenciaExterna) {
        Transaccion transaccion = transaccionDAO.obtenerTransaccion(referenciaExterna)
                .orElseThrow(() -> new ResourceNotFoundException("Transaccion no encotrada con la referencia: "+ referenciaExterna));

        TransaccionResponseDTO dto = transaccionMapper.toResponse(transaccion);

        return dto;
    }

//    @Override
//    public TransPorUsuarioDTO obtenerTransPorUsuario(Long usuarioId) {
//        Usuario usuario = usuarioDAO.usuario(usuarioId)
//                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " +usuarioId));
//
//        List<Transaccion> transaccionList = transaccionDAO.obtenerTransPorUsuario(usuarioId);
//
//        TransPorUsuarioDTO dto = transaccionMapper.toTransPorUsuarioDTO(usuario,transaccionList);
//
//        return dto;
//    }

    @Override
    public Page<TransRespUserDTO> obtenerTransPorUsuario(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioDAO.usuario(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId));

        Page<Transaccion> transaccionesPage = transaccionDAO.obtenerTransPorUsuario(usuario.getIdUsuario(), pageable);

        List<TransRespUserDTO> dtoList = transaccionesPage.getContent()
                .stream()
                .map(transaccionMapper::toTransRespUserDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, transaccionesPage.getTotalElements());
    }

    @Override
    public Page<TransaccionResponseDTO> obtenerTransPorUsuarioFiltro(LocalDate fechaDesde, LocalDate fechaHasta, String categoria, String tipo, String estado, Long idUsuario, Pageable pageable) {
        Usuario usuario = usuarioDAO.usuario(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + idUsuario));

        Page<Transaccion> transaccionesPage = transaccionDAO.obtenerTransPorUsuarioFiltros(fechaDesde,fechaHasta,categoria,tipo,estado,usuario.getIdUsuario(), pageable);

        List<TransaccionResponseDTO> dtoList = transaccionesPage.getContent()
                .stream()
                .map(transaccionMapper::toResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, transaccionesPage.getTotalElements());
    }

    //en este metodo valido que el monto no sea negativo en caso de Ingreso, y que verifique que no sea mayor que el saldo actual
    private void validarMonto(TransaccionRequestDTO transaccionDTO, BigDecimal saldo){
        if (transaccionDTO.getTipoDeTrans() == TipoDeTrans.INGRESO){
            if (transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0){
                throw new BadRequestException("El monto igreseado no puede ser negativo");
            }
        } else if (transaccionDTO.getTipoDeTrans() == TipoDeTrans.EGRESO){
            if (transaccionDTO.getMonto().compareTo(saldo) > 0){
                throw new BadRequestException("Saldo insuficiente para la transaccion");
            }
        }
    }

    //en este metodo realizo el seteo de los campos automaticos que no viene del cliente en la peticion
    private void prepararTransaccion(Transaccion transaccion, Usuario usuario){
        LocalDateTime hoy = LocalDateTime.now();
        String referencia = "TRX-" + UUID.randomUUID();

        transaccion.setFecha(hoy);
        transaccion.setReferenciaExterna(referencia);
        transaccion.setEstadoTransaccion(EstadoTransaccion.PENDIENTE);
        transaccion.setUsuario(usuario);
    }
}
