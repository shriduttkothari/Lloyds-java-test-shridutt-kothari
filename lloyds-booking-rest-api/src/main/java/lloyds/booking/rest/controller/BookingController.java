package lloyds.booking.rest.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lloyds.booking.rest.dto.MeetingResponseDTO;
import lloyds.booking.rest.dto.RequestDTO;
import lloyds.booking.rest.service.BookingService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping(path = "/bookings", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Set<MeetingResponseDTO>> createBookings(@RequestBody RequestDTO requestDTO) {
		Set<MeetingResponseDTO> meetingResponseDTO = bookingService.createBookings(requestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(meetingResponseDTO);
	}

}
