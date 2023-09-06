package io.danielegradassai.service;

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticoliService {


    public Iterable<Articoli> SelTutti();

    public List<ArticoliDto> SelByDescrizione(String descrizione);

    public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable);

    public ArticoliDto SelByCodArt(String codArt);

    public ArticoliDto SelByBarcode(String barcode);

    public void DelArticolo(Articoli articolo);

    public void InsArticolo(Articoli articolo);



}
