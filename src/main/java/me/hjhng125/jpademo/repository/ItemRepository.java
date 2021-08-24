package me.hjhng125.jpademo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findById(Long itemId) {
        return entityManager.find(Item.class, itemId);
    }
}
