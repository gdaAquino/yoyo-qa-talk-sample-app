package me.popslide.training;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

/**
 * @author Gian Darren Aquino
 */
public class Utils {

    public static void runApplication(String packageName, Class clazz) {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent()
                .setClassName(packageName, clazz.getName())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
