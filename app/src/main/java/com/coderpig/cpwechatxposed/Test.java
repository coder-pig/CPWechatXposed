package com.coderpig.cpwechatxposed;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/19 16:07.
 */

public class Test implements IXposedHookInitPackageResources {
    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        resparam.res.hookLayout(resparam.packageName, "layout", "activity_main", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable { }
        });
    }
}
