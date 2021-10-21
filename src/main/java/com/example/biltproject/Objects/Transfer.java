package com.example.biltproject.Objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="amount")
    private int amount;

    @Column(name="user")
    private String user;

    @Column(name="date")
    private String date;

    @Column(name="cost")
    private int cost;

    @Column(name="program")
    private String program;

}
