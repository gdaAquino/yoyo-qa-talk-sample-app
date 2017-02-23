package me.popslide.training;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Gian Darren Aquino
 */

public class MathUnitTest {

    @Test
    public void testAdditionIsCorrect() {
        int input1 = 100;
        int input2 = 100;
        int output = input1 + input2;
        assertThat(Math.add(input1, input2), CoreMatchers.equalTo(output));
    }

    @Test
    public void testSubtractionIsCorrect() {
        int input1 = 200;
        int input2 = 100;
        int output = input1 - input2;
        assertThat(Math.subtract(input1, input2), CoreMatchers.equalTo(output));
    }
}
