package com.Analyser.parse;



import com.Analyser.detect.LogType;
import com.Analyser.model.SecurityEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SshLogParser {

    private static final Pattern P = Pattern.compile(
            "Accepted password for (\\S+) from (\\S+)"
    );

    public static SecurityEvent parse(String line) {
        Matcher m = P.matcher(line);
        if (!m.find()) return null;

        SecurityEvent e = new SecurityEvent();
        e.username = m.group(1);
        e.sourceIp = m.group(2);
        e.action = "SSH_LOGIN_SUCCESS";
        e.sourceType = LogType.SSH;
        e.raw = line;

        return e;
    }
}
