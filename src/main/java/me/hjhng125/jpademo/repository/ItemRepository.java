package me.hjhng125.jpademo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
}
