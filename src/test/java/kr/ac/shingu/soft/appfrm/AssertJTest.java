package kr.ac.shingu.soft.appfrm;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class AssertJTest {
    @Test
    public void test() {
        int num1 = 15;
        int num2 = 0;
        int num3 = -5;

        assertThat(num1).isPositive();

        assertThat(num2).isZero();

        assertThat(num3).isNegative();

        assertThat(num1).isGreaterThan(num2);

        assertThat(num3).isLessThan(num2);

    }

}
