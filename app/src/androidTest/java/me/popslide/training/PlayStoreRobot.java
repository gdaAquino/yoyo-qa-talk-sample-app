package me.popslide.training;

import android.app.Activity;
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

    private final UiDevice mDevice;
    private final Activity mActivity;

    public PlayStoreRobot(UiDevice device, Activity activity) {
        mDevice = device;
        mActivity = activity;
    }

    public PlayStoreRobot isDisplayingPlayStore() {
        assertTrue("PlayStore is not displayed", mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), UI_TIMEOUT));
        return this;
    }

    public PlayStoreRobot isDisplayingApplicationName(String name) {
        assertTrue("Application is not displayed", mDevice.findObject(new UiSelector().text(name)).exists());
        return this;
    }

    public PlayStoreRobot isNotInstalled() {
        assertTrue("Application is not Installed", mDevice.findObject(new UiSelector().text("INSTALL")).exists());
        return this;
    }

    public PlayStoreRobot install() throws UiObjectNotFoundException {
        mDevice.findObject(new UiSelector().text("INSTALL")).click();
        return this;
    }

    public PlayStoreRobot isDownloading() {
        mDevice.wait(Until.hasObject(By.res("com.android.vending:id/progress_bar")), UI_TIMEOUT);
        UiObject progress = mDevice.findObject(new UiSelector().resourceId("com.android.vending:id/progress_bar"));
        assertTrue("Not downloading...", progress.exists());
        return this;
    }

    public PlayStoreRobot isRunningApplication(String application) {
        mDevice.wait(Until.hasObject(By.pkg(application).depth(0)), ASYNC_TIMEOUT);
        assertThat(mDevice.getCurrentPackageName(), Matchers.equalTo(application));
        return this;
    }
}
