package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity bookTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try{
            String response=ticketService.bookTicket(ticketEntryDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
