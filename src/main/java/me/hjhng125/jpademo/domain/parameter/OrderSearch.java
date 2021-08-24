package me.hjhng125.jpademo.domain.parameter;

import lombok.Getter;
import lombok.Setter;
import me.hjhng125.jpademo.domain.status.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName;

    private OrderStatus orderStatus;
}
