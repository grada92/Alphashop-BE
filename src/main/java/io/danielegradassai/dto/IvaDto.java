package io.danielegradassai.dto;

import lombok.Data;

@Data
public class IvaDto {
    private int idIva;
    private String descrizione;
    private int aliquota;
}
