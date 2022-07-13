package lloyds.booking.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lloyds.booking.rest.dto.BookingRequestDTO;
import lloyds.booking.rest.dto.MeetingDTO;
import lloyds.booking.rest.dto.MeetingResponseDTO;
import lloyds.booking.rest.dto.RequestDTO;
import lloyds.booking.rest.service.BookingService;
import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService bookingService;

	@Test
	@SneakyThrows
	public void test_bookings_endpoint() {

		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setCompanyOfficeHoursStart(LocalTime.of(9, 00));
		requestDTO.setCompanyOfficeHoursEnd(LocalTime.of(17, 30));
		List<BookingRequestDTO> bookingRequestDTOList = new ArrayList<>();
		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		bookingRequestDTO.setEmployeeId("EMP001");
		bookingRequestDTO.setMeetingDurationInHours(2);
		bookingRequestDTO.setRequestSubmissionTime(LocalDateTime.of(2016, 7, 18, 10, 17, 6));
		bookingRequestDTO.setMeetingStartTime(LocalDateTime.of(2016, 7, 21, 9, 0));

		bookingRequestDTOList.add(bookingRequestDTO);
		requestDTO.setBookingRequests(bookingRequestDTOList);

		MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
		meetingResponseDTO.setMeetingDate(LocalDate.of(2011, 3, 21));
		TreeSet<MeetingDTO> meetings = new TreeSet<>();
		MeetingDTO meetingDTO = new MeetingDTO();
		meetingDTO.setEmployeeId("EMP001");
		meetingDTO.setMeetingStartHours(LocalTime.of(9, 00));
		meetingDTO.setMeetingEndHours(LocalTime.of(11, 00));

		meetings.add(meetingDTO);
		meetingResponseDTO.setMeetings(meetings);
		TreeSet<MeetingResponseDTO> meetingResponseDTOSet = new TreeSet<>();
		meetingResponseDTOSet.add(meetingResponseDTO);

		when(bookingService.createBookings(requestDTO)).thenReturn(meetingResponseDTOSet);

		String requestPayload = convertObjectToJsonString(requestDTO);
		RequestBuilder operation = post("/bookings").content(requestPayload)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

		ResultActions resultActions = this.mockMvc.perform(operation);

		resultActions.andDo(print());
		resultActions.andExpect(status().is(201));
		resultActions.andExpect(jsonPath("$.[0].meetingDate").value("2011-03-21"));
		resultActions.andExpect(jsonPath("$.[0].meetings.[0].meetingStartHours").value("09:00"));
		resultActions.andExpect(jsonPath("$.[0].meetings.[0].meetingEndHours").value("11:00"));
		resultActions.andExpect(jsonPath("$.[0].meetings.[0].employeeId").value("EMP001"));
	}

	@SneakyThrows
	private String convertObjectToJsonString(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(object);
	}
	
}
