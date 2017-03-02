package me.popslide.training;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gian Darren Aquino
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mainActivityRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void addDisplaysCorrectResult() {
        new CalculatorActivityRobot()
                .input1("100")
                .input2("100")
                .add()
                .isCorrect("200");
    }

    @Test
    public void subtractDisplaysCorrectResult() {
        new CalculatorActivityRobot()
                .input1("200")
                .input2("100")
                .subtract()
                .isCorrect("100");
    }
}
