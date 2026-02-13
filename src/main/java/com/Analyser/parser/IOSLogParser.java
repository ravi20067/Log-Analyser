package com.Analyser.parser;

import com.Analyser.ENUM.MobileAttackType;
import com.Analyser.Entity.IOSLog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOSLogParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^(\\w+)\\s+(\\d+)\\s+(\\d{2}:\\d{2}:\\d{2})\\s+(\\w+)\\s+([\\w\\.]+)\\[(\\d+)]\\s+<(\\w+)>:\\s+(.*)"
    );

    public static List<IOSLog> parse(List<String> lines, MobileAttackType attackType) {

        List<IOSLog> logs = new ArrayList<>();

        for (String line : lines) {

            Matcher matcher = PATTERN.matcher(line);

            if (matcher.find()) {

                String month = matcher.group(1);
                int day = Integer.parseInt(matcher.group(2));
                String time = matcher.group(3);
                String device = matcher.group(4);
                String process = matcher.group(5);
                int pid = Integer.parseInt(matcher.group(6));
                String level = matcher.group(7);
                String message = matcher.group(8);

                IOSLog log = new IOSLog(
                        month, day, time,
                        device, process, pid,
                        level, message,
                        attackType
                );

                logs.add(log);
            }
        }

        return logs;
    }
}

