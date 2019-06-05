import group.Student;
import parser.Parser;

import java.util.List;

public class App {
    private static final String INPUT_FILE = "group.xml";
    private static final String OUTPUT_FILE = "new_group.xml";

    public static void main(String[] args) {
        List<Student> studentList = Parser.readFromXmlFile(INPUT_FILE);

        System.out.println("Read from file:");
        for (Student student : studentList) {
            System.out.println(student.toString());
            System.out.println("Is XML-average equals to REAL-average: "
                    + student.isXmlAverageMatchRealAverage());
        }

        System.out.println("\nAfter correction: ");
        for (Student student : studentList) {
            System.out.println(student.toString());
            System.out.println(student.isXmlAverageMatchRealAverage());
        }

        Parser.writeToXmlFile(studentList, OUTPUT_FILE);
    }
}
