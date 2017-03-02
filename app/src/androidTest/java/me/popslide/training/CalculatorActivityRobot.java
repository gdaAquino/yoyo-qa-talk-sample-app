package me.popslide.training;

import android.app.Activity;

import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * https://google.github.io/android-testing-support-library/docs/espresso/basics/
 *
 * @author Gian Darren Aquino
 */
class CalculatorActivityRobot {

    private Activity mActivity;

    public CalculatorActivityRobot(Activity activity) {
        mActivity = activity;
    }

    CalculatorActivityRobot input1(String input) {
        onView(withId(R.id.input1)).perform(click()).perform(typeText(input));
        Spoon.screenshot(mActivity, "Input_1");
        return this;
    }

    CalculatorActivityRobot input2(String input) {
        onView(withId(R.id.input2)).perform(click()).perform(typeText(input));
        Spoon.screenshot(mActivity, "Input_2");
        return this;
    }

    CalculatorActivityRobot add() {
        onView(withId(R.id.add)).perform(click());
        return this;
    }

    CalculatorActivityRobot subtract() {
        onView(withId(R.id.subtract)).perform(click());
        return this;
    }

    CalculatorActivityRobot isCorrect(String result) {
        onView(withId(R.id.result)).check(matches(withText(result)));
        Spoon.screenshot(mActivity, "Correct");
        return this;
    }
}
