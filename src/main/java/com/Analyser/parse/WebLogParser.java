package com.Analyser.parse;

import com.Analyser.detect.LogType;
import com.Analyser.model.SecurityEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebLogParser {

    private static final Pattern P = Pattern.compile(
            "^(\\S+) .* \\[(.*?)\\] \"(GET|POST) (.*?) HTTP.*\" (\\d{3})"
    );

    public static SecurityEvent parse(String line) {
        Matcher m = P.matcher(line);
        if (!m.find()) return null;

        SecurityEvent e = new SecurityEvent();
        e.sourceIp = m.group(1);
        e.timestamp = m.group(2);
        e.action = m.group(3);
        e.resource = m.group(4);
        e.statusCode = Integer.parseInt(m.group(5));
        e.sourceType = LogType.WEB;
        e.raw = line;

        return e;
    }
}

