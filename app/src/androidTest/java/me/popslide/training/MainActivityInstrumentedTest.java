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
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addDisplaysCorrectResult() {
        new MainActivityRobot()
                .input1("100")
                .input2("100")
                .performAddition()
                .resultMatches("200");

    }

    @Test
    public void subtractDisplaysCorrectResult() {
        new MainActivityRobot()
                .input1("200")
                .input2("100")
                .performSubtraction()
                .resultMatches("100");
    }
}
