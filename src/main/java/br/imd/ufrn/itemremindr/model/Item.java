package br.imd.ufrn.itemremindr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;


import br.imd.ufrn.itemremindr.DTO.UpdateItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private Set<Place> places;

    public void updateItem (UpdateItemDTO data) {
        if(data.name() != null) {
            this.name = data.name();
        }
    }
}