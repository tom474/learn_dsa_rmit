package w5_time_and_space_tradeoffs;

public class Problem3 {
    public static void main(String[] args) {
        RMITStudentCollectionLinearProbing2 collection = new RMITStudentCollectionLinearProbing2(13);

        // Alice -> Bob -> Carol
        collection.put(new RMITStudent("S123", "Alice", "IT", 3.6));
        collection.put(new RMITStudent("S321", "Bob", "SE", 4.0));
        collection.put(new RMITStudent("S231", "Carol", "CS", 3.8));
        System.out.println(collection.get("S123"));  // Alice
        System.out.println(collection.get("S231"));  // Carol
        // Remove Bob - the middle element
        collection.remove("S321");
        // Check if Bob has been replaced by DELETED
        System.out.println(collection.hashTable[(collection.hashString("S321") + 1) % collection.N]);
        // Try to access Carol again
        System.out.println(collection.get("S231"));  // Carol
        // Add a new student having the same hash value
        collection.put(new RMITStudent("S312", "Dang", "IS", 3.5));
        // Check if the new student occupies the slot of Bob before
        System.out.println(collection.hashTable[(collection.hashString("S321") + 1) % collection.N]);
    }
}

class RMITStudentCollectionLinearProbing2 {
    int N;      // Size of the array for the hash table
    int size;   // The actual number of elements in the hash table
    RMITStudent[] hashTable;

    // Let's create a special value for DELETED
    static RMITStudent DELETED = new RMITStudent("S000", "DELETED", "NONE", 0.0);

    public RMITStudentCollectionLinearProbing2(int tableSize) {
        N = tableSize;
        size = 0;
        hashTable = new RMITStudent[N];
    }

    int hashCharacter(char c) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return str.indexOf(c);
    }

    int hashString(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += hashCharacter(s.charAt(i));
        }
        return res % N;
    }

    boolean put(RMITStudent student) {
        // Hash table is full
        if (size == N) {
            return false;
        }
        int hash = hashString(student.studentID);
        while (hashTable[hash] != null && hashTable[hash] != DELETED) {
            // The calculated position has been occupied (collision)
            // Check if there is a duplicated id
            if (hashTable[hash].studentID.equals(student.studentID)) {
                return false;
            }
            // Try the next position
            // Make sure hash is not out of range
            hash = (hash + 1) % N;
        }
        hashTable[hash] = student;
        size++;
        return true;
    }

    RMITStudent get(String studentID) {
        int hash = hashString(studentID);
        while (hashTable[hash] != null) {
            // Is this the correct student?
            if (hashTable[hash].studentID.equals(studentID)) {
                return hashTable[hash];
            }
            // Try the next position
            hash = (hash + 1) % N;
        }
        return null;
    }

    boolean remove(String studentID) {
        int hash = hashString(studentID);
        while (hashTable[hash] != null) {
            // Is this the correct student?
            if (hashTable[hash].studentID.equals(studentID)) {
                hashTable[hash] = DELETED;
                size--;
                return true;
            }
            // Try the next position
            hash = (hash + 1) % N;
        }
        return false;
    }
}