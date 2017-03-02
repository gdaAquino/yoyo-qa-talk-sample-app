package me.popslide.training;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Gian Darren Aquino
 */
public class PlayStoreRobot {

    private static final int UI_TIMEOUT = 10_000;
    private static final int ASYNC_TIMEOUT = 30_000;
    private static final String PACKAGE_NAME = "com.android.vending";

    private UiDevice device;

    public PlayStoreRobot(UiDevice device) {
        this.device = device;
    }

    public PlayStoreRobot isDisplayingPlayStore() {
        assertTrue("PlayStore is not displayed", device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), UI_TIMEOUT));
        return this;
    }

    public PlayStoreRobot isDisplayingApplicationName(String name) {
        assertTrue("Application is not displayed", device.findObject(new UiSelector().text(name)).exists());
        return this;
    }

    public PlayStoreRobot isNotInstalled() {
        assertTrue("Application is not Installed", device.findObject(new UiSelector().text("INSTALL")).exists());
        return this;
    }

    public PlayStoreRobot install() throws UiObjectNotFoundException {
        device.findObject(new UiSelector().text("INSTALL")).click();
        return this;
    }

    public PlayStoreRobot isDownloading() {
        device.wait(Until.hasObject(By.res("com.android.vending:id/progress_bar")), UI_TIMEOUT);
        UiObject progress = device.findObject(new UiSelector().resourceId("com.android.vending:id/progress_bar"));
        assertTrue("Not downloading...", progress.exists());
        return this;
    }

    public PlayStoreRobot isRunningApplication(String application) {
        device.wait(Until.hasObject(By.pkg(application).depth(0)), ASYNC_TIMEOUT);
        assertThat(device.getCurrentPackageName(), Matchers.equalTo(application));
        return this;
    }
}
