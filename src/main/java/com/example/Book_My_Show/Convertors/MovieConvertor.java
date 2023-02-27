package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.MovieEntity;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;

public class MovieConvertor {
    public static MovieEntity movieDtoToEntity(MovieEntryDto movieEntryDto) {
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).length(movieEntryDto.getLength())
                .genre(movieEntryDto.getGenre()).rating(movieEntryDto.getRating()).language(movieEntryDto.getLanguage()).build();
        return movieEntity;
    }
}
