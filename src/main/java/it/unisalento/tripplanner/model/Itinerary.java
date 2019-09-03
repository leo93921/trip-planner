package it.unisalento.tripplanner.model;

import java.util.List;

public class Itinerary extends Experience {

    private List<TripStop> stops;
    private String description;

    public List<TripStop> getStops() {
        return stops;
    }

    public void setStops(List<TripStop> stops) {
        this.stops = stops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
