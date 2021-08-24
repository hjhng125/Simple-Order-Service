package me.hjhng125.jpademo.domain.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PACKAGE)
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

//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public static OrderItem createOrderItem(Item item, long orderPrice, int count) {
        item.removeStock(count);
        return OrderItem.builder()
            .item(item)
            .orderPrice(orderPrice)
            .count(count)
            .build();
    }

    public void cancel() {
        this.item.addStock(count);
    }

    public long getTotalPrice() {
        return this.orderPrice * this.count;
    }
}
