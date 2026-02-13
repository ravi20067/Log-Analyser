package com.Analyser.oldwork.parse;



import com.Analyser.oldwork.detect.LogType;
import com.Analyser.oldwork.model.SecurityEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudoLogParser {

    private static final Pattern P = Pattern.compile(
            "sudo: (\\S+) .* USER=(\\S+) .* COMMAND=(.*)"
    );

    public static SecurityEvent parse(String line) {
        Matcher m = P.matcher(line);
        if (!m.find()) return null;

        SecurityEvent e = new SecurityEvent();
        e.username = m.group(1);
        e.action = "SUDO";
        e.resource = m.group(3);
        e.sourceType = LogType.SUDO;
        e.raw = line;

        return e;
    }
}

