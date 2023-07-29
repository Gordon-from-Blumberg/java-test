import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void test() {
        Assertions.assertEquals(1, Calculator.calculate("1"));
        Assertions.assertEquals(-1, Calculator.calculate("-1"));
        Assertions.assertEquals(3, Calculator.calculate("1+2"));
        Assertions.assertEquals(-1, Calculator.calculate("1-2"));
        Assertions.assertEquals(6, Calculator.calculate("2*3"));
        Assertions.assertEquals(6, Calculator.calculate("1+2+3"));
        Assertions.assertEquals(5, Calculator.calculate("1*2+3"));
        Assertions.assertEquals(7, Calculator.calculate("1+2*3"));
        Assertions.assertEquals(2.5f, Calculator.calculate("5/2"));
        Assertions.assertEquals(7, Calculator.calculate("1.5*3+5/2"));
        Assertions.assertEquals(3, Calculator.calculate("(1+2)"));
        Assertions.assertEquals(8, Calculator.calculate("(1+2)+5"));
        Assertions.assertEquals(3, Calculator.calculate("1-(1+2)+5"));
        Assertions.assertEquals(6, Calculator.calculate("3+2*1.5"));
        Assertions.assertEquals(7.5f, Calculator.calculate("(3+2)*1.5"));
        Assertions.assertEquals(18, Calculator.calculate("2*((1+2)*3)"));
        Assertions.assertEquals(16, Calculator.calculate("20-2*(10/(7-2))"));
    }
}
