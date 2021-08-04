package com.example.restapi.Dao.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private String description;
    private String img;
    private int category;
    private int user;
   // private String data;
    private String location;

}
