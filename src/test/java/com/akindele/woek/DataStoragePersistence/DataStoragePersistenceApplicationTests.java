package com.akindele.woek.DataStoragePersistence;

import com.akindele.woek.DataStoragePersistence.entity.Delivery;
import com.akindele.woek.DataStoragePersistence.entity.Plant;
import com.akindele.woek.DataStoragePersistence.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class DataStoragePersistenceApplicationTests {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    PlantRepository plantRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testPriceLessThan() {
        Plant plant = entityManager.persist(new Plant("yellow", 54.54));
        entityManager.persist(new Plant("cauliflower", 24.35));

        List<Plant> priceLessThan = plantRepository.findByPriceLessThan(BigDecimal.valueOf(32));
        Assertions.assertTrue(priceLessThan.get(0).getPrice().doubleValue() < 32);
        System.out.println(plantRepository.deliveryCompleted(plant.getId()));
    }

    @Test
    public void testDeliveryCompleted() {
        Plant p = entityManager.persist(new Plant("Baz root", 3.48));
        Delivery d = entityManager.persist(new Delivery("Abdulmatin Akindele",
                "1234 West Street", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        Boolean value = plantRepository.deliveryCompleted(p.getId());
        System.out.println(value);
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}
