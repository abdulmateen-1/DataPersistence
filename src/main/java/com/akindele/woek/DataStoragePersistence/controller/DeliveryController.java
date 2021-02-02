package com.akindele.woek.DataStoragePersistence.controller;

import com.akindele.woek.DataStoragePersistence.dto.RecipientAndPrice;
import com.akindele.woek.DataStoragePersistence.entity.Delivery;
import com.akindele.woek.DataStoragePersistence.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    //returns the delivery ID after taking a body of delivery data
    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    /**
     * returns the name and price of each recipient
     */

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }
}
