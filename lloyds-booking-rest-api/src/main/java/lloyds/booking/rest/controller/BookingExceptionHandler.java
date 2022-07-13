package lloyds.booking.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lloyds.booking.rest.dto.ErrorResponseDTO;
import lloyds.booking.rest.exception.CouldNotMakeBookingException;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO("Internal Server Error");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CouldNotMakeBookingException.class)
	public final ResponseEntity<ErrorResponseDTO> handleCouldNotMakeBookingException(CouldNotMakeBookingException ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO("Could not make booking!!");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
	}
}
