package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectPrice {
    private String projectName;
    private double projectPrice;

    public ProjectPrice(String projectName, double projectPrice) {
        this.projectName = projectName;
        this.projectPrice = projectPrice;
    }

    @Override
    public String toString() {
        return "Project: " + projectName +
                " Price: " + projectPrice;
    }
}