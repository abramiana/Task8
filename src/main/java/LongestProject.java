import java.util.Date;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "LongestProject: \n" +
                "Id: " + id +
                ", Start: " + startDate +
                ", Finish: " + finishDate +
                ", Duration in Months: " + durationInMonths +
                ", client id: " + clientId;
    }
}