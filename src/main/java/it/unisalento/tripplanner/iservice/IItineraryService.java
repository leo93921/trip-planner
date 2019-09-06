package it.unisalento.tripplanner.iservice;

import it.unisalento.tripplanner.dto.Itinerary;
import org.springframework.data.domain.Page;

public interface IItineraryService {
    Page<Itinerary> findAll(Integer page, Integer pageSize);
    Itinerary save(Itinerary itinerary);
    boolean deleteByID(String id);
    Itinerary update(Itinerary itinerary);
}
