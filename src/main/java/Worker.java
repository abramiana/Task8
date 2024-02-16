import java.util.Date;

public class Worker {
    private int id;
    private String name;
    private String level;
    private double salary;
    private Date birthday;

    public Worker(int id, String name, String level, double salary) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.salary = salary;
    }

    public Worker(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Worker(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker: \n" +
                "id: " + id +
                " Name: " + name +
                " Level: " + level +
                " Salary:" + salary +
                " Birthday " + birthday;

    }
}