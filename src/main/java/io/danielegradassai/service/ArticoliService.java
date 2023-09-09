package io.danielegradassai.service;

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticoliService {


    public Iterable<Articoli> SelTutti();


    public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable);

    public ArticoliDto SelByCodArt(String codArt);

    List<ArticoliDto> SelByDescrizione(String descrizione);

    public ArticoliDto SelByBarcode(String barcode);

    Articoli SelByCodArt2(String codArt);

    public void DelArticolo(Articoli articolo);

    @Transactional
    void InsArticolo(Articoli articolo);
}
