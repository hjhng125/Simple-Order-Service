package me.hjhng125.jpademo.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import lombok.Getter;
import me.hjhng125.jpademo.exception.NotEnoughStockException;

@Getter
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private long price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int stockQuantity) {
        if (this.stockQuantity < 0) {
            throw new NotEnoughStockException("재고가 모자랍니다.");
        }
        this.stockQuantity -= stockQuantity;
    }
}
