package com.tajhotels.automation.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RunContext {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH-mm-ss");
    private static final DateTimeFormatter TS   = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private static final String runDate = LocalDate.now().format(DATE);
    private static final String runTime = LocalDateTime.now().format(TIME);

    // One folder PER run
    private static final Path RUN_BASE =
            Paths.get(System.getProperty("user.dir"), "test-output", "runs", runDate, runTime);

    private static final Path REPORT_DIR = RUN_BASE.resolve("report");
    private static final Path SS_DIR     = RUN_BASE.resolve("screenshots");

    static {
        try {
            Files.createDirectories(REPORT_DIR);
            Files.createDirectories(SS_DIR);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create run folders: " + RUN_BASE, e);
        }
    }

    private RunContext() {}

    public static Path runBase() { return RUN_BASE; }
    public static Path reportDir() { return REPORT_DIR; }
    public static Path screenshotDir() { return SS_DIR; }

    public static String nowTs() {
        return LocalDateTime.now().format(TS);
    }
}