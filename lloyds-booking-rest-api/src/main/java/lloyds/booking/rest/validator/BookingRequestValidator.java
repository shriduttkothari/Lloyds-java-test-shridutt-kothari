package lloyds.booking.rest.validator;

import lloyds.booking.rest.dto.RequestDTO;

public interface BookingRequestValidator {

	RequestDTO removeInvalidBookingRequests(RequestDTO requestDTO);

}
