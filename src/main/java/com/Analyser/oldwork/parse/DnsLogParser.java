package com.Analyser.oldwork.parse;

import com.Analyser.oldwork.detect.LogType;
import com.Analyser.oldwork.model.SecurityEvent;

public class DnsLogParser {

    public static SecurityEvent parse(String line) {
        SecurityEvent e = new SecurityEvent();
        e.sourceType = LogType.DNS;
        e.action = "DNS_QUERY";

        // Detect base64 patterns
        if (line.matches(".*\\([A-Za-z0-9+/=]+\\.evil\\.example\\).*")) {
            e.action = "DNS_TUNNELING";
        }

        // Extract source IP
        if (line.contains("client")) {
            e.sourceIp = line.split("client ")[1].split("#")[0];
        }

        e.raw = line;
        return e;
    }
}

