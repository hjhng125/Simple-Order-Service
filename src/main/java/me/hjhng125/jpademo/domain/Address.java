package me.hjhng125.jpademo.domain;

import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {

    private String city;

    private String street;

    private String zipCode;
}