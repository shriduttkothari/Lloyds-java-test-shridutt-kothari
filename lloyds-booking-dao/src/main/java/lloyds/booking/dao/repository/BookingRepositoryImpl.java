package lloyds.booking.dao.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

	private final Map<LocalDate, List<Meeting>> meetingByDay = new TreeMap<>();

	@Override
	public Map<LocalDate, List<Meeting>> createBookings(TreeSet<BookingRequest> bookingRequests) {
		bookingRequests.stream().forEach(bookingRequest -> performBookingIfPossible(bookingRequest));
		return meetingByDay;
	}

	private void performBookingIfPossible(BookingRequest bookingRequest) {
		LocalDate localDate = bookingRequest.getMeetingStartTime().toLocalDate();
		if (!meetingByDay.keySet().contains(localDate)) {
			List<Meeting> meetings = new LinkedList<>();
			Meeting meeting = createMeetingFromBooking(bookingRequest);
			meetings.add(meeting);
			meetingByDay.put(localDate, meetings);
		} else {
			List<Meeting> bookedMeetings = meetingByDay.get(localDate);
			boolean isTimeSlotAvailable = isTimeSlotAvailable(bookedMeetings, bookingRequest);
			if (isTimeSlotAvailable) {
				Meeting meeting = createMeetingFromBooking(bookingRequest);
				bookedMeetings.add(meeting);
				meetingByDay.put(localDate, bookedMeetings);
			}
		}
	}

	private boolean isTimeSlotAvailable(List<Meeting> bookedMeetings, BookingRequest bookingRequest) {
		return !bookedMeetings.stream().filter(bookedMeeting -> isOverlappng(bookedMeeting, bookingRequest)).findAny().isPresent();
	}

	private boolean isOverlappng(Meeting bookedMeeting, BookingRequest bookingRequest) {
		LocalTime bookingStartTime = bookingRequest.getMeetingStartTime().toLocalTime();
		LocalTime bookingEndTime = bookingRequest.getMeetingStartTime().toLocalTime().plusHours(bookingRequest.getMeetingDurationInHours());
		
		if(bookingStartTime.equals(bookedMeeting.getMeetingStartHours()) || bookingEndTime.equals(bookedMeeting.getMeetingEndHours())) {
			//(Start and Start are overlapping) or (End  and end are overlapping)
			return true;
		} else if (bookingStartTime.isAfter(bookedMeeting.getMeetingStartHours()) && bookingStartTime.isBefore(bookedMeeting.getMeetingEndHours())) {
			//(Booking Start is between Meeting Start And End)
			return true;
		} else if (bookingEndTime.isAfter(bookedMeeting.getMeetingStartHours()) && bookingEndTime.isBefore(bookedMeeting.getMeetingEndHours())) {
			//(Booking End is between Meeting Start And End)
			return true;
		} else if (bookingStartTime.isBefore(bookedMeeting.getMeetingStartHours()) && bookingEndTime.isAfter(bookedMeeting.getMeetingStartHours())) {
			//(Booking start is before Meeting Start But Booking End is after meeting end)
			return true;
		} 
		return false;
	}

	private Meeting createMeetingFromBooking(BookingRequest bookingRequest) {
		Meeting meeting = new Meeting();
		meeting.setEmployeeId(bookingRequest.getEmployeeId());
		meeting.setMeetingStartHours(bookingRequest.getMeetingStartTime().toLocalTime());
		meeting.setMeetingEndHours(bookingRequest.getMeetingStartTime().toLocalTime()
				.plusHours(bookingRequest.getMeetingDurationInHours()));
		return meeting;
	}

}
