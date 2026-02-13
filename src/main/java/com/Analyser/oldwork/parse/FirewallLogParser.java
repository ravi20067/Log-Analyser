package com.Analyser.oldwork.parse;

import com.Analyser.oldwork.detect.LogType;
import com.Analyser.oldwork.model.SecurityEvent;

public class FirewallLogParser {

    public static SecurityEvent parse(String line) {
        SecurityEvent e = new SecurityEvent();
        e.sourceType = LogType.FIREWALL;
        e.action = "FIREWALL_BLOCK";

        // Extract SRC IP
        int srcIndex = line.indexOf("SRC=");
        if (srcIndex != -1) {
            e.sourceIp = line.substring(srcIndex + 4).split(" ")[0];
        }

        e.raw = line;
        return e;
    }
}
