import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            String filePath = "/Users/meghapersonal/Desktop/interns-assignment/Assignment-2/InputOutput/src/input.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            BufferedWriter bw = new BufferedWriter(new FileWriter("outer.txt"));
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] line = lines.split("=");
                if (line.length == 2) {
                    String expression = line[0].trim();
                    String result = evaluateExpression(expression.trim());
                    bw.write(expression + " = " + result + "\n");
                }
            }
            bw.close();
            br.close();
            System.out.println("File generated successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(String expression) {
        try {
            System.out.println("Evaluating expression: " + expression);
            String result = Double.toString(eval(expression));
            System.out.println("Result: " + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ')
                    nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+'))
                        x += parseTerm(); // addition
                    else if (eat('-'))
                        x -= parseTerm(); // subtraction
                    else
                        return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*'))
                        x *= parseFactor(); // multiplication
                    else if (eat('/'))
                        x /= parseFactor(); // division
                    else
                        return x;
                }
            }

            double parseFactor() {
                boolean unaryMinus = false; // Flag to track unary minus
                while (eat('+') || eat('-')) {
                    if (ch == '-') {
                        unaryMinus = !unaryMinus; // Toggle the unaryMinus flag
                    }
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return unaryMinus ? -x : x; // Apply unary minus if necessary
            }
        }.parse();
    }
}
