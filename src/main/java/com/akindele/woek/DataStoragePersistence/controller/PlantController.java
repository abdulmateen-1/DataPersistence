package com.akindele.woek.DataStoragePersistence.controller;

import com.akindele.woek.DataStoragePersistence.dto.PlantDTO;
import com.akindele.woek.DataStoragePersistence.dto.Views;
import com.akindele.woek.DataStoragePersistence.entity.Plant;
import com.akindele.woek.DataStoragePersistence.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;


    @PostMapping("/save")
    public Long postPlants(@RequestBody Plant plant) {
        return plantService.save(plant);
    }

    @GetMapping("/under-price/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> getPlantLessPrice(BigDecimal price) {
        return plantService.findPlantsBelowPrice(price);
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.isDelivered(id);
    }

}
