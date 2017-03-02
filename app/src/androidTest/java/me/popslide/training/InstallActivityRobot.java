package me.popslide.training;

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

    public InstallActivityRobot input(String input) {
        onView(withId(R.id.input)).perform(typeText(input));
        return this;
    }

    public InstallActivityRobot isInstall() {
        onView(withId(R.id.install)).check(matches(withText(equalToIgnoringCase("install"))));
        return this;
    }

    public InstallActivityRobot isOpen() {
        onView(withId(R.id.install)).check(matches(withText(equalToIgnoringCase("open"))));
        return this;
    }

    public void execute() {
        onView(withId(R.id.install)).perform(click());
    }
}
