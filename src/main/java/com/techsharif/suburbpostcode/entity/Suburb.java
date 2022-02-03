package com.techsharif.suburbpostcode.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Suburb extends BaseEntity{
    @NotNull
    private String suburb;

    @NotNull
    private Long postcode;
}
