package br.imd.ufrn.itemremindr.controller;
import br.imd.ufrn.itemremindr.DTO.UpdatePlaceDTO;
import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.model.Place;
import br.imd.ufrn.itemremindr.model.User;
import br.imd.ufrn.itemremindr.repository.UserRepository;
import br.imd.ufrn.itemremindr.service.ItemService;
import br.imd.ufrn.itemremindr.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/places" , "/place"})
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid Place data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            // checks if the user is authenticated and the principal is a UserDetails object
            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
                String username = userDetails.getUsername();

                User user = (User) userRepository.findByUsername(username);
                if (user != null) {
                    data.setUser(user);
                }

                placeService.registerPlace(data);
            }
            return ResponseEntity.ok("Place registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }

    @GetMapping
    public List<Place> list(){
        return placeService.listPlaces();
    }

    @GetMapping("/{id}")
    public Optional<Place> listOne(@PathVariable Long id){
        return placeService.ListById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable Long id){
        placeService.deletePlace(id);
    }

    @PutMapping("/{id}")
    public void updatePlace(@PathVariable Long id, @RequestBody @Valid UpdatePlaceDTO data){
        placeService.updatePlace(data, id);
    }

    @PutMapping("/{placeId}/item/{itemId}")
    public ResponseEntity<Place> associateItemWithPlace(
            @PathVariable Long placeId,
            @PathVariable Long itemId) {

        Place place = placeService.ListById(placeId).orElse(null);
        Item item = itemService.ListById(itemId).orElse(null);

        if (place != null && item != null) {
           return ResponseEntity.ok(placeService.associateItemWithPlace(place, item));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
