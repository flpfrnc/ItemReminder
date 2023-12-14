package br.imd.ufrn.itemremindr.DTO;

import br.imd.ufrn.itemremindr.model.Item;
import br.imd.ufrn.itemremindr.model.User;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdatePlaceDTO(
        @NotNull
        Long id,
        String name,
        User user,
        Set<Item> items) {}
