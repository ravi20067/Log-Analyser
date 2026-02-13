package com.Analyser.parser;

import com.Analyser.ENUM.WebAttackType;
import com.Analyser.Entity.WebLog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebLogParser {

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) \\S+ \\S+ \\[(.*?)\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"(.*?)\" \"(.*?)\""
    );

    public static List<WebLog> parse(List<String> logLines , WebAttackType type) {

        List<WebLog> webLogs = new ArrayList<>();

        for (String logLine : logLines) {

            Matcher matcher = LOG_PATTERN.matcher(logLine);

            if (matcher.find()) {

                String ip = matcher.group(1);
                String timestamp = matcher.group(2);
                String method = matcher.group(3);
                String url = matcher.group(4);
                String protocol = matcher.group(5);
                int statusCode = Integer.parseInt(matcher.group(6));
                int responseSize = Integer.parseInt(matcher.group(7));
                String referrer = matcher.group(8);
                String userAgent = matcher.group(9);

                WebLog webLog = new WebLog(
                        ip,
                        timestamp,
                        method,
                        url,
                        protocol,
                        statusCode,
                        responseSize,
                        referrer,
                        userAgent,
                        type
                );

                webLogs.add(webLog);
            }
        }

        return webLogs;
    }
}
