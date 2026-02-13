package com.Analyser.parser;

import com.Analyser.ENUM.NetworkAttackType;
import com.Analyser.Entity.NetworkLog;

import java.util.*;
import java.util.regex.*;

public class NetworkLogParser {

    private static final Pattern HEADER_PATTERN = Pattern.compile(
            "^(\\w{3})\\s+(\\d{1,2})\\s+(\\d{2}:\\d{2}:\\d{2})\\s+(\\S+)\\s+([^:]+):\\s+(.*)$"
    );

    // Pattern for arrow traffic format
    private static final Pattern ARROW_PATTERN = Pattern.compile(
            "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)\\s*->\\s*(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)"
    );

    public static List<NetworkLog> parse(List<String> lines, NetworkAttackType attackType) {

        List<NetworkLog> logs = new ArrayList<>();

        for (String line : lines) {

            line = line.trim();
            if (line.isEmpty()) continue;

            Matcher headerMatcher = HEADER_PATTERN.matcher(line);

            if (!headerMatcher.matches()) continue;

            // Header extraction
            String month = headerMatcher.group(1);
            String day = headerMatcher.group(2);
            String date = month + " " + day;

            String time = headerMatcher.group(3);
            String device = headerMatcher.group(4);
            String service = headerMatcher.group(5);
            String message = headerMatcher.group(6);

            // Default values
            String srcIp = null;
            String dstIp = null;
            String proto = null;
            String spt = null;
            String dpt = null;

            // 1️⃣ Extract firewall-style values
            srcIp = extractKeyValue(message, "SRC=");
            dstIp = extractKeyValue(message, "DST=");
            proto = extractKeyValue(message, "PROTO=");
            spt = extractKeyValue(message, "SPT=");
            dpt = extractKeyValue(message, "DPT=");

            // 2️⃣ Extract arrow format traffic (Suricata style)
            Matcher arrowMatcher = ARROW_PATTERN.matcher(message);
            if (arrowMatcher.find()) {

                if (srcIp == null) srcIp = arrowMatcher.group(1);
                if (spt == null) spt = arrowMatcher.group(2);
                if (dstIp == null) dstIp = arrowMatcher.group(3);
                if (dpt == null) dpt = arrowMatcher.group(4);
            }

            // 3️⃣ Extract IP without port (ARP / DNS cases)
            if (srcIp == null) {
                Pattern ipPattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)");
                Matcher ipMatcher = ipPattern.matcher(message);
                if (ipMatcher.find()) {
                    srcIp = ipMatcher.group(1);
                }
            }

            NetworkLog log = new NetworkLog(
                    date,
                    time,
                    device,
                    service,
                    message,
                    srcIp,
                    dstIp,
                    proto,
                    spt,
                    dpt,
                    attackType
            );

            logs.add(log);
        }

        return logs;
    }

    private static String extractKeyValue(String message, String key) {

        Pattern pattern = Pattern.compile(key + "(\\S+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

}
