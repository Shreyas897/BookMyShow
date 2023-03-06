package com.example.Book_My_Show.EntryDtos;

import com.example.Book_My_Show.Enums.ShowType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class ShowEntryDto {
    LocalDate localDate;
    LocalTime localTime;
    private ShowType showType;
    private int movieId;
    private int theaterId;
    private int classicSeatPrice;
    private int premiumSeatPrice;
}
