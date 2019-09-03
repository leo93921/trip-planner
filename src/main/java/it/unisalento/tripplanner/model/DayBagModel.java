package it.unisalento.tripplanner.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class DayBagModel {

    @Id
    private String id;
    private Date day;
    private List<TripStopModel> tripStops;

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

    public List<TripStopModel> getTripStops() {
        return tripStops;
    }

    public void setTripStops(List<TripStopModel> tripStops) {
        this.tripStops = tripStops;
    }

}
