package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;

public class UserConvertor {
    public static UserEntity userDtoToEntity(UserEntryDto userEntryDto){
        UserEntity userEntity=UserEntity.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).email(userEntryDto.getEmail())
                .mobNo(userEntryDto.getMobNo()).address(userEntryDto.getAddress()).build();
        return userEntity;
    }
}
