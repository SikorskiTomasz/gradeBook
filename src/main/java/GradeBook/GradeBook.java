package GradeBook;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GradeBook {

    private ArrayList<Student> Students;

    public GradeBook() {
        Students = new ArrayList<>();
    }

    public GradeBook(String[] serializationStrings) {
        Students = new ArrayList<>();
        deserialize(serializationStrings);

    }

    public void showStudent(Student student) {
        System.out.println("Student: " + student.getName() + " " + student.getLastName());
        System.out.println("Lowest grade: " + student.getLowest());
        System.out.println("Highest grade: " + student.getHighest());
        System.out.printf("Avarage grade: %.2f\n",student.getAverage());
    }

    public void display() {
        for(int i = 0; i < Students.size(); i++) {
            showStudent(Students.get(i));
        }
    }

    public void addStudent(Student student) {
        Students.add(student);
    }

    public String[] serialize() {
        String[] retval = new String[Students.size()];
        for(int i = 0; i < Students.size(); i++) {
            retval[i] = Students.get(i).serialize();
        }
        return retval;
    }

    private void deserialize(String[] serializationLines) {
        for (String line : serializationLines) {
            addStudent(new Student(line));
        }
    }

    public void saveToFile(String fileName){
        try {
            PrintWriter printWriter = new PrintWriter(fileName, "UTF-8");
            String[] studentsList = serialize();
            for(int i = 0; i < studentsList.length; i++) {
                printWriter.println(studentsList[i]);
            }
            printWriter.close();
        }
        catch (Exception e) {
        }
    }

    public static GradeBook readFromFile(String fileName){
        Path path = Paths.get(fileName);
        Charset charset = Charset.forName("UTF-8");
        try {
            List<String> lines = Files.readAllLines(path, charset);
            return new GradeBook(lines.toArray(new String[lines.size()]));
        }
        catch (Exception e)
        {
            return null;

        }
    }

    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Student student = new Student("John", "Buttchicks");
        gradeBook.addStudent(student);
        student.addGrade(6);
        student.addGrade(3);
        student.addGrade(4);
        student.addGrade(2);

        student = new Student("Diana", "Buttholes");
        gradeBook.addStudent(student);
        student.addGrade(5);
        student.addGrade(3);
        student.addGrade(1);
        student.addGrade(3);
        student.addGrade(3);
        student.addGrade(4);
        student.addGrade(2);
        student.addGrade(6);

        student = new Student("Ronald", "Buttface");
        gradeBook.addStudent(student);
        student.addGrade(6);
        student.addGrade(6);
        student.addGrade(6);

        System.out.println(student.serialize());

        gradeBook.display();

        gradeBook.saveToFile("\\home\\tomasz\\gradeBook.txt");

        GradeBook gradeBook_read = GradeBook.readFromFile("\\home\\tomasz\\gradeBook.txt");
        gradeBook_read.display();
    }
}
