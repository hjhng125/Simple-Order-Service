package me.hjhng125.jpademo.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import me.hjhng125.jpademo.domain.entity.Member;
import me.hjhng125.jpademo.domain.entity.Order;
import me.hjhng125.jpademo.domain.parameter.OrderSearch;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);

        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate orderStatusEq = cb.equal(o.get("orderStatus"), orderSearch.getOrderStatus());
            criteria.add(orderStatusEq);
        }

        if (orderSearch.getMemberName() != null) {
            Join<Order, Member> m = o.join("member", JoinType.INNER);
            Predicate memberNameContains = cb.like(m.get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(memberNameContains);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));

        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);

        return query.getResultList();
    }
}
