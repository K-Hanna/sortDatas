import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{

        //read file
        List<String> components = new ArrayList<>();

        try {
            FileReader fr01 = new FileReader("dane01.txt");
            StreamTokenizer st = new StreamTokenizer(fr01);
            int value = 0;
            while ((value = st.nextToken()) != StreamTokenizer.TT_EOF) {
                if (value == StreamTokenizer.TT_WORD) {
                    components.add(st.sval);
                }
                else if (value == StreamTokenizer.TT_NUMBER) {
                    components.add(Integer.toString((int)st.nval));
                }
            }
        } catch(Exception ex){
            System.out.println("Error: " + ex);
        }
        try {
            FileReader fr02 = new FileReader("dane02.txt");
            StreamTokenizer st = new StreamTokenizer(fr02);
            int value = 0;
            while ((value = st.nextToken()) != StreamTokenizer.TT_EOF) {
                if (value == StreamTokenizer.TT_WORD) {
                    components.add(st.sval);
                }
                else if (value == StreamTokenizer.TT_NUMBER) {
                    components.add(Integer.toString((int)st.nval));
                }
            }
        } catch(Exception ex){
            System.out.println("Error: " + ex);
        }

        //preparing data
        Student[] students = new Student[components.size() / 4];
        for (int i = 0; i < components.size() / 4; i++) {
            students[i] = new Student();
            students[i].setIndexNumber(components.get(i * 4));
            students[i].setFirstName(components.get(i * 4 + 1));
            students[i].setLastName(components.get(i * 4 + 2));
            students[i].setYearOfBirth(Integer.parseInt(components.get(i * 4 + 3)));
        }

        List<Student> student = new ArrayList<>();
        for (int i = 0; i < components.size() / 4; i++) {
            student.add(students[i]);
        }

        //save files
        student.sort(Comparator.comparing(Student::getIndexNumber));

        PrintWriter sortSka = new PrintWriter("sortSka.txt");
        sortSka.print(student);
        sortSka.close();

        student.sort(Comparator.comparing(Student::getFirstName).reversed());

        PrintWriter sortName = new PrintWriter("sortName.txt");
        sortName.print(student);
        sortName.close();
    }
}
class Person {

    private String firstName;
    private String lastName;
    private int yearOfBirth;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + " " + yearOfBirth;
    }
}
class Student extends Person{

    private String indexNumber;

    public Student(){
        super();
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString(){
        return  "\r\n" + indexNumber + " " + super.toString();
    }
}