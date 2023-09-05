package io.danielegradassai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "famassort")
public class FamAssort {

    @Id
    @Column(name = "ID")
    private int id;

    @Id
    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "famAssort")
    private Set<Articoli> articoli = new HashSet<>();

}
