package com.atul.audioplayer.helper;

import android.os.Build;

public class VersioningHelper {
    public static boolean isVersionQ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }
}
