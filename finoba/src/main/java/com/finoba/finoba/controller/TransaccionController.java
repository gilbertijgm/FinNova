package com.finoba.finoba.controller;

import com.finoba.finoba.dtos.transaccion.TransPorUsuarioDTO;
import com.finoba.finoba.dtos.transaccion.TransRespUserDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionRequestDTO;
import com.finoba.finoba.dtos.transaccion.TransaccionResponseDTO;
import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.response.ApiResponse;
import com.finoba.finoba.response.PagedResponse;
import com.finoba.finoba.response.Pagination;
import com.finoba.finoba.service.ITransaccionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaccion")
public class TransaccionController {

    private final ITransaccionService transaccionService;

    @PostMapping("/crearTrans/{usuarioId}")
    public ResponseEntity<?> crearTransaccion(@PathVariable Long usuarioId ,@RequestBody TransaccionRequestDTO transaccionDTO) throws URISyntaxException {

        TransaccionResponseDTO transaccion = transaccionService.crearTransaccion(usuarioId, transaccionDTO);

        ApiResponse<TransaccionResponseDTO> response = new ApiResponse<>(201, "Transaccion creada con Exito", transaccion);

        return ResponseEntity.created(new URI("/api/transaccion/")).body(response);
    }

//    @GetMapping("/usuario/{usuarioId}/transacciones")
//    public ResponseEntity<?> transPorUsuario(@PathVariable Long usuarioId
//    ){
//
//        TransPorUsuarioDTO transPorUsuarioDTO = transaccionService.obtenerTransPorUsuario(usuarioId);
//
//        ApiResponse<TransPorUsuarioDTO> response = new ApiResponse<>(200, "Listado de transacciones por usuario", transPorUsuarioDTO);
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/byReferencia/{referencia}")
    public ResponseEntity<?> transPorUsuario(@PathVariable String referencia){

        TransaccionResponseDTO transPorReferencia = transaccionService.obtenerTransaccion(referencia);

        ApiResponse<TransaccionResponseDTO> response = new ApiResponse<>(200, "Transaccion encontrada", transPorReferencia);

        return ResponseEntity.ok(response);
    }
//
//    @GetMapping("/usuario/{usuarioId}/transacciones")
//    public ResponseEntity<PagedResponse<TransRespUserDTO>> transPorUsuario(@PathVariable Long usuarioId,
//                                                                           @RequestParam(defaultValue = "0") int page,
//                                                                           @RequestParam(defaultValue = "3") int size,
//                                                                           HttpServletRequest request
//    ){
//        int pageNumber = Math.max(0, page);
//        int pageSize = Math.min(Math.max(1, size), 100);
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("fecha").descending());
//
//        Page<TransRespUserDTO> pageResult = transaccionService.obtenerTransPorUsuario(usuarioId, pageable);
//
//        Pagination pagination = new Pagination(
//                pageResult.getNumber(),
//                pageResult.getSize(),
//                pageResult.getTotalPages(),
//                pageResult.getTotalElements(),
//                pageResult.hasNext(),
//                pageResult.hasPrevious()
//        );
//
//        String baseUrl = request.getRequestURL().toString();
//        String queryParams = request.getQueryString() != null ? "&" + request.getQueryString().replaceAll("page=\\d+", "") : "";
//
//        Map<String, String> links = new LinkedHashMap<>();
//        links.put("firstPage", baseUrl + "?page=0&size=" + size + queryParams);
//        links.put("lastPage", baseUrl + "?page=" + (pageResult.getTotalPages() - 1) + "&size=" + size + queryParams);
//        links.put("nextPage", pageResult.hasNext() ? baseUrl + "?page=" + (page + 1) + "&size=" + size + queryParams : null);
//        links.put("previousPage", pageResult.hasPrevious() ? baseUrl + "?page=" + (page - 1) + "&size=" + size + queryParams : null);
//
//        String mensaje = pageResult.getTotalElements() == 0 ? "No se encontraron transacciones" : "Listado de transacciones por usuario";
//
//        PagedResponse<TransRespUserDTO> response = new PagedResponse<>(
//                200,
//                mensaje,
//                pageResult.getContent(),
//                pagination,
//                links
//        );
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/usuario/{usuarioId}/transaccionesFiltros")
    public ResponseEntity<PagedResponse<TransaccionResponseDTO>> transPorUsuarioFiltro(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String estado,
            @PathVariable Long usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            HttpServletRequest request
    ){
        int pageNumber = Math.max(0, page);
        int pageSize = Math.min(Math.max(1, size), 100);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("fecha").descending());

        Page<TransaccionResponseDTO> pageResult = transaccionService.obtenerTransPorUsuarioFiltro(fechaDesde, fechaHasta,categoria,tipo,estado,usuarioId,pageable);

        Pagination pagination = new Pagination(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getTotalElements(),
                pageResult.hasNext(),
                pageResult.hasPrevious()
        );

        String baseUrl = request.getRequestURL().toString();
        String queryParams = request.getQueryString() != null ? "&" + request.getQueryString().replaceAll("page=\\d+", "") : "";

        Map<String, String> links = new LinkedHashMap<>();
        links.put("firstPage", baseUrl + "?page=0&size=" + size + queryParams);
        links.put("lastPage", baseUrl + "?page=" + (pageResult.getTotalPages() - 1) + "&size=" + size + queryParams);
        links.put("nextPage", pageResult.hasNext() ? baseUrl + "?page=" + (page + 1) + "&size=" + size + queryParams : null);
        links.put("previousPage", pageResult.hasPrevious() ? baseUrl + "?page=" + (page - 1) + "&size=" + size + queryParams : null);

        String mensaje = pageResult.getTotalElements() == 0 ? "No se encontraron transacciones" : "Listado de transacciones por usuario";

        PagedResponse<TransaccionResponseDTO> response = new PagedResponse<>(
                200,
                mensaje,
                pageResult.getContent(),
                pagination,
                links
        );

        return ResponseEntity.ok(response);
    }

}
