package io.danielegradassai.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "{NotNull.Barcode.barcode.Validation}")
    @Size(min = 8, max = 13, message = "{Size.Barcode.barcode.Validation}")
    private String barcode;

    @Column(name = "IDTIPOART")
    @NotNull(message = "{NotNull.Barcode.idTipoArt.Validation}")
    private String idTipoArt;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "CODART", referencedColumnName = "codArt")
    @JsonBackReference
    private Articoli articolo;

}