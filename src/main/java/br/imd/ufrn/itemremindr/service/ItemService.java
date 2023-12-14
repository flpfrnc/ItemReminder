package br.imd.ufrn.itemremindr.service;

import br.imd.ufrn.itemremindr.DTO.UpdateItemDTO;
import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;
    public Item save (Item item){
        return repository.save(item);
    }

    @Transactional
    public void saveItemsSeparately(List<Item> items) {
        for (Item item : items) {
            repository.save(item);
        }
    }

    @Transactional
    public ResponseEntity deleteItem(Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }


    public List<Item> listItems(){
        return  repository.findAll();
    }

    @Transactional
    public ResponseEntity updateItem(UpdateItemDTO data) {
        if(data.id() == null){
            return ResponseEntity.badRequest().build();
        }
        Item item = repository.getReferenceById(data.id());
        item.updateItem(data);
        return ResponseEntity.ok().build();

    }
}
