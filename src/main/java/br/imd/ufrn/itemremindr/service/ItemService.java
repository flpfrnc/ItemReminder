package br.imd.ufrn.itemremindr.service;

import br.imd.ufrn.itemremindr.DTO.UpdateItemDTO;
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

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    @Autowired
    PlaceRepository placeRepository;
    public Item registerItem (Item item){
        return repository.save(item);
    }

    @Transactional
    public void saveItemsSeparately(List<Item> items) {
        for (Item item : items) {
            repository.save(item);
        }
    }

    public List<Item> listItems(){
        return  repository.findAll();
    }

    public Optional<Item> ListById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ResponseEntity updateItem(UpdateItemDTO data, Long id) {
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        Item item = repository.getReferenceById(id);
        item.updateItem(data);
        return ResponseEntity.ok().build();

    }

    @Transactional
    public ResponseEntity deleteItem(Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
