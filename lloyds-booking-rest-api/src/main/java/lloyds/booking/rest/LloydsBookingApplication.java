package lloyds.booking.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "lloyds.booking.rest", "lloyds.booking.dao" })
public class LloydsBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LloydsBookingApplication.class, args);
	}

}
