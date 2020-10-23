import java.util.ArrayList;
import java.util.List;


/**
 * Parsing an expression and evaluating its value using the recursive descent method. Only works with integer.
 */
public class Main {


    public static void main(String[] args) {
        String expression = "7* ((12-(4+4+4*2)+5)-12) / 5";
        List<Lexeme> lexemes = lexicalAnalyzer(expression);
        Buffer buffer = new Buffer(lexemes);
        System.out.println("Result: " + expression(buffer));
    }

    /**
     * An enumerated type that includes the possible types of characters encountered in the expression.
     */
    public enum LexemeType {
        OPEN_BRACKET, CLOSE_BRACKET, PLUS, MINUS, MULTIPLICATION, DIVISION, NUMBER, END_OF_LINE;
    }

    /**
     * Helper class for representing a single lexeme.
     */
    public static class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    /**
     * A class for concentrating information about traversing an expression and making it easier.
     */
    public static class Buffer {
        private int pos;

        public List<Lexeme> lexemes;

        public Buffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }

    /**
     * The lexical analyzer itself. Goes through the expression and puts individual lexemes to the array.
     * Spaces are not counted.
     * @param expression The given expression.
     * @return An array of individual lexemes from the expression.
     */
    public static List<Lexeme> lexicalAnalyzer(String expression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char symbol = expression.charAt(pos);
            switch (symbol) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.OPEN_BRACKET, symbol));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.CLOSE_BRACKET, symbol));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, symbol));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINUS, symbol));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.MULTIPLICATION, symbol));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIVISION, symbol));
                    pos++;
                    continue;
                default:
                    if (symbol <= '9' && symbol >= '0') {
                        StringBuilder number = new StringBuilder();
                        do {
                            number.append(symbol);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            symbol = expression.charAt(pos);
                        } while (symbol <= '9' && symbol >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, number.toString()));
                    } else {
                        if (symbol != ' ') {
                            throw new RuntimeException("Unexpected character: " + symbol);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.END_OF_LINE, ""));
        return lexemes;
    }

    /**
     * Calculates the entire expression.
     * Last priority in expression.
     * @param lexemes An array of individual lexemes from the expression.
     * @return 0, if the expression is empty, or the result of addOrSubtract method (actually, the final result).
     */
    public static int expression(Buffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.END_OF_LINE) {
            return 0;
        } else {
            lexemes.back();
            return addOrSubtract(lexemes);
        }
    }

    /**
     * Calculates expressions with addition or subtraction signs. The principle is the same as in the multiplyOrDivide
     * function, but one level higher, so that instead of multiplyOrDivide, numberOrBrackets
     * is called to go through all the operation priorities.
     * Third priority in expression.
     * @param lexemes An array of individual lexemes from the expression.
     * @return The first number that is the result of multiplication or division
     * or the result of addition or subtraction.
     */
    public static int addOrSubtract(Buffer lexemes) {
        int value = multiplyOrDivide(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case PLUS:
                    value += multiplyOrDivide(lexemes);
                    break;
                case MINUS:
                    value -= multiplyOrDivide(lexemes);
                    break;
                case END_OF_LINE:
                case CLOSE_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    /**
     * Calculates expressions with division or multiplication signs. If the sign is different,
     * then it returns the first number that was read at the beginning.
     * Second priority in expression.
     * @param lexemes An array of individual lexemes from the expression.
     * @return The first number that was read or the result of multiplication or division.
     */
    public static int multiplyOrDivide(Buffer lexemes) {
        int value = numberOrBrackets(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case MULTIPLICATION:
                    value *= numberOrBrackets(lexemes);
                    break;
                case DIVISION:
                    value /= numberOrBrackets(lexemes);
                    break;
                case END_OF_LINE:
                case CLOSE_BRACKET:
                case PLUS:
                case MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    /**
     * Reads a number or calculates the expression in brackets. If brackets are met, it calls the addOrSubtract method
     * to calculate the expression in them.
     * First priority in expression.
     * @param lexemes An array of individual lexemes from the expression.
     * @return A number or the calculated expression in brackets.
     */
    public static int numberOrBrackets(Buffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case OPEN_BRACKET:
                int value = addOrSubtract(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.CLOSE_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
        }
    }
}
