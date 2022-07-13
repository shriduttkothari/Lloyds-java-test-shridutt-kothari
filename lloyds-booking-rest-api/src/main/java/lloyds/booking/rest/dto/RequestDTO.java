package lloyds.booking.rest.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO {

	@JsonFormat(pattern = "HHmm")
	private LocalTime companyOfficeHoursStart;
	@JsonFormat(pattern = "HHmm")
	private LocalTime companyOfficeHoursEnd;
	private List<BookingRequestDTO> bookingRequests = new ArrayList<>();
}
