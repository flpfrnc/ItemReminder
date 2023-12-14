package br.imd.ufrn.itemremindr.repository;

import br.imd.ufrn.itemremindr.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
