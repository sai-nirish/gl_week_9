package com.example.greatlearning.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer price;

}
