package br.imd.ufrn.itemremindr.model;

import jakarta.persistence.*;
import java.util.Set;


import br.imd.ufrn.itemremindr.DTO.UpdatePlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "places")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToMany(mappedBy = "places")
    private Set<Item> items;

    public Place(Place place) {
        this.name = place.getName();
        this.user = place.getUser();
        this.items = place.getItems();
    }

    public void updatePlace (UpdatePlaceDTO data) {
        if(data.name() != null) {
            this.name = data.name();
        }if(this.user != null) {
            this.user = data.user();
        }if(this.items != null) {
            this.items = data.items();
        }
    }
}