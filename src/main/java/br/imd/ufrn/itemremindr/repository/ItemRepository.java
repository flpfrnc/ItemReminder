package br.imd.ufrn.itemremindr.repository;

import br.imd.ufrn.itemremindr.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
