package step1.arithmetic;

public class Multiplication implements FourArithmetic {
    @Override
    public int calculate(int value1, int value2) {
        return value1 * value2;
    }
}