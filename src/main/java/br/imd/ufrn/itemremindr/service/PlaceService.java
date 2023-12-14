package br.imd.ufrn.itemremindr.service;



import br.imd.ufrn.itemremindr.DTO.UpdatePlaceDTO;
import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.model.Place;
import br.imd.ufrn.itemremindr.repository.ItemRepository;
import br.imd.ufrn.itemremindr.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository repository;

    @Autowired
    ItemRepository itemRepository;

    public Place registerPlace (Place place){
        return repository.save(place);
    }

    @Transactional
    public void savePlacesSeparately(List<Place> places) {
        for (Place place : places) {
            repository.save(place);
        }
    }

    public Optional<Place> ListById(Long id) {
        return repository.findById(id);
    }

    public List<Place> listPlaces(){
        return  repository.findAll();
    }

    @Transactional
    public ResponseEntity updatePlace(UpdatePlaceDTO data, Long id) {
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        Place place = repository.getReferenceById(id);
        place.updatePlace(data);
        return ResponseEntity.ok().build();

    }

    @Transactional
    public ResponseEntity deletePlace(Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

    @Transactional
    public Place associateItemWithPlace(Place place, Item item) {
        Set<Item> items = place.getItems();
        items.add(item);
        place.setItems(items);
        return repository.save(place);
    }
}
