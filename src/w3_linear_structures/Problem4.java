package w3_linear_structures;

public class Problem4 {
    public static boolean isBalanced(String sequence) {
        LinkedListStack<Character> st = new LinkedListStack<>();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                st.push(c);
                continue;
            }
            if (st.isEmpty()) {
                return false;
            }
            char c2 = st.peek();
            st.pop();
            if ((c == ']' && c2 != '[') || (c == ')' && c2 != '(') || (c == '}' && c2 != '{')) {
                return false;
            }
        }
        if (!st.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[]: " + isBalanced("[]"));  // True
        System.out.println("[](){}: " + isBalanced("[](){}"));  // True
        System.out.println("{[]}(()): " + isBalanced("{[]}(())"));  // True
        System.out.println("][)(}{: " + isBalanced("][)(}{"));  // False
        System.out.println("(((((((((()))))))))}: " + isBalanced("(((((((((()))))))))}"));  // False
    }
}
