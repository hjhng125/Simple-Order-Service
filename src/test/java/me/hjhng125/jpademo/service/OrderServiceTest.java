package me.hjhng125.jpademo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.entity.Address;
import me.hjhng125.jpademo.domain.entity.Book;
import me.hjhng125.jpademo.domain.entity.Item;
import me.hjhng125.jpademo.domain.entity.Member;
import me.hjhng125.jpademo.domain.entity.Order;
import me.hjhng125.jpademo.domain.status.OrderStatus;
import me.hjhng125.jpademo.exception.NotEnoughStockException;
import me.hjhng125.jpademo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() {
        Member member = createMember();
        System.out.println("member = " + member);
        int stockQuantity = 10;
        Item item = createBook("jpa programming", 10000, stockQuantity);
        System.out.println("item = " + item);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        Order order = orderRepository.findById(orderId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(order.getOrderItems().size()).isEqualTo(1L);
        assertThat(order.getTotalPrice()).isEqualTo(item.getPrice() * orderCount);
        assertThat(item.getStockQuantity()).isEqualTo(stockQuantity - orderCount);
    }

    @Test
    void 상품주문_재고수량초과() {
        Member member = createMember();
        System.out.println("member = " + member);
        int stockQuantity = 10;
        Item item = createBook("jpa programming", 10000, stockQuantity);
        System.out.println("item = " + item);

        int orderCount = 11; // 재고 초과

        NotEnoughStockException notEnoughStockException = assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));
        assertThat(notEnoughStockException.getMessage()).isEqualTo("재고가 모자랍니다.");
    }

    @Test
    void 주문취소() {
        Member member = createMember();
        System.out.println("member = " + member);
        int stockQuantity = 10;
        Item item = createBook("jpa programming", 10000, stockQuantity);
        System.out.println("item = " + item);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order byId = orderRepository.findById(orderId);

        assertThat(byId.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(stockQuantity);
    }

    private Member createMember() {
        Member member = Member.builder()
            .name("hong")
            .address(Address.builder()
                .city("경기도")
                .street("탄천로")
                .zipCode("123-123")
                .build())
            .build();

        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
            .author("jamez")
            .name(name)
            .stockQuantity(stockQuantity)
            .price(price)
            .build();

        em.persist(book);
        return book;
    }
}