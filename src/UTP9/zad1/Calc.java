/**
 * @author Vu Cong Minh S25206
 */

package UTP9.zad1;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    final HashMap<Character, ParamsInterface> operators = new HashMap<>();

    public Calc() {
        operators.put('+', BigDecimal::add);
        operators.put('-', BigDecimal::subtract);
        operators.put('*', BigDecimal::multiply);
        operators.put('/', BigDecimal::divide);
    }

    public String doCalc(String input) {
        try {
            String[] values = this.extractValues(input);
            BigDecimal firstNumber = new BigDecimal(values[0]);
            BigDecimal secondNumber = new BigDecimal(values[1]);
            char operator = values[2].charAt(0);
            BigDecimal result = operators.get(operator).apply(firstNumber, secondNumber, MathContext.DECIMAL32);
            return result.toString();
        } catch (ArithmeticException | NullPointerException | IllegalStateException e) {
            return "Invalid command to calc";
        }
    }

    private String[] extractValues(String input) throws IllegalStateException {
        Pattern digitPattern = Pattern.compile("-?\\d+");
        Pattern whitespacePattern = Pattern.compile("\\s+");
        Matcher digitMatcher = digitPattern.matcher(input);
        digitMatcher.find();
        String firstNumber = digitMatcher.group();
        digitMatcher.find();
        String secondNumber = digitMatcher.group();
        Matcher whitespaceMatcher = whitespacePattern.matcher(input);
        whitespaceMatcher.find();
        char operator = input.charAt(whitespaceMatcher.end());
        return new String[]{firstNumber, secondNumber, Character.toString(operator)};
    }
}  
