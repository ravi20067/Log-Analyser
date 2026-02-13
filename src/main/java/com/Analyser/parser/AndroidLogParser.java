package com.Analyser.parser;

import com.Analyser.ENUM.MobileAttackType;
import com.Analyser.Entity.AndroidLog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidLogParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^(\\d{2}-\\d{2})\\s+(\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\s+(\\d+)\\s+(\\d+)\\s+(\\w)\\s+(\\w+):\\s+(.*)"
    );

    public static List<AndroidLog> parse(List<String> lines , MobileAttackType attackType) {

        List<AndroidLog> logs = new ArrayList<>();

        for (String line : lines) {

            Matcher matcher = PATTERN.matcher(line);

            if (matcher.find()) {

                String date = matcher.group(1);
                String time = matcher.group(2);
                int pid = Integer.parseInt(matcher.group(3));
                int tid = Integer.parseInt(matcher.group(4));
                String level = matcher.group(5);
                String tag = matcher.group(6);
                String message = matcher.group(7);

                AndroidLog log = new AndroidLog(
                        date,
                        level,
                        pid,
                        time,
                        tid,
                        tag,
                        message,
                        attackType
                );

                logs.add(log);
            }
        }
        return logs;
    }
}
