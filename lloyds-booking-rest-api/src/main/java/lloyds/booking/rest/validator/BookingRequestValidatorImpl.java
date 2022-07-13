package lloyds.booking.rest.validator;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lloyds.booking.rest.dto.BookingRequestDTO;
import lloyds.booking.rest.dto.RequestDTO;

@Component
public class BookingRequestValidatorImpl implements BookingRequestValidator {

	public RequestDTO removeInvalidBookingRequests(RequestDTO requestDTO) {
		List<BookingRequestDTO> inValidBookingRequests = requestDTO.getBookingRequests().stream()
				.filter(bookingRequestDTO -> !isValidMeetingTime(requestDTO.getCompanyOfficeHoursStart(),
						requestDTO.getCompanyOfficeHoursEnd(), bookingRequestDTO))
				.collect(Collectors.toList());

		if (!inValidBookingRequests.isEmpty()) {
			inValidBookingRequests.forEach(invalidBookingRequestDTO -> requestDTO.getBookingRequests().remove(invalidBookingRequestDTO));
		}
		return requestDTO;
	}

	private boolean isValidMeetingTime(LocalTime companyOfficeHoursStart, LocalTime companyOfficeHoursEnd,
			BookingRequestDTO bookingRequestDTO) {
		if (bookingRequestDTO.getMeetingStartTime().toLocalTime().isBefore(companyOfficeHoursStart)) {
			return false;
		} else if (bookingRequestDTO.getMeetingStartTime().toLocalTime().isAfter(companyOfficeHoursEnd)) {
			return false;
		} else if (bookingRequestDTO.getMeetingStartTime().toLocalTime()
				.plusHours(bookingRequestDTO.getMeetingDurationInHours()).isAfter(companyOfficeHoursEnd)) {
			return false;
		}
		return true;
	}

}
