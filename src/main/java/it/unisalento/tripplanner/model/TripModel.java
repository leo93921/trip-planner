package it.unisalento.tripplanner.model;

import java.util.List;

public class TripModel extends ExperienceModel {

    private List<DayBagModel> dayBags;

    public List<DayBagModel> getDayBags() {
        return dayBags;
    }

    public void setDayBags(List<DayBagModel> dayBags) {
        this.dayBags = dayBags;
    }
}
