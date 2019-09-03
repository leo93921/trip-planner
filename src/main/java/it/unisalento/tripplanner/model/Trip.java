package it.unisalento.tripplanner.model;

import java.util.List;

public class Trip extends Experience {

    private List<DayBag> dayBags;

    public List<DayBag> getDayBags() {
        return dayBags;
    }

    public void setDayBags(List<DayBag> dayBags) {
        this.dayBags = dayBags;
    }
}
