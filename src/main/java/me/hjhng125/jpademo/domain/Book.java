package me.hjhng125.jpademo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;
}
