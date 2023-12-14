package br.imd.ufrn.itemremindr.controller;
import br.imd.ufrn.itemremindr.DTO.UpdatePlaceDTO;
import br.imd.ufrn.itemremindr.model.Place;
import br.imd.ufrn.itemremindr.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/places" , "/place"})
public class PlaceController {

    @Autowired
    PlaceService place;

    @PostMapping
    public void register(@RequestBody @Valid Place data){
        place.registerPlace(data);
    }

    @GetMapping
    public List<Place> list(){
        return place.listPlaces();
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable Long id){
        place.deletePlace(id);
    }

    @PutMapping
    public void updatePlace(@RequestBody @Valid UpdatePlaceDTO data){
        place.updatePlace(data);
    }
}
