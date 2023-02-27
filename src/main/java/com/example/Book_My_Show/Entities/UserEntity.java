package com.example.Book_My_Show.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private int age;
    @NonNull
    @Column(unique = true)
    private String mobNo;
    private String address;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList=new ArrayList<>();
}
