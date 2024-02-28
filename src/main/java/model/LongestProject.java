package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Клас, що представляє найтриваліший проєкт з інформацією про його ідентифікатор, дати початку та завершення,
 * тривалість у місяцях та ідентифікатор клієнта.
 */
@Getter
@Setter
public class LongestProject {
    private int id;
    private Date startDate;
    private Date finishDate;
    private int durationInMonths;
    private int clientId;

    public LongestProject(int id, Date startDate, Date finishDate, int durationInMonths, int clientId) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.durationInMonths = durationInMonths;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "model.LongestProject: \n" +
                "Id: " + id +
                ", Start: " + startDate +
                ", Finish: " + finishDate +
                ", Duration in Months: " + durationInMonths +
                ", client id: " + clientId;
    }
}