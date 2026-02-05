package com.tajhotels.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            if (driver == null) return null;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dir = "test-output/screenshots/";
            String path = dir + testName + "_" + time + ".png";

            new File(dir).mkdirs();
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}