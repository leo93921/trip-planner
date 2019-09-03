package it.unisalento.tripplanner.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class ExperienceModel {

    @Id
    private String id;
    private String title;
    private Float maxBudget;
    private Integer budgetLevel;
    private Date creationDate;
    private Date updateDate;
    private Date deleteDate;
    private LocationModel startPoint;
    private LocationModel endPoint;
    private String userId;

    public ExperienceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Float maxBudget) {
        this.maxBudget = maxBudget;
    }

    public Integer getBudgetLevel() {
        return budgetLevel;
    }

    public void setBudgetLevel(Integer budgetLevel) {
        this.budgetLevel = budgetLevel;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public LocationModel getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LocationModel startPoint) {
        this.startPoint = startPoint;
    }

    public LocationModel getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LocationModel endPoint) {
        this.endPoint = endPoint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
