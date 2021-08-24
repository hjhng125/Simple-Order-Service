package me.hjhng125.jpademo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@DiscriminatorValue("A")
@NoArgsConstructor
@AllArgsConstructor
public class Album extends Item {

    private String artist;
    private String etc;
}
