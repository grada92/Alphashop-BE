package io.danielegradassai.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
@Data
public class Articoli  implements Serializable
{
    private static final long serialVersionUID = 291353626011036772L;

    @Id
    @Column(name = "CODART")
    private String codArt;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Column(name = "UM")
    private String um;

    @Column(name = "CODSTAT")
    private String codStat;

    @Column(name = "PZCART")
    private Integer pzCart;

    @Column(name = "PESONETTO")
    private double pesoNetto;

    @Column(name = "IDSTATOART")
    private String idStatoArt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATACREAZIONE")
    private Date dataCreaz;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
    @JsonManagedReference
    private Set<Barcode> barcode = new HashSet<>();

    @OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ingredienti ingredienti;

    @ManyToOne
    @JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
    private Iva iva;

    @ManyToOne
    @JoinColumn(name = "IDFAMASS", referencedColumnName = "ID")
    private FamAssort famAssort;


}