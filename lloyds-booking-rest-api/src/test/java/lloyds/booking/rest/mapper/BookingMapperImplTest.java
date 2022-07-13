package lloyds.booking.rest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.rest.dto.BookingRequestDTO;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class BookingMapperImplTest {

	@InjectMocks
	private BookingMapperImpl classUnderTest;

	@Test
	@SneakyThrows
	public void test_empty_bookings_returns_empty_booking_request() {

		List<BookingRequestDTO> bookings = new ArrayList<>();

		TreeSet<BookingRequest> actual = classUnderTest.mapToBookingRequests(bookings);

		assertThat(actual).isEmpty();
	}
	
	@Test
	@SneakyThrows
	public void test_non_empty_bookings_returns_non_empty_booking_request_with_correct_mapping_of_request_submission_time() {

		List<BookingRequestDTO> bookings = new ArrayList<>();
		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		bookingRequestDTO.setRequestSubmissionTime(LocalDateTime.of(2022, 12, 15, 13, 20));
		bookings.add(bookingRequestDTO);
		
		TreeSet<BookingRequest> actual = classUnderTest.mapToBookingRequests(bookings);

		assertThat(actual.size()).isEqualTo(1);
		assertThat(actual.stream().findFirst().get().getRequestSubmissionTime()).isEqualTo(LocalDateTime.of(2022, 12, 15, 13, 20));
	}

}
