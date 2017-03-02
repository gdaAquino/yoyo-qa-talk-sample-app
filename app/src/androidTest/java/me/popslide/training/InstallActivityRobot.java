package me.popslide.training;

import android.app.Activity;

import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * @author Gian Darren Aquino
 */

public class InstallActivityRobot {

    private Activity mActivity;

    public InstallActivityRobot(Activity activity) {
        mActivity = activity;
    }

    public InstallActivityRobot input(String input) {
        onView(withId(R.id.input)).perform(typeText(input));
        Spoon.screenshot(mActivity, "Input");
        return this;
    }

    public InstallActivityRobot isInstall() {
        onView(withId(R.id.install)).check(matches(withText(equalToIgnoringCase("install"))));
        Spoon.screenshot(mActivity, "Is_Install");
        return this;
    }

    public InstallActivityRobot isOpen() {
        onView(withId(R.id.install)).check(matches(withText(equalToIgnoringCase("open"))));
        Spoon.screenshot(mActivity, "Is_Open");
        return this;
    }

    public void execute() {
        onView(withId(R.id.install)).perform(click());
        Spoon.screenshot(mActivity, "Execute");
    }
}
