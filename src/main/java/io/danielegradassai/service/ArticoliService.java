package io.danielegradassai.service;

import io.danielegradassai.entity.Articoli;

import java.awt.print.Pageable;
import java.util.List;

public interface ArticoliService {

    public Iterable<Articoli> SelTutti();
    public List<Articoli> SelByDescrizione(String descrizione);
    public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable);
    public Articoli SelByCodArt(String codArt);
    public void DelArticolo(Articoli articolo);
    public void InsArticolo(Articoli articolo);
}
