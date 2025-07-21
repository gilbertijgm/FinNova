package com.finoba.finoba.repository;

import com.finoba.finoba.entities.Transaccion;
import com.finoba.finoba.entities.enums.Categoria;
import com.finoba.finoba.entities.enums.EstadoTransaccion;
import com.finoba.finoba.entities.enums.TipoDeTrans;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransaccionRepositoryImpl implements TransaccionRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Transaccion> buscarTransConFiltros(LocalDate fechaDesde, LocalDate fechaHasta, String categoria, String tipo, String estado, Long usuarioId, Pageable pageable) {
        // ============================
        // CONSULTA PRINCIPAL (traer datos)
        // ============================

        CriteriaBuilder cb = entityManager.getCriteriaBuilder(); // Obtenemos el constructor de criterios
        CriteriaQuery<Transaccion> query = cb.createQuery(Transaccion.class); // Creamos una consulta que devuelve objetos
        Root<Transaccion> transaccion = query.from(Transaccion.class); // Definimos la entidad base de la consulta (FROM Entidad)
        List<Predicate> predicates = new ArrayList<>(); // Lista que acumulará los filtros dinámicos (WHERE)

        //Filtro por rango de fecha
        if (fechaDesde != null && fechaHasta !=  null){
            predicates.add(cb.between(transaccion.get("fecha"), fechaDesde.atStartOfDay(), fechaHasta.atTime(23, 59, 59)));
        } else if (fechaDesde != null ){
            predicates.add(cb.greaterThanOrEqualTo(transaccion.get("fecha"), fechaDesde.atStartOfDay()));
        } else if (fechaHasta != null){
            predicates.add(cb.lessThanOrEqualTo(transaccion.get("fecha"), fechaHasta.atTime(23, 59, 59)));
        }

        //filtrar por categoria
        if (categoria != null && !categoria.isBlank()){
            Predicate categoriaEqual = cb.equal(transaccion.get("categoria"), Categoria.valueOf(categoria.toUpperCase()));
            predicates.add(categoriaEqual);
        }

        //filtrar por tipo
        if (tipo != null && !tipo.isBlank()){
            Predicate tipoTransEqual = cb.equal(transaccion.get("tipoDeTrans"), TipoDeTrans.valueOf(tipo.toUpperCase()));
            predicates.add(tipoTransEqual);
        }

        //filtrar por estado
        if (estado != null && !estado.isBlank()){
            Predicate estadoEqual = cb.equal(transaccion.get("estadoTransaccion"), EstadoTransaccion.valueOf(estado.toUpperCase()));
            predicates.add(estadoEqual);
        }

        //filtrar por usuarioID
        if (usuarioId != null ){

            predicates.add(cb.equal(transaccion.get("usuario").get("idUsuario"), usuarioId));
        }

        // Aplicamos todos los filtros acumulados con AND
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // ============================
        // EJECUTAMOS LA CONSULTA PRINCIPAL CON PAGINACIÓN
        // ============================

        // Creamos el objeto TypedQuery a partir del CriteriaQuery construido
        TypedQuery<Transaccion> typedQuery = entityManager.createQuery(query);

        // Configuramos la paginación:
        // - offset: cantidad de registros a omitir (según la página actual)
        // - maxResults: cantidad máxima de registros a devolver por página
        typedQuery.setFirstResult((int) pageable.getOffset());         // Ejemplo: página 2, size 10 → offset = 20
        typedQuery.setMaxResults(pageable.getPageSize());              // Tamaño de página definido por el cliente

        // Ejecutamos la consulta y obtenemos los resultados paginados
        List<Transaccion> resultList = typedQuery.getResultList();

        // ============================
        // CONSULTA DE CONTEO (para saber cuántos registros en total hay con los filtros aplicados)
        // ============================

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class); // Creamos consulta de tipo Long (conteo)
        Root<Transaccion> countRoot = countQuery.from(Transaccion.class); // Nueva raíz, no se puede usar la anterior
        List<Predicate> countPredicates = new ArrayList<>(); // Lista de filtros (de nuevo, pero para count)

        // ========== CONSULTA DE CONTEO ==========

        //Conteo para rango de fecha
        if (fechaDesde != null && fechaHasta !=  null){
            countPredicates.add(cb.between(countRoot.get("fecha"), fechaDesde.atStartOfDay(), fechaHasta.atTime(23, 59, 59)));
        } else if (fechaDesde != null ){
            countPredicates.add(cb.greaterThanOrEqualTo(countRoot.get("fecha"), fechaDesde.atStartOfDay()));
        } else if (fechaHasta != null){
            countPredicates.add(cb.lessThanOrEqualTo(countRoot.get("fecha"), fechaHasta.atTime(23, 59, 59)));
        }

        //filtrar por categoria
        if (categoria != null && !categoria.isBlank()){
            Predicate categoriaEqualCount = cb.equal(countRoot.get("categoria"), Categoria.valueOf(categoria.toUpperCase()));
            countPredicates.add(categoriaEqualCount);
        }

        //filtrar por tipo
        if (tipo != null && !tipo.isBlank()){
            Predicate tipoTransEqualCount = cb.equal(countRoot.get("tipoDeTrans"), TipoDeTrans.valueOf(tipo.toUpperCase()));
            predicates.add(tipoTransEqualCount);
        }

        //filtrar por estado
        if (estado != null && !estado.isBlank()){
            Predicate estadoEqualCount = cb.equal(countRoot.get("estadoTransaccion"), EstadoTransaccion.valueOf(estado.toUpperCase()));
            predicates.add(estadoEqualCount);
        }

        //filtrar por usuarioID
        if (usuarioId != null ){

            countPredicates.add(cb.equal(countRoot.get("usuario").get("idUsuario"), usuarioId));
        }

        // Definimos la operación de conteo con todos los filtros aplicados
        countQuery.select(cb.count(countRoot)).where(cb.and(countPredicates.toArray(new Predicate[0])));

        // Ejecutamos la consulta y obtenemos el total de resultados
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // ============================
        // RETORNAMOS LA PÁGINA RESULTANTE
        // ============================
        // Devolvemos un PageImpl con la lista de tareas, la paginación original, y el total
        return new PageImpl<>(resultList, pageable, total);
    }
}
