package it.unisalento.tripplanner.iservice;

import it.unisalento.tripplanner.dto.Trip;
import org.springframework.data.domain.Page;

public interface ITripService {
    Trip saveTrip(Trip trip);
    Page<Trip> findAll(Integer pageNumber, Integer pageSize);
    Trip updateTrip(Trip trip);
}
