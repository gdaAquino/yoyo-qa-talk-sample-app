package me.popslide.training;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Gian Darren Aquino
 *         https://google.github.io/android-testing-support-library/docs/espresso/basics/
 */
class MainActivityRobot {

    MainActivityRobot input1(String input) {
        onView(withId(R.id.input1)).perform(click()).perform(typeText(input));
        return this;
    }

    MainActivityRobot input2(String input) {
        onView(withId(R.id.input2)).perform(click()).perform(typeText(input));
        return this;
    }

    MainActivityRobot performAddition() {
        onView(withId(R.id.add)).perform(click());
        return this;
    }

    MainActivityRobot performSubtraction() {
        onView(withId(R.id.subtract)).perform(click());
        return this;
    }

    MainActivityRobot resultMatches(String result) {
        onView(withId(R.id.result)).check(matches(withText(result)));
        return this;
    }
}
