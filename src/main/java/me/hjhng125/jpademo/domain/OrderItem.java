package me.hjhng125.jpademo.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    private long orderPrice;

    private int count;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }
}
