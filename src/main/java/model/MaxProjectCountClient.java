package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Клас, що представляє клієнта з найбільшою кількістю проєктів і містить інформацію про його ім'я та кількість проєктів.
 */
@Getter
@Setter
public class MaxProjectCountClient {
    private String name;
    private int projectCount;

    public MaxProjectCountClient(String name, int projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {

        return "model.LongestProject: \n"
                + "Name: " + name +
                ", Project count: " + projectCount +
                "\n**********************************";
    }
}