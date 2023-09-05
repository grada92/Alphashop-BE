package io.danielegradassai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BARCODE")
public class Barcode implements Serializable {

    private static final long serialVersionUID = 1853763261962860635L;

    @Id
    @Column(name = "BARCODE")
    private String barcode;

    @Id
    @Column(name = "IDTIPOART")
    private String idTipoArt;

}
