package me.hjhng125.jpademo.domain;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }
}
