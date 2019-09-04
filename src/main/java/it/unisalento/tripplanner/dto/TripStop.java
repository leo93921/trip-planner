package it.unisalento.tripplanner.dto;

import java.time.LocalTime;
import java.util.List;

public class TripStop {

    private String id;
    private RefType refType;
    private String refId;
    private LocalTime visitTime;
    private Integer visitOrder;
    private Location location;
    private Boolean warningPresent;
    private List<String> warningMessages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RefType getRefType() {
        return refType;
    }

    public void setRefType(RefType refType) {
        this.refType = refType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getVisitOrder() {
        return visitOrder;
    }

    public void setVisitOrder(Integer visitOrder) {
        this.visitOrder = visitOrder;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getWarningPresent() {
        return warningPresent;
    }

    public void setWarningPresent(Boolean warningPresent) {
        this.warningPresent = warningPresent;
    }

    public List<String> getWarningMessages() {
        return warningMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }
}
