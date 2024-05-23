package w5_time_and_space_tradeoffs;

public class RMITStudent {
    String studentID;
    String fullName;
    String major;
    double GPA;

    public RMITStudent(String studentID, String fullName, String major, double GPA) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.major = major;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "studentID: " + studentID + ", fullName: " + fullName + ", major: " + major + ", GPA: " + GPA;
    }
}
