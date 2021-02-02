package com.akindele.woek.DataStoragePersistence.repository;

import com.akindele.woek.DataStoragePersistence.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    //Check if a plants by this Id exists and confirm it it was delivered
    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    //Return a boolean directly
    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(@Param("plantId") Long plantId);

    //return a list of plant with values less than the price
    List<Plant> findByPriceLessThan(BigDecimal price);


}
