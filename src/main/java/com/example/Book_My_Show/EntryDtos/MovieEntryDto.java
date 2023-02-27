package com.example.Book_My_Show.EntryDtos;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntryDto {

    private String movieName;

    private Genre genre;
    private double rating;
    private int length;

    private Language language;
}
