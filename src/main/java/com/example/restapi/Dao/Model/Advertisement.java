package com.example.restapi.Dao.Model;

import com.example.restapi.Dao.Repository.CommentRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private String description;
    private String img;
    private int category;
    private int user;
    private Date data;
    private String location;



}
