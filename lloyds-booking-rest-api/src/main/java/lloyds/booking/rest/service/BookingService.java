package lloyds.booking.rest.service;

import java.util.TreeSet;

import lloyds.booking.rest.dto.MeetingResponseDTO;
import lloyds.booking.rest.dto.RequestDTO;

public interface BookingService {

	public TreeSet<MeetingResponseDTO> createBookings(RequestDTO requestDTO);

}
