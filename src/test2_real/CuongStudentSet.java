package test2_real;

public class CuongStudentSet {
    Student[] students;

    public CuongStudentSet(Student[] students) {
        this.students = students;
    }

    // Complexity: O(N)
    public int countGrade(String grade) {
        int count = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].grade.equals(grade)) {
                count++;
            }
        }
        return count;
    }

    // Complexity: O(N)
    public Student[] sortByGrade() {
        Student[] result = new Student[students.length];
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < students.length; i++) {
            linkedList.insert(students[i]);
        }
        for (int i = 0; i < students.length; i++) {
            result[i] = linkedList.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        Student student1 = new Student(1, "Tom", 3.9, "DI");
        Student student2 = new Student(2, "Sam", 3.8, "DI");
        Student student3 = new Student(3, "Jude", 2.3, "CR");
        Student student4 = new Student(4, "Harry", 0.8, "NN");
        Student[] studentList = new Student[4];
        studentList[0] = student1;
        studentList[1] = student2;
        studentList[2] = student3;
        studentList[3] = student4;
        CuongStudentSet set = new CuongStudentSet(studentList);
        System.out.println(set.countGrade("DI"));   // return 2

        Student[] result = set.sortByGrade();
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i].name);
        }
    }
}

class Student {
    public int id;
    public String name;
    public double GPA;
    public String grade;

    public Student(int id, String name, double GPA, String grade) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
        this.grade = grade;
    }

    public int compareTo(Student anotherStudent) {
        if (this.grade.equals("NN")) {
            if (anotherStudent.grade.equals("NN")) {
                return 0;
            } else if (anotherStudent.grade.equals("PA")) {
                return -1;
            } else if (anotherStudent.grade.equals("CR")) {
                return -1;
            } else if (anotherStudent.grade.equals("DI")) {
                return -1;
            } else if (anotherStudent.grade.equals("HD")) {
                return -1;
            }
        } else if (this.grade.equals("PA")) {
            if (anotherStudent.grade.equals("NN")) {
                return 1;
            } else if (anotherStudent.grade.equals("PA")) {
                return 0;
            } else if (anotherStudent.grade.equals("CR")) {
                return -1;
            } else if (anotherStudent.grade.equals("DI")) {
                return -1;
            } else if (anotherStudent.grade.equals("HD")) {
                return -1;
            }
        } else if (this.grade.equals("CR")) {
            if (anotherStudent.grade.equals("NN")) {
                return 1;
            } else if (anotherStudent.grade.equals("PA")) {
                return 1;
            } else if (anotherStudent.grade.equals("CR")) {
                return 0;
            } else if (anotherStudent.grade.equals("DI")) {
                return -1;
            } else if (anotherStudent.grade.equals("HD")) {
                return -1;
            }
        } else if (this.grade.equals("DI")) {
            if (anotherStudent.grade.equals("NN")) {
                return 1;
            } else if (anotherStudent.grade.equals("PA")) {
                return 1;
            } else if (anotherStudent.grade.equals("CR")) {
                return 1;
            } else if (anotherStudent.grade.equals("DI")) {
                return 0;
            } else if (anotherStudent.grade.equals("HD")) {
                return -1;
            }
        } else if (this.grade.equals("HD")) {
            if (anotherStudent.grade.equals("NN")) {
                return 1;
            } else if (anotherStudent.grade.equals("PA")) {
                return 1;
            } else if (anotherStudent.grade.equals("CR")) {
                return 1;
            } else if (anotherStudent.grade.equals("DI")) {
                return 1;
            } else if (anotherStudent.grade.equals("HD")) {
                return 0;
            }
        }
        return 0;
    }
}

class LinkedList {
    private int size;
    private StudentNode head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    public void insert(Student data) {
        if (head == null) {
            head = new StudentNode(data);
            size++;
            return;
        }
        StudentNode previous = null;
        StudentNode current = head;
        while (current != null) {
            if (current == head && current.data.compareTo(data) == -1) {
                StudentNode temp = current;
                StudentNode newNode = new StudentNode(data);
                head = newNode;
                newNode.next = temp;
                size++;
                return;
            }
            if (current.data.compareTo(data) == -1) {
                StudentNode temp = current.next;
                StudentNode newNode = new StudentNode(data);
                current.next = newNode;
                newNode.next = temp;
                size++;
                return;
            }
            previous = current;
            current = current.next;
        }
        StudentNode newNode = new StudentNode(data);
        previous.next = newNode;
        size++;
    }

    public Student pop() {
        Student temp = head.data;
        head = head.next;
        return temp;
    }
}

class StudentNode {
    public Student data;
    public StudentNode next;

    public StudentNode(Student data) {
        this.data = data;
        this.next = null;
    }
}