package lloyds.booking.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;
import lloyds.booking.dao.repository.BookingRepository;
import lloyds.booking.rest.dto.MeetingResponseDTO;
import lloyds.booking.rest.dto.RequestDTO;
import lloyds.booking.rest.mapper.BookingMapper;
import lloyds.booking.rest.validator.BookingRequestValidator;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

	@InjectMocks
	private BookingServiceImpl classUnderTest;

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private BookingMapper bookingMapper;

	@Mock
	private BookingRequestValidator bookingRequestValidator;

	@Test
	@SneakyThrows
	public void test_all_classes_arewired_up_together_correctly() {

		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setBookingRequests(new ArrayList<>());
		when(bookingRequestValidator.removeInvalidBookingRequests(requestDTO)).thenReturn(requestDTO);

		TreeSet<BookingRequest> bookingRequests = new TreeSet<>();
		when(bookingMapper.mapToBookingRequests(requestDTO.getBookingRequests())).thenReturn(bookingRequests);

		Map<LocalDate, List<Meeting>> bookings = new HashMap<>();
		when(bookingRepository.createBookings(bookingRequests)).thenReturn(bookings);

		TreeSet<MeetingResponseDTO> meetingResponseDTOSet = new TreeSet<>();
		when(bookingMapper.mapToMeetingResponseDTOs(bookings)).thenReturn(meetingResponseDTOSet);

		TreeSet<MeetingResponseDTO> actual = classUnderTest.createBookings(requestDTO);

		assertThat(actual).isEqualTo(meetingResponseDTOSet);
	}

}
