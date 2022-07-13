package lloyds.booking.rest.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;
import lloyds.booking.rest.dto.BookingRequestDTO;
import lloyds.booking.rest.dto.MeetingResponseDTO;

public interface BookingMapper {

	TreeSet<BookingRequest> mapToBookingRequests(List<BookingRequestDTO> bookings);

	TreeSet<MeetingResponseDTO> mapToMeetingResponseDTOs(Map<LocalDate, List<Meeting>> bookings);

}
