package com.tajhotels.automation.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> TL_TEST = new ThreadLocal<>();

    private ExtentTestManager() {}

    public static void set(ExtentTest test) {
        TL_TEST.set(test);
    }

    public static ExtentTest get() {
        return TL_TEST.get();
    }

    public static void remove() {
        TL_TEST.remove();
    }
}