package br.imd.ufrn.itemremindr.DTO;

import br.imd.ufrn.itemremindr.model.Place;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record UpdateItemDTO(
        String name) {}
