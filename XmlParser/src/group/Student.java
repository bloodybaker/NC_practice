package group;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firsName;
    private String lastName;
    private String groupNumber;

    private List<Subject> subjects = new ArrayList<>();

    private double average;

    public Student() {
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public boolean isXmlAverageMatchRealAverage() {
        double realAverage;
        double sum = 0;
        double epsilon = 0.1;
        int subjectsCount = subjects.size();
        for (Subject subject : subjects) {
            sum += subject.getMark();
        }
        if (subjectsCount != 0) {
            realAverage = sum / subjectsCount;
            boolean isEqual = Math.abs(realAverage - this.average) <= epsilon;
            if (!isEqual) {
                this.setAverage(realAverage);
            }
            return isEqual;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", subjects=" + subjects +
                ", average=" + average +
                '}';
    }
}
