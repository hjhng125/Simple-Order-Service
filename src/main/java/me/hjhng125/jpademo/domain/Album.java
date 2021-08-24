package me.hjhng125.jpademo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;
}
