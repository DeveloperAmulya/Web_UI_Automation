package com.tajhotels.automation.reports;

import com.aventstack.extentreports.Status;

public final class Report {

    private Report() {}

    public static void info(String message) {
        if (ExtentTestManager.get() != null) {
            ExtentTestManager.get().log(Status.INFO, message);
        }
    }

    public static void pass(String message) {
        if (ExtentTestManager.get() != null) {
            ExtentTestManager.get().log(Status.PASS, message);
        }
    }

    public static void fail(String message) {
        if (ExtentTestManager.get() != null) {
            ExtentTestManager.get().log(Status.FAIL, message);
        }
    }
}