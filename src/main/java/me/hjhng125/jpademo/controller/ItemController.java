package me.hjhng125.jpademo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hjhng125.jpademo.domain.entity.Book;
import me.hjhng125.jpademo.domain.entity.Item;
import me.hjhng125.jpademo.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items =
            itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/new")
    public String createForm() {
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book book) {
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String update(Book book) {
        itemService.saveItem(book);
        return "redirect:/items";
    }
}
