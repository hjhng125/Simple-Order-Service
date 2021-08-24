package me.hjhng125.jpademo.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
/*
 * super class 인자까지 포함하는 builder를 만들기 위함 super 클래스에도 포함해야한다.
 *
 * * Builder를 사용할 경우 Package 접근제한자 이상인 모든 인자를 받는 생성자가 필수이지만
 * SuperBuilder는 자기 자신의 Builder를 인자로 받는 생성자를 자동으로 생성하며 이를 통해 필드를 초기화 하기에 필요없다.
 *
 * 다른 방법으로 슈퍼 클래스의 인자까지 받는 생성자를 선언하고, 해당 생성자에 @Builder를 붙이는 방법도 있다.
*/
@SuperBuilder
@DiscriminatorValue("B")
@NoArgsConstructor(access = PROTECTED)
public class Book extends Item {

    private String author;
    private String isbn;
}
