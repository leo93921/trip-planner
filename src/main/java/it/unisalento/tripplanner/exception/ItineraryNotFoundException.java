package it.unisalento.tripplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ExceptionMessage.ITINERARY_NOT_FOUND_MESSAGE)
public class ItineraryNotFoundException extends RuntimeException {
}
