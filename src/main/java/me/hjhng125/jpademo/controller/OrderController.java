package me.hjhng125.jpademo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hjhng125.jpademo.domain.entity.Item;
import me.hjhng125.jpademo.domain.entity.Member;
import me.hjhng125.jpademo.service.ItemService;
import me.hjhng125.jpademo.service.MemberService;
import me.hjhng125.jpademo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/orders")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/orderForm";
    }

    @PostMapping("/orders")
    public String create(
        @RequestParam("memberId") Long memberId,
        @RequestParam Long itemId,
        @RequestParam int count,
        Model model) {

        Long order = orderService.order(memberId, itemId, count);
        model.addAttribute("order", order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
