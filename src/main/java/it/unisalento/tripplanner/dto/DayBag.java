package it.unisalento.tripplanner.dto;

import java.util.Date;
import java.util.List;

public class DayBag {

    private String id;
    private Date day;
    private List<TripStop> tripStops;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public List<TripStop> getTripStops() {
        return tripStops;
    }

    public void setTripStops(List<TripStop> tripStops) {
        this.tripStops = tripStops;
    }
}
