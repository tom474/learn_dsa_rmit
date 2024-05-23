package w5_time_and_space_tradeoffs;

public class Problem2B {
    public static void main(String[] args) {
        RMITStudentCollectionLinearProbing collection = new RMITStudentCollectionLinearProbing(13);

        // All student records have the same hash value (why?)
        collection.put(new RMITStudent("S123", "Alice", "IT", 3.6));
        collection.put(new RMITStudent("S321", "Bob", "SE", 4.0));
        collection.put(new RMITStudent("S231", "Carol", "CS", 3.8));
        System.out.println(collection.get("S123"));  // Alice
        System.out.println(collection.get("S231"));  // Carol
        System.out.println(collection.get("S213"));  // null
    }
}

class RMITStudentCollectionLinearProbing {
    int N;      // Size of the array for the hash table
    int size;   // the actual number of elements in the hash table
    RMITStudent[] hashTable;

    public RMITStudentCollectionLinearProbing(int tableSize) {
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
        while (hashTable[hash] != null) {
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
        int probes = 0;  // number of probes?

        while (hashTable[hash] != null) {
            // Is this the correct student?
            if (hashTable[hash].studentID.equals(studentID)) {
                return hashTable[hash];
            }
            // Try the next position
            hash = (hash + 1) % N;
            probes++;
            if (probes >= N) {
                // Hash table is full, but the search key does not exist
                return null;
            }
        }
        return null;
    }
}