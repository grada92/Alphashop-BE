package io.danielegradassai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ARTICOLI")
public class Articoli implements Serializable {

    private static final long serialVersionUID = 291353626911036772L;

    @Id
    @Column(name = "CODART")
    private String codArt;

    @Id
    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Id
    @Column(name = "UM")
    private String um;

    @Id
    @Column(name = "CODSTAT")
    private String codStat;

    @Id
    @Column(name = "PZCART")
    private String pzCart;

    @Id
    @Column(name = "PESONETTO")
    private String pesoNetto;

    @Id
    @Column(name = "IDSTATOART")
    private String idStatoArt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATACREAZIONE")
    private Date dataCreaz;





}
