package me.hjhng125.jpademo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hjhng125.jpademo.domain.entity.Delivery;
import me.hjhng125.jpademo.domain.entity.Item;
import me.hjhng125.jpademo.domain.entity.Member;
import me.hjhng125.jpademo.domain.entity.Order;
import me.hjhng125.jpademo.domain.entity.OrderItem;
import me.hjhng125.jpademo.domain.parameter.OrderSearch;
import me.hjhng125.jpademo.repository.MemberRepository;
import me.hjhng125.jpademo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemService.findOne(itemId);

        Delivery delivery = Delivery.builder()
            .address(member.getAddress())
            .build();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order byId = orderRepository.findById(orderId);
        byId.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
