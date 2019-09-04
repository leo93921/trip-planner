package it.unisalento.tripplanner.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "itinerary")
public class ItineraryModel extends ExperienceModel {

    private List<TripStopModel> stops;
    private String description;

    public List<TripStopModel> getStops() {
        return stops;
    }

    public void setStops(List<TripStopModel> stops) {
        this.stops = stops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
