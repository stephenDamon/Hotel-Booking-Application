package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Bookings;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.BookingDto;
import com.airbnb.service.BookingService;
import com.airbnb.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/airbnb/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    private PDFService pdfService;

    public BookingController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<BookingDto> createBooking(
            @RequestParam long propertyId,
            @RequestBody BookingDto bookingDto,
            @AuthenticationPrincipal AppUser user
            ){
        BookingDto booking = bookingService.createBooking(propertyId, bookingDto, user);
        pdfService.generatePDF(booking.getId()+"-"+booking.getName()+"-confirmation of booking ",booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);


    }
}
