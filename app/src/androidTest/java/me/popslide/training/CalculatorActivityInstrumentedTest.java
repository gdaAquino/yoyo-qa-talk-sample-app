package me.popslide.training;

import android.support.test.filters.LargeTest;
import android.support.test.filters.RequiresDevice;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gian Darren Aquino
 */
@LargeTest
@RequiresDevice
@RunWith(AndroidJUnit4.class)
public class CalculatorActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void addDisplaysCorrectResult() {
        new CalculatorActivityRobot(mActivityRule.getActivity())
                .input1("100")
                .input2("100")
                .add()
                .isCorrect("200");
    }

    @Test
    public void subtractDisplaysCorrectResult() {
        new CalculatorActivityRobot(mActivityRule.getActivity())
                .input1("200")
                .input2("100")
                .subtract()
                .isCorrect("100");
    }
}
