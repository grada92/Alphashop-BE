package io.danielegradassai.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Integer pzCart;

    @Id
    @Column(name = "PESONETTO")
    private double pesoNetto;

    @Id
    @Column(name = "IDSTATOART")
    private String idStatoArt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATACREAZIONE")
    private Date dataCreaz;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
    @JsonManagedReference
    private Set<Barcode> barcode = new HashSet<>();

    @OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL,orphanRemoval = true)
    private Ingredienti ingredienti;

    @ManyToOne
    @JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
    private Iva iva;

    @ManyToOne
    @JoinColumn(name = "IDFAMASS", referencedColumnName = "ID")
    private FamAssort famAssort;





}
