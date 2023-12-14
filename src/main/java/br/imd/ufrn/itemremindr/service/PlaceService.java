package br.imd.ufrn.itemremindr.service;



import br.imd.ufrn.itemremindr.DTO.UpdatePlaceDTO;
import br.imd.ufrn.itemremindr.model.Place;
import br.imd.ufrn.itemremindr.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository repository;
    public ResponseEntity registerPlace (Place place){
        repository.save(new Place(place));
        return  ResponseEntity.noContent().build();
    }

    @Transactional
    public void savePlacesSeparately(List<Place> places) {
        for (Place place : places) {
            repository.save(place);
        }
    }

    @Transactional
    public ResponseEntity deletePlace(Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }


    public List<Place> listPlaces(){
        return  repository.findAll();
    }

    @Transactional
    public ResponseEntity updatePlace(UpdatePlaceDTO data) {
        if(data.id() == null){
            return ResponseEntity.badRequest().build();
        }
        Place place = repository.getReferenceById(data.id());
        place.updatePlace(data);
        return ResponseEntity.ok().build();

    }
}
