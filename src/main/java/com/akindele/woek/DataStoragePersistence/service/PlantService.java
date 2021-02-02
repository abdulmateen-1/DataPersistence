package com.akindele.woek.DataStoragePersistence.service;

import com.akindele.woek.DataStoragePersistence.entity.Plant;
import com.akindele.woek.DataStoragePersistence.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public Long save(Plant plant) {
        return plantRepository.save(plant).getId();
    }


    public Boolean isDelivered(Long id) {
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
    }
}
