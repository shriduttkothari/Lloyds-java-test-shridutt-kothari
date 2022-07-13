package lloyds.booking.rest.dto;

import java.time.LocalDate;
import java.util.TreeSet;

import lombok.Data;

@Data
public class MeetingResponseDTO implements Comparable<MeetingResponseDTO> {

	private LocalDate meetingDate;
	private TreeSet<MeetingDTO> meetings;

	@Override
	public int compareTo(MeetingResponseDTO another) {
		if (this.meetingDate.isAfter(another.getMeetingDate())) {
			return 1;
		} else if (this.meetingDate.isBefore(another.getMeetingDate())) {
			return -1;
		}
		return 0;
	}
}
