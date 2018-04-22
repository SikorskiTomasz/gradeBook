package GradeBook;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<Integer> Grades;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        Grades = new ArrayList<Integer>();
    }

    public Student(String serializationLine) {
        Grades = new ArrayList<>();
        deserialize(serializationLine);
    }


    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLowest() {
        int lowest = Integer.MAX_VALUE;
        for (int i = 0; i < Grades.size(); i++) {
            if (Grades.get(i) < lowest) {
                lowest = Grades.get(i);
            }
        }
        return lowest;
    }

    public int getHighest() {
        int highest = 0;
        for (int i = 0; i < Grades.size(); i++) {
            if (Grades.get(i) > highest) {
                highest = Grades.get(i);
            }
        }
        return highest;
    }

    public double getAverage() {
        double total = 0;
        for (int i = 0; i < Grades.size(); i++) {
            total += Grades.get(i);
        }
        if (Grades.size() == 0) return 0;
        return (double) total / (double) Grades.size();
    }

    public void addGrade(Integer grade) {
        Grades.add(grade);
    }

    public String serialize() {
        String retval = firstName + "," + lastName;
        for (int i = 0; i < Grades.size(); i++) {
            retval += ","  + Grades.get(i);
        }
        return retval;
    }

    private void deserialize(String serializationString) {
        StringTokenizer tokenizer = new StringTokenizer(serializationString, ", ");
        firstName = tokenizer.nextToken();
        lastName = tokenizer.nextToken();
        while (tokenizer.hasMoreTokens()) {
            Grades.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }
}
