package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Entities.ShowEntity;
import com.example.Book_My_Show.Entities.ShowSeatEntity;
import com.example.Book_My_Show.Entities.TicketEntity;
import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show.Repository.*;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    JavaMailSender javaMailSender;
    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception{
        TicketEntity ticketEntity=new TicketEntity();
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();
        boolean isValidRequest=checkValidityOfSeats(ticketEntryDto,showEntity);
        if(isValidRequest==false){
            throw new Exception("Seats not available");
        }
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
        List<ShowSeatEntity>showSeatEntityList=showEntity.getShowSeatEntityList();
        int totalPrice=0;
        String bookedSeats="";
        for(ShowSeatEntity showSeatEntity:showSeatEntityList){
            if(requestedSeats.contains(showSeatEntity.getSeatNo())) {
                totalPrice += showSeatEntity.getPrice();
                showSeatEntity.setBookedOn(new Date());
                showSeatEntity.setBooked(true);
                if (bookedSeats.length() == 0) {
                    bookedSeats = showSeatEntity.getSeatNo();
                } else {
                    bookedSeats = bookedSeats + ", " + showSeatEntity.getSeatNo();
                }
            }

        }
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());
        ticketEntity.setTotalAmount(totalPrice);
        ticketEntity.setBookedSeats(bookedSeats);

        ticketEntity.setShowEntity(showEntity);
        ticketEntity.setUserEntity(userEntity);

        ticketRepository.save(ticketEntity);

        userEntity.getTicketEntityList().add(ticketEntity);

        showEntity.getTicketEntityList().add(ticketEntity);
        userRepository.save(userEntity);
        showRepository.save(showEntity);

        String body = "Hi this is to confirm your booking for seat No "+bookedSeats +" for the movie : " + ticketEntity.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("shreyasshetty897.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);



        return "Tickets Booked";

    }
    private boolean checkValidityOfSeats(TicketEntryDto ticketEntryDto,ShowEntity showEntity){
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
        List<ShowSeatEntity>showSeatEntityList=showEntity.getShowSeatEntityList();
        for(ShowSeatEntity showSeatEntity:showSeatEntityList){
            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                if(showSeatEntity.isBooked()==true){
                    return false;
                }
            }
        }
        return true;
    }
}
