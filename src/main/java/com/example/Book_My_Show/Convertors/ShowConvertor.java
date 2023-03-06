package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.ShowEntity;
import com.example.Book_My_Show.EntryDtos.ShowEntryDto;

public class ShowConvertor {

    public static ShowEntity dtoToEntity(ShowEntryDto showEntryDto){
        return ShowEntity.builder().showDate(showEntryDto.getLocalDate()).showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType())
                .build();
    }
}
