package lloyds.booking.dao.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingRequest implements Comparable<BookingRequest> {

	private String employeeId;
	private Integer meetingDurationInHours;
	private LocalDateTime requestSubmissionTime;
	private LocalDateTime meetingStartTime;

	@Override
	public int compareTo(BookingRequest another) {
		if (this.requestSubmissionTime.isAfter(another.getRequestSubmissionTime())) {
			return 1;
		} else if (this.requestSubmissionTime.isBefore(another.getRequestSubmissionTime())) {
			return -1;
		}
		return 0;
	}
}
