package it.unisalento.tripplanner.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trip")
public class TripModel extends ExperienceModel {

    private List<DayBagModel> dayBags;

    public List<DayBagModel> getDayBags() {
        return dayBags;
    }

    public void setDayBags(List<DayBagModel> dayBags) {
        this.dayBags = dayBags;
    }
}
