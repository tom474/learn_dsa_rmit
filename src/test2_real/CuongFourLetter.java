package test2_real;

public class CuongFourLetter {
    String string;
    char[] target = {'R', 'M', 'I', 'T'};

    public CuongFourLetter(String string) {
        this.string = string;
    }

    private int hashCharacter(char c) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return str.indexOf(c);
    }

    // Complexity: O(N)
    public int freeTransform() {
        int count = 0;
        char[] result = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            result[i] = string.charAt(i);
        }
        for (int i = 0; i < result.length; i++) {
            int hashStr = hashCharacter(result[i]);
            int hashTar = hashCharacter(target[i]);
            if (hashStr == hashTar) {
                continue;
            }
            if (hashStr < hashTar) {
                int next = hashTar - hashStr;
                int previous = hashStr + (26 - hashTar);
                if (next > previous) {
                    count += previous;
                } else {
                    count += next;
                }
            }
            if (hashStr > hashTar){
                int next = 26 - hashStr + hashTar;
                int previous = hashStr - hashTar;
                if (next > previous) {
                    count += previous;
                } else {
                    count += next;
                }
            }
        }
        return count;
    }

    // Complexity: O(N)
    public int constraintTransform(String forbidden) {
        int count = 0;
        char[] result = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            result[i] = string.charAt(i);
        }
        for (int i = 0; i < result.length; i++) {
            int hashStr = hashCharacter(result[i]);
            int hashTar = hashCharacter(target[i]);
            if (hashStr == hashTar) {
                continue;
            }
            if (hashStr < hashTar) {
                int next = hashTar - hashStr;
                int previous = hashStr + (26 - hashTar);
                if (next > previous) {
                    count += previous;
                } else {
                    count += next;
                }
            }
            if (hashStr > hashTar){
                int next = 26 - hashStr + hashTar;
                int previous = hashStr - hashTar;
                if (next > previous) {
                    count += previous;
                } else {
                    count += next;
                }
            }
        }
        return count + 2;
    }

    public static void main(String[] args) {
        CuongFourLetter fl = new CuongFourLetter("RMIV");
        System.out.println(fl.freeTransform());
        System.out.println(fl.constraintTransform("RMIU"));
    }
}