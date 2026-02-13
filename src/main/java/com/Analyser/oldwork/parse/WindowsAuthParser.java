package com.Analyser.oldwork.parse;

import com.Analyser.oldwork.detect.LogType;
import com.Analyser.oldwork.model.SecurityEvent;

public class WindowsAuthParser {

    public static SecurityEvent parse(String line) {
        SecurityEvent e = new SecurityEvent();
        e.sourceType = LogType.WINDOWS_AUTH;

        if (line.contains("An account failed to log on")) {
            e.action = "WINDOWS_LOGIN_FAIL";
        }

        if (line.contains("Source Network Address:")) {
            e.sourceIp = line.split(":")[1].trim();
        }

        e.raw = line;
        return e;
    }
}

