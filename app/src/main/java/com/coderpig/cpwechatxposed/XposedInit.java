package com.coderpig.cpwechatxposed;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/03 18:51.
 */

public class XposedInit implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.coderpig.cpwechatxposed")) {
            XposedHelpers.findAndHookMethod("com.coderpig.cpwechatxposed.MainActivity",
                    lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Class c = lpparam.classLoader.loadClass("com.coderpig.cpwechatxposed.MainActivity");
                            Field field = c.getDeclaredField("tv");
                            field.setAccessible(true);
                            XposedBridge.log("Test");
                            TextView tv = (TextView) field.get(param.thisObject);
                            tv.setText("贪玩难约");
                        }
                    });
        }
    }

}
