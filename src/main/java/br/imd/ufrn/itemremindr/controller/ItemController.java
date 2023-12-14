package br.imd.ufrn.itemremindr.controller;

import br.imd.ufrn.itemremindr.DTO.UpdateItemDTO;
import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/items" , "/item"})
public class ItemController {

    @Autowired
    ItemService item;

    @PostMapping
    public void register(@RequestBody @Valid Item data){
        item.save(data);
    }

    @GetMapping
    public List<Item> list(){
        return item.listItems();
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable Long id){
        item.deleteItem(id);
    }

    @PutMapping
    public void updatePlace(@RequestBody @Valid UpdateItemDTO data){
        item.updateItem(data);
    }
}
