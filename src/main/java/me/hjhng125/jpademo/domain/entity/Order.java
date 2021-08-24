package me.hjhng125.jpademo.domain.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hjhng125.jpademo.domain.status.DeliveryStatus;
import me.hjhng125.jpademo.domain.status.OrderStatus;

@Entity
@Getter
@Builder
@Table(name = "ORDERS")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PACKAGE)
public class Order {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    private String name;

    private LocalDate orderDate;

    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        return Order.builder()
            .member(member)
            .delivery(delivery)
            .orderItems(Arrays.stream(orderItems).collect(Collectors.toList()))
            .orderStatus(OrderStatus.ORDER)
            .orderDate(LocalDate.now())
            .build();
    }

    public void cancel() {
        if (this.delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new RuntimeException("이미 배송 완료된 주문입니다.");
        }

        this.orderStatus = OrderStatus.CANCEL;
        this.orderItems.forEach(OrderItem::cancel);
    }

    public long getTotalPrice() {
        return orderItems.stream()
            .mapToLong(OrderItem::getTotalPrice)
            .sum();
    }

//    public void makeOrder(Member member) {
//        this.member = member;
//        member.getOrders().add(this);
//    }
//
//    public void addOrderItems(OrderItem orderItem) {
//        this.orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//
//    public void makeDelivery(Delivery delivery) {
//        this.delivery = delivery;
//        delivery.setOrder(this);
//    }
}
