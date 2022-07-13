package lloyds.booking.dao.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Meeting {

	private LocalTime meetingStartHours;
	private LocalTime meetingEndHours;
	private String employeeId;
}
