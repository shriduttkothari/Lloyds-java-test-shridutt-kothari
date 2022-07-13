package lloyds.booking.rest.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import lloyds.booking.rest.dto.BookingRequestDTO;
import lloyds.booking.rest.dto.RequestDTO;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class BookingRequestValidatorImplTest {

	@InjectMocks
	private BookingRequestValidatorImpl classUnderTest;

	@Test
	@SneakyThrows
	public void test_empty_request_dto_returns_emoty_dequest_dto() {

		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setBookingRequests(new ArrayList<>());

		RequestDTO actual = classUnderTest.removeInvalidBookingRequests(requestDTO);

		assertThat(actual).isEqualTo(requestDTO);
	}

	@Test
	@SneakyThrows
	public void test_invalid_booking_is_removed() {

		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setCompanyOfficeHoursStart(LocalTime.of(9, 0));
		requestDTO.setCompanyOfficeHoursEnd(LocalTime.of(17, 0));

		List<BookingRequestDTO> bookingRequestList = new ArrayList<>();
		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		bookingRequestDTO.setMeetingStartTime(LocalDateTime.of(2022, 12, 15, 18, 0));
		bookingRequestDTO.setMeetingDurationInHours(1);

		bookingRequestList.add(bookingRequestDTO);
		requestDTO.setBookingRequests(bookingRequestList);

		RequestDTO actual = classUnderTest.removeInvalidBookingRequests(requestDTO);

		assertThat(actual.getBookingRequests()).isEmpty();
	}
	
	@Test
	@SneakyThrows
	public void test_valid_booking_is_not_removed() {

		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setCompanyOfficeHoursStart(LocalTime.of(9, 0));
		requestDTO.setCompanyOfficeHoursEnd(LocalTime.of(17, 0));

		List<BookingRequestDTO> bookingRequestList = new ArrayList<>();
		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		bookingRequestDTO.setMeetingStartTime(LocalDateTime.of(2022, 12, 15, 16, 0));
		bookingRequestDTO.setMeetingDurationInHours(1);

		bookingRequestList.add(bookingRequestDTO);
		requestDTO.setBookingRequests(bookingRequestList);

		RequestDTO actual = classUnderTest.removeInvalidBookingRequests(requestDTO);

		assertThat(actual.getBookingRequests().size()).isEqualTo(1);
	}

}
