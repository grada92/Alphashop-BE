package io.danielegradassai.repository;

import io.danielegradassai.entity.Articoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface ArticoliRepository extends JpaRepository<Articoli, String> {

    @Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
    List<Articoli> SelbyDescrizioneLike(@Param("desArt") String descrizione);
    List<Articoli> findByDescrizioneLike(String descrizione , Pageable pageable);
    Articoli findByCodArt(String codArt);
}
