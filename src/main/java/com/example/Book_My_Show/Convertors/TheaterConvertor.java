package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.TheaterEntity;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor



public class TheaterConvertor {
    public static TheaterEntity theaterEntryToEntity(TheaterEntryDto theaterEntryDto){
        TheaterEntity theaterEntity=TheaterEntity.builder().name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation()).build();
        return theaterEntity;
    }
}
