package test1_real;

public class CuongStudentLinkedList {
    int size;
    CuongStudentNode head;
    CuongStudentNode pointer;

    public CuongStudentLinkedList() {
        this.size = 0;
        this.head = null;
        this.pointer = null;
    }

    private void reset() {
        pointer = head;
    }

    private boolean hasNext() {
        return (pointer != null);
    }

    private CuongStudent next() {
        CuongStudent data = pointer.data;
        pointer = pointer.next;
        return data;
    }

    private boolean insertAtHead(CuongStudent student) {
        CuongStudentNode newNode = new CuongStudentNode(student);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    private boolean insertAt(int index, CuongStudent student) {
        if (index > size) {
            return false;
        }
        if (index == 0) {
            return insertAtHead(student);
        }
        CuongStudentNode current = head;
        CuongStudentNode previous = null;
        while (index > 0) {
            previous = current;
            current = current.next;
            index--;
        }
        CuongStudentNode newNode = new CuongStudentNode(student);
        newNode.next = current;
        previous.next = newNode;
        size++;
        return true;
    }

    // add complexity = O(N)
    public int add(CuongStudent student) {
        reset();
        if (!hasNext()) {
            insertAt(0, student);
            return 0;
        }
        int index = 0;
        CuongStudent temp = next();
        while (temp.GPA < student.GPA) {
            index++;
            if (!hasNext()) {
                break;
            }
            temp = next();
        }
        insertAt(index, student);
        return index;
    }

    // nearest complexity = O(N)
    public CuongStudent nearest(double searchGPA) {
        reset();
        if (size == 0) {
            return null;
        }
        double currentNearest = Double.MAX_VALUE;
        CuongStudent nearestStudent, currentStudent;
        nearestStudent = currentStudent = next();
        while (true) {
            if (Math.abs(currentStudent.GPA - searchGPA) < currentNearest) {
                currentNearest = Math.abs(currentStudent.GPA - searchGPA);
                nearestStudent = currentStudent;
            }
            if (nearestStudent.GPA >= searchGPA) {
                break;
            }
            if (hasNext()) {
                currentStudent = next();
            } else {
                break;
            }
        }
        return nearestStudent;
    }

    // Client code
    public static void main(String[] args) {
        CuongStudentLinkedList list = new CuongStudentLinkedList();

        System.out.println("Test add method: ");
        System.out.println(list.add(new CuongStudent(1, "Alice", 3.3)));    // return 0
        System.out.println(list.add(new CuongStudent(2, "Bob", 3.6)));      // return 1
        System.out.println(list.add(new CuongStudent(3, "Carol", 3.0)));    // return 0
        System.out.println(list.add(new CuongStudent(4, "David", 3.5)));    // return 2
        System.out.println(list.add(new CuongStudent(5, "Evan", 3.9)));     // return 4

        System.out.println("Test nearest method: ");
        CuongStudent temp = list.nearest(0.1);
        System.out.printf("Neareast to 0.1 is (%d, %s, %.2f)\n", temp.id, temp.name, temp.GPA);  // (3, Carol, 3.0)
        temp = list.nearest(3.1);
        System.out.printf("Neareast to 3.1 is (%d, %s, %.2f)\n", temp.id, temp.name, temp.GPA);  // (3, Carol, 3.0)
        temp = list.nearest(3.2);
        System.out.printf("Neareast to 3.2 is (%d, %s, %.2f)\n", temp.id, temp.name, temp.GPA);  // (1, Alice, 3.3)
        temp = list.nearest(3.5);
        System.out.printf("Neareast to 3.5 is (%d, %s, %.2f)\n", temp.id, temp.name, temp.GPA);  // (4, David, 3.5)
        temp = list.nearest(4);
        System.out.printf("Neareast to 4.0 is (%d, %s, %.2f)\n", temp.id, temp.name, temp.GPA);  // (5, Evan, 3.9)
    }
}

class CuongStudentNode {
    CuongStudent data;
    CuongStudentNode next;
    public CuongStudentNode(CuongStudent data) {
        this.data = data;
        this.next = null;
    }

}

class CuongStudent {
    int id;
    String name;
    double GPA;

    public CuongStudent(int id, String name, double GPA) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }
}