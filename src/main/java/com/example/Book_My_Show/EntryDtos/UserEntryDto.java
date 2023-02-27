package com.example.Book_My_Show.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntryDto {
    private String name;

    private String email;
    private int age;

    private String mobNo;
    private String address;
}
