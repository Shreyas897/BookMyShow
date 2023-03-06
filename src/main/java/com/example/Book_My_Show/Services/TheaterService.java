package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.TheaterConvertor;
import com.example.Book_My_Show.Entities.TheaterEntity;
import com.example.Book_My_Show.Entities.TheaterSeatEntity;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;



    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        if(theaterEntryDto.getName()==null||theaterEntryDto.getLocation()==null){
            throw new Exception("Name and Location cannot be null");
        }

        TheaterEntity theaterEntity= TheaterConvertor.theaterEntryToEntity(theaterEntryDto);
        List<TheaterSeatEntity>theaterSeatEntityList=createTheaterSeats(theaterEntryDto,theaterEntity);
        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater added Successfully";
    }
    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto,TheaterEntity theaterEntity){
        int noOfClassicSeats= theaterEntryDto.getClassicSeatsCount();
        int noOfPremiumSeats= theaterEntryDto.getPremiumSeatsCount();
        List<TheaterSeatEntity>theaterSeatEntityList=new ArrayList<>();
        for(int count=1;count<=noOfClassicSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder()
                    .seatType(SeatType.Classic).seatNo(count+"C").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }
        for(int count=1;count<=noOfPremiumSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder()
                    .seatType(SeatType.Premium).seatNo(count+"P").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }
       // theaterSeatRepository.saveAll(theaterSeatEntityList);
        return theaterSeatEntityList;
    }

}
