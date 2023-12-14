package br.imd.ufrn.itemremindr.controller;

import br.imd.ufrn.itemremindr.DTO.UpdateItemDTO;
import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.model.Place;
import br.imd.ufrn.itemremindr.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = {"/items" , "/item"})
public class ItemController {

    @Autowired
    ItemService item;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid Item data){
        try {
            item.registerItem(data);
            return ResponseEntity.ok("Place registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping
    public List<Item> list(){
        return item.listItems();
    }

    @GetMapping("/{id}")
    public Optional<Item> listOne(@PathVariable Long id){
        return item.ListById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        item.deleteItem(id);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody @Valid UpdateItemDTO data){
        item.updateItem(data, id);
    }
}
