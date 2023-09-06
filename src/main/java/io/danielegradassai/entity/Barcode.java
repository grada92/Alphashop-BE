package io.danielegradassai.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BARCODE")
@Data
public class Barcode implements Serializable
{
    private static final long serialVersionUID = 1853763261962860635L;

    @Id
    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "IDTIPOART")
    private String idTipoArt;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "CODART", referencedColumnName = "codArt")
    @JsonBackReference
    private Articoli articolo;

}