package model;

import domain.Booking;
import domain.Movie;
import service.MovieBookingService;
import service.PaymentService;
import service.PaymentType;

import java.util.List;

public class MovieBookingModel {

    private MovieBookingService movieBookingService;
    private PaymentService paymentService;

    public List<Movie> retrieveMovies() {
        return movieBookingService.retrieveAllMovies();
    }

    public boolean isBookable(Booking b) {
        return isBookable(b);
    }

    public boolean isBookableTogether(List<Booking> bookings) {
        return movieBookingService.checkBookableTogether(bookings);
    }

    public int bookWithPay(List<Booking> bookings, int point, PaymentType paymentType) {
        if (movieBookingService.checkBookableTogether(bookings)) {
            throw new IllegalArgumentException("Specified bookings cannot be booked together");
        }
        int payed = paymentService.handlePayment(movieBookingService.getTotalPrice(bookings), point, paymentType);
        movieBookingService.bookMovie(bookings);

        return payed;
    }

    public int getTotalPrice(List<Booking> bookings) {
        return movieBookingService.getTotalPrice(bookings);
    }
}
