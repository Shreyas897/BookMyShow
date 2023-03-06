package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.ShowConvertor;
import com.example.Book_My_Show.Entities.*;
import com.example.Book_My_Show.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.ShowSeatRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(ShowEntryDto showEntryDto){
        ShowEntity showEntity= ShowConvertor.dtoToEntity(showEntryDto);
        MovieEntity movie=movieRepository.findById(showEntryDto.getMovieId()).get();
        TheaterEntity theater=theaterRepository.findById(showEntryDto.getTheaterId()).get();
        showEntity.setMovieEntity(movie);
        showEntity.setTheaterEntity(theater);

        List<ShowSeatEntity> showSeatEntityList=createShowSeat(showEntryDto,showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);

        showRepository.save(showEntity);

        movie.getShowEntityList().add(showEntity);
        movieRepository.save(movie);

        theater.getShowEntityList().add(showEntity);
        theaterRepository.save(theater);

        return "Show has been added successfully";

    }
    private List<ShowSeatEntity> createShowSeat(ShowEntryDto showEntryDto,ShowEntity showEntity){

        List<TheaterSeatEntity>theaterSeatEntityList=showEntity.getTheaterEntity().getTheaterSeatEntityList();

        List<ShowSeatEntity>showSeatEntityList=new ArrayList<>();
        for(TheaterSeatEntity theaterSeatEntity: theaterSeatEntityList){
            ShowSeatEntity showSeatEntity=ShowSeatEntity.builder()
                    .isBooked(false).seatType(theaterSeatEntity.getSeatType())
                    .showEntity(showEntity).seatNo(theaterSeatEntity.getSeatNo())
                    .build();
            if(showSeatEntity.getSeatType().equals(SeatType.Classic)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }else{
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }
}
