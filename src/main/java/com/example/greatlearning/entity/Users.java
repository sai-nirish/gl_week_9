package com.example.greatlearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users{

    @Id
    @Column
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean enabled;

    @Column
    private String role;

}
