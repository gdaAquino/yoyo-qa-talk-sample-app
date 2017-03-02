package me.popslide.training;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gian Darren Aquino
 */
@RunWith(AndroidJUnit4.class)
public class InstallActivityInstrumentedTest {

    private static final String APP_NAME_PIANO_POP = "PianoPop";

    private static final String PACKAGE_NAME_APP = "me.popslide.training";
    private static final String PACKAGE_NAME_PLAYSTORE = "com.android.vending";
    private static final String PACKAGE_NAME_PIANO_POP = "com.yoyo.PianoPop.android";

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
                .input(PACKAGE_NAME_PLAYSTORE)
                .isOpen()
                .execute();
    }

    @Test
    public void install() throws Exception {
        new InstallActivityRobot()
                .input(PACKAGE_NAME_PIANO_POP)
                .isInstall()
                .execute();
        new PlayStoreRobot(device)
                .isDisplayingPlayStore()
                .isDisplayingApplicationName(APP_NAME_PIANO_POP)
                .isNotInstalled()
                .install()
                .isDownloading()
                .isRunningApplication(PACKAGE_NAME_PIANO_POP);
        /** Go back to our application **/
        Utils.runApplication(PACKAGE_NAME_APP, InstallActivity.class);
        /** PlayStore auto run app so wait for it **/
        new PlayStoreRobot(device).isRunningApplication(PACKAGE_NAME_APP);
        new InstallActivityRobot()
                .input(PACKAGE_NAME_PIANO_POP)
                .isOpen();
    }
}