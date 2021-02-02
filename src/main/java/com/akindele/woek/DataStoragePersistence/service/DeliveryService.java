package com.akindele.woek.DataStoragePersistence.service;

import com.akindele.woek.DataStoragePersistence.dto.RecipientAndPrice;
import com.akindele.woek.DataStoragePersistence.entity.Delivery;
import com.akindele.woek.DataStoragePersistence.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.recipient(deliveryId);
    }
}
