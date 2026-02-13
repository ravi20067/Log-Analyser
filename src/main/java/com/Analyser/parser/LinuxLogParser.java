package com.Analyser.parser;

import com.Analyser.ENUM.LinuxAttackType;
import com.Analyser.Entity.LinuxLog;

import java.util.*;
import java.util.regex.*;

public class LinuxLogParser {

    // Universal LinuxSaver header pattern
    private static final Pattern HEADER_PATTERN = Pattern.compile(
            "^(\\w{3})\\s+(\\d{1,2})\\s+(\\d{2}:\\d{2}:\\d{2})\\s+(\\S+)\\s+([^:]+):\\s+(.*)$"
    );

    private static final Pattern IP_PATTERN = Pattern.compile(
            "(\\d+\\.\\d+\\.\\d+\\.\\d+)"
    );

    private static final Pattern FILE_PATTERN = Pattern.compile(
            "(/\\S+)"
    );

    public static List<LinuxLog> parse(List<String> lines, LinuxAttackType attackType) {

        List<LinuxLog> logs = new ArrayList<>();

        for (String line : lines) {

            line = line.trim();
            if (line.isEmpty()) continue;

            Matcher matcher = HEADER_PATTERN.matcher(line);

            if (!matcher.matches()) continue;

            String month = matcher.group(1);
            String day = matcher.group(2);
            String date = month + " " + day;

            String time = matcher.group(3);
            String host = matcher.group(4);
            String service = matcher.group(5);
            String message = matcher.group(6);

            String sourceIp = extractIP(message);
            String username = extractUsername(message);
            String process = extractProcess(service);
            String filePath = extractFilePath(message);

            LinuxLog log = new LinuxLog(
                    date,
                    time,
                    host,
                    service,
                    message,
                    sourceIp,
                    username,
                    process,
                    filePath,
                    attackType
            );

            logs.add(log);
        }

        return logs;
    }

    private static String extractIP(String message) {
        Matcher m = IP_PATTERN.matcher(message);
        if (m.find()) return m.group(1);
        return null;
    }

    private static String extractFilePath(String message) {
        Matcher m = FILE_PATTERN.matcher(message);
        if (m.find()) return m.group(1);
        return null;
    }

    private static String extractProcess(String service) {

        int bracketIndex = service.indexOf("[");
        if (bracketIndex != -1)
            return service.substring(0, bracketIndex);

        return service;
    }

    private static String extractUsername(String message) {

        if (message.contains("for ")) {
            int index = message.indexOf("for ");
            String sub = message.substring(index + 4);
            String[] parts = sub.split(" ");
            return parts[0];
        }

        return null;
    }
}
