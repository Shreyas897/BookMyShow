package com.example.Book_My_Show.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmount;
    private String theaterName;
    private String ticketId= UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;


    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

}
