package lloyds.booking.dao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;

public interface BookingRepository {

	Map<LocalDate, List<Meeting>> createBookings(TreeSet<BookingRequest> bookingRequests);

}
