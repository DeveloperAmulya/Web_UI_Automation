package com.tajhotels.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Path;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            if (driver == null) return null;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String fileName = safeName + "_" + RunContext.nowTs() + ".png";

            Path dest = RunContext.screenshotDir().resolve(fileName);
            FileUtils.copyFile(src, dest.toFile());

            // return absolute path so Extent can load it reliably
            return dest.toString();
        } catch (Exception e) {
            return null;
        }
    }
}