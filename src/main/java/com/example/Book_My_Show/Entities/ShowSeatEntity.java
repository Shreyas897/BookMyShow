package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="showSeat")
@Data
@NoArgsConstructor
public class ShowSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isBooked;
    private int price;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Date bookedOn;

    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
