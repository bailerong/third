import java.util.Stack;

/**
 * Author:lidan
 * Created:2019/5/11
 */

public class Main {

    static Stack<Character> op = new Stack<>();

    public static Float getv(char op, Float f1, Float f2) {
        if (op == '+') return f2 + f1;
        else if (op == '-') return f2 - f1;
        else if (op == '*') return f2 * f1;
        else if (op == '/') return f2 / f1;
        else return Float.valueOf(-0);
    }

    public static float calrp(String rp) {
        Stack<Float> v = new Stack<>();
        char[] arr = rp.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            Character ch = arr[i];
            if (ch >= '0' && ch <= '9') v.push(Float.valueOf(ch - '0'));
            else v.push(getv(ch, v.pop(), v.pop()));
        }
        return v.pop();
    }

    public static String getrp(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        String out = "";

        for (int i = 0; i < len; i++) {
            char ch = arr[i];
            if (ch == ' ') continue;
            if (ch >= '0' && ch <= '9') {
                out += ch;
                continue;
            }
            if (ch == '(') op.push(ch);
            if (ch == '+' || ch == '-') {
                while (!op.empty() && (op.peek() != '('))
                    out += op.pop();
                op.push(ch);
                continue;
            }
            if (ch == '*' || ch == '/') {
                while (!op.empty() && (op.peek() == '*' || op.peek() == '/'))
                    out += op.pop();
                op.push(ch);
                continue;
            }
            if (ch == ')') {
                while (!op.empty() && op.peek() != '(')
                    out += op.pop();
                op.pop();
                continue;
            }
        }
        while (!op.empty()) out += op.pop();
        return out;
    }

    public static void main(String[] args) {
        String exp = "1-2*(3+2)";
        System.out.println(getrp(exp));
        System.out.println(calrp(getrp(exp)));
    }

}