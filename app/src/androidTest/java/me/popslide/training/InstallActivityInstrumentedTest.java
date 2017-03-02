package me.popslide.training;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Gian Darren Aquino
 */
@RunWith(AndroidJUnit4.class)
public class InstallActivityInstrumentedTest {

    private static final int UI_TIMEOUT = 10_000;
    private static final int ASYNC_TIMEOUT = 30_000;

    private static final String PIANO_POP_APP_NAME = "PianoPop";

    private static final String PACKAGE_NAME = "me.popslide.training";
    private static final String PLAYSTORE_PACKAGE_NAME = "com.android.vending";
    private static final String PIANO_POP_PACKAGE_NAME = "com.yoyo.PianoPop.android";

    @Rule
    public ActivityTestRule<InstallActivity> mainActivityRule = new ActivityTestRule<>(InstallActivity.class);
    public UiDevice device;

    @Before
    public void setUp() throws Exception {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void open() {
        new InstallActivityRobot()
                .input(PLAYSTORE_PACKAGE_NAME)
                .isOpen()
                .execute();
    }

    @Test
    public void install() throws Exception {

        new InstallActivityRobot()
                .input(PIANO_POP_PACKAGE_NAME)
                .isInstall()
                .execute();

        /** Wait for PlayStore opening **/
        device.wait(Until.hasObject(By.pkg(PLAYSTORE_PACKAGE_NAME).depth(0)), UI_TIMEOUT);

        /** Check if its the correct application **/
        UiObject name = device.findObject(new UiSelector().text(PIANO_POP_APP_NAME));
        assertTrue(name.exists());

        /** Install application **/
        BySelector install = By.text("INSTALL");
        assertTrue(device.wait(Until.hasObject(install), UI_TIMEOUT));
        device.findObject(install).click();

        /** Check if its downloading **/
        device.wait(Until.hasObject(By.res("com.android.vending:id/progress_bar")), UI_TIMEOUT);
        UiObject progress = device.findObject(new UiSelector().resourceId("com.android.vending:id/progress_bar"));
        assertTrue("Not downloading...", progress.exists());

        /** PlayStore auto run app so wait for it **/
        device.wait(Until.hasObject(By.pkg(PIANO_POP_PACKAGE_NAME).depth(0)), ASYNC_TIMEOUT);
        assertThat(device.getCurrentPackageName(), Matchers.equalTo(PIANO_POP_PACKAGE_NAME));

        /** Go back to our application **/
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent()
                .setClassName(PACKAGE_NAME, InstallActivity.class.getName())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        /** PlayStore auto run app so wait for it **/
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), UI_TIMEOUT);
        assertThat(device.getCurrentPackageName(), Matchers.equalTo(PACKAGE_NAME));

        new InstallActivityRobot()
                .input(PIANO_POP_PACKAGE_NAME)
                .isOpen();
    }
}