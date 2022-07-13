package lloyds.booking.rest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;
import lloyds.booking.dao.repository.BookingRepository;
import lloyds.booking.rest.dto.MeetingResponseDTO;
import lloyds.booking.rest.dto.RequestDTO;
import lloyds.booking.rest.mapper.BookingMapper;
import lloyds.booking.rest.validator.BookingRequestValidator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

	private BookingRepository bookingRepository;

	private BookingMapper bookingMapper;
	
	private BookingRequestValidator bookingRequestValidator;

	public TreeSet<MeetingResponseDTO> createBookings(RequestDTO requestDTO) {
		
		requestDTO = bookingRequestValidator.removeInvalidBookingRequests(requestDTO);
		
		requestDTO.getBookingRequests();
		TreeSet<BookingRequest> bookingRequests = bookingMapper.mapToBookingRequests(requestDTO.getBookingRequests());
		Map<LocalDate, List<Meeting>> bookings = bookingRepository.createBookings(bookingRequests);
		
		return bookingMapper.mapToMeetingResponseDTOs(bookings);
	}

}
