package lloyds.booking.rest.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MeetingDTO implements Comparable<MeetingDTO> {

	@JsonFormat(pattern = "HH:mm")
	private LocalTime meetingStartHours;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime meetingEndHours;
	private String employeeId;
	
	@Override
	public int compareTo(MeetingDTO another) {
		if (this.meetingStartHours.isAfter(another.getMeetingStartHours())) {
			return 1;
		} else if (this.meetingStartHours.isBefore(another.getMeetingStartHours())) {
			return -1;
		}
		return 0;
	}
}
