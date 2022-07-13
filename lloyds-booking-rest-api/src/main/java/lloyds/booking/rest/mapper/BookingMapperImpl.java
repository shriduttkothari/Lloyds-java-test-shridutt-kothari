package lloyds.booking.rest.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lloyds.booking.dao.model.BookingRequest;
import lloyds.booking.dao.model.Meeting;
import lloyds.booking.rest.dto.BookingRequestDTO;
import lloyds.booking.rest.dto.MeetingDTO;
import lloyds.booking.rest.dto.MeetingResponseDTO;

@Component
public class BookingMapperImpl implements BookingMapper {

	Supplier<TreeSet<BookingRequest>> bookingRequestTreeSetSupplier = () -> new TreeSet<>();
	Supplier<TreeSet<MeetingResponseDTO>> meetingResponseDTOTreeSetSupplier = () -> new TreeSet<>();
	Supplier<TreeSet<MeetingDTO>> meetingDTOTreeSetSupplier = () -> new TreeSet<>();

	@Override
	public TreeSet<BookingRequest> mapToBookingRequests(List<BookingRequestDTO> bookings) {
		return bookings.stream().map(bookingRequestDTO -> mapToBookingRequest(bookingRequestDTO))
				.collect(Collectors.toCollection(bookingRequestTreeSetSupplier));
	}

	private BookingRequest mapToBookingRequest(BookingRequestDTO bookingRequestDTO) {
		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setEmployeeId(bookingRequestDTO.getEmployeeId());
		bookingRequest.setMeetingDurationInHours(bookingRequestDTO.getMeetingDurationInHours());
		bookingRequest.setMeetingStartTime(bookingRequestDTO.getMeetingStartTime());
		bookingRequest.setRequestSubmissionTime(bookingRequestDTO.getRequestSubmissionTime());
		return bookingRequest;
	}

	@Override
	public TreeSet<MeetingResponseDTO> mapToMeetingResponseDTOs(Map<LocalDate, List<Meeting>> bookings) {
		return bookings.entrySet().stream().map(entry -> mapToMeetingResponseDTO(entry))
				.collect(Collectors.toCollection(meetingResponseDTOTreeSetSupplier));
	}

	private MeetingResponseDTO mapToMeetingResponseDTO(Entry<LocalDate, List<Meeting>> entry) {
		MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
		meetingResponseDTO.setMeetingDate(entry.getKey());

		TreeSet<MeetingDTO> meetingsList = entry.getValue().stream().map(meeting -> mapToMeetingDTO(meeting))
				.collect(Collectors.toCollection(meetingDTOTreeSetSupplier));
		meetingResponseDTO.setMeetings(meetingsList);
		return meetingResponseDTO;
	}

	private MeetingDTO mapToMeetingDTO(Meeting meeting) {
		MeetingDTO meetingDTO = new MeetingDTO();
		meetingDTO.setEmployeeId(meeting.getEmployeeId());
		meetingDTO.setMeetingStartHours(meeting.getMeetingStartHours());
		meetingDTO.setMeetingEndHours(meeting.getMeetingEndHours());
		return meetingDTO;
	}

}
