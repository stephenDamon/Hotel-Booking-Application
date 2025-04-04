package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Bookings;
import com.airbnb.entity.Property;
import com.airbnb.payload.BookingDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.repository.BookingsRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingsRepository bookingsRepository;
    private PropertyRepository propertyRepository;
    private AppUserRepository appUserRepository;

    public BookingServiceImpl(BookingsRepository bookingsRepository, PropertyRepository propertyRepository, AppUserRepository appUserRepository) {
        this.bookingsRepository = bookingsRepository;
        this.propertyRepository = propertyRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public BookingDto createBooking(long propertyId, BookingDto bookingDto, AppUser user) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Optional<AppUser> byUsername = appUserRepository.findByUsername(user.getUsername());
        Property property= null;
        if (byId.isPresent()){
             property = byId.get();
             bookingDto.setProperty(property);
             bookingDto.setAppUser(user);
             bookingDto.setPrice((double) (property.getNightlyPrice()*bookingDto.getTotalNights())+
                     property.getNightlyPrice()*bookingDto.getTotalNights()*0.18);
            Bookings savedData = bookingsRepository.save(dtoToEntity(bookingDto));
            return entityToDto(savedData);
        }



        return null;
    }


    Bookings dtoToEntity(BookingDto bookingDto){
        Bookings bookings = new Bookings();
        bookings.setName(bookingDto.getName());
        bookings.setEmail(bookingDto.getEmail());
        bookings.setPrice(bookingDto.getPrice());
        bookings.setMobile(bookingDto.getMobile());
        bookings.setTotalNights(bookingDto.getTotalNights());
        bookings.setAppUser(bookingDto.getAppUser());
        bookings.setProperty(bookingDto.getProperty());
        return bookings;
    }

    BookingDto entityToDto(Bookings bookings){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(bookings.getId());
        bookingDto.setName(bookings.getName());
        bookingDto.setEmail(bookings.getEmail());
        bookingDto.setPrice(bookings.getPrice());
        bookingDto.setMobile(bookings.getMobile());
        bookingDto.setTotalNights(bookings.getTotalNights());
        bookingDto.setAppUser(bookings.getAppUser());
        bookingDto.setProperty(bookings.getProperty());
        return bookingDto;
    }
}
