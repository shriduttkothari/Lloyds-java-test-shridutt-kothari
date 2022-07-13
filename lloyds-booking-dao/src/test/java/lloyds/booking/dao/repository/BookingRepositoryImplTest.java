package lloyds.booking.dao.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class BookingRepositoryImplTest {

	@InjectMocks
	private BookingRepositoryImpl classUnderTest;

	@Test
	@SneakyThrows
	public void test_empty_booking_requests_returns_empty_meeting_by_day() {
		TreeSet<BookingRequest> bookingRequests = new TreeSet<>();
		Map<LocalDate, List<Meeting>> actual = classUnderTest.createBookings(bookingRequests);

		assertThat(actual).isEmpty();
	}
	
	@Test
	@SneakyThrows
	public void test_booking_requests_returns_meeting_by_day_with_successful_boooking() {
		TreeSet<BookingRequest> bookingRequests = new TreeSet<>();
		
		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setEmployeeId("Emp001");
		bookingRequest.setMeetingDurationInHours(2);
		bookingRequest.setRequestSubmissionTime(LocalDateTime.now());
		bookingRequest.setMeetingStartTime(LocalDateTime.now());
		bookingRequests.add(bookingRequest );
		
		Map<LocalDate, List<Meeting>> actual = classUnderTest.createBookings(bookingRequests);

		assertThat(actual.entrySet().size()).isEqualTo(1);
		
		assertThat(actual.entrySet().stream().findFirst().get().getKey()).isEqualTo(LocalDate.now());
		assertThat(actual.entrySet().stream().findFirst().get().getValue().get(0).getEmployeeId()).isEqualTo("Emp001");
	}

}
