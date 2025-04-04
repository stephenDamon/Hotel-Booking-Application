package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Bookings;
import com.airbnb.payload.BookingDto;

public interface BookingService {
    BookingDto createBooking(long propertyId, BookingDto bookingDto, AppUser user);
}
