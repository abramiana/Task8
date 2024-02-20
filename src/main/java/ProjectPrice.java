public class ProjectPrice {
    private String projectName;
    private double projectPrice;

    public ProjectPrice(String projectName, double projectPrice) {
        this.projectName = projectName;
        this.projectPrice = projectPrice;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(double projectPrice) {
        this.projectPrice = projectPrice;
    }

    @Override
    public String toString() {
        return "Project: " + projectName +
                " Price: " + projectPrice;
    }
}