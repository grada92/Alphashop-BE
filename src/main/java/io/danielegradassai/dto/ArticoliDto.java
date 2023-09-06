package io.danielegradassai.dto;

import io.danielegradassai.entity.Barcode;
import io.danielegradassai.entity.FamAssort;
import io.danielegradassai.entity.Ingredienti;
import io.danielegradassai.entity.Iva;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ArticoliDto {

    private String codArt;
    private String descrizione;
    private String um;
    private String codStat;
    private Integer pzCart;
    private double pesoNetto;
    private String idStatoArt;
    private Date dataCreaz;
    private double prezzo = 0;

    private Set<Barcode> barcode = new HashSet<>();
    private Ingredienti ingredienti;
    private FamAssort famAssort;
    private Iva iva;
}
