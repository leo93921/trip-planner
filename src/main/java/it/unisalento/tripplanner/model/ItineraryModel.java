package it.unisalento.tripplanner.model;

import java.util.List;

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
