package com.akindele.woek.DataStoragePersistence.repository;

import com.akindele.woek.DataStoragePersistence.dto.RecipientAndPrice;
import com.akindele.woek.DataStoragePersistence.entity.Delivery;
import com.akindele.woek.DataStoragePersistence.entity.Plant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        entityManager.remove(entityManager.find(Delivery.class, id));
    }

    public List<Delivery> findAllDeliveries(String name) {
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findAll", Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public RecipientAndPrice recipient(Long deliveryId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> recipientAndPrice = builder.createQuery(RecipientAndPrice.class);

        Root<Plant> root = recipientAndPrice.from(Plant.class);
        recipientAndPrice.select(
                builder.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        builder.sum(root.get("price"))))
                .where(builder.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(recipientAndPrice).getSingleResult();
    }
}
