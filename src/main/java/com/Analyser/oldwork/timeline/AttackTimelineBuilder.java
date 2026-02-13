package com.Analyser.oldwork.timeline;



import com.Analyser.oldwork.model.SecurityEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AttackTimelineBuilder {

    public static List<String> buildTimeline(List<SecurityEvent> events) {

        List<String> timeline = new ArrayList<>();

        // sort by timestamp if available
        events.sort(Comparator.comparing(e ->
                e.timestamp == null ? "" : e.timestamp
        ));

        for (SecurityEvent e : events) {

            switch (e.action) {

                case "LOGIN_FAIL" ->
                        timeline.add("Login failed from IP " + e.sourceIp);

                case "SQL_INJECTION" ->
                        timeline.add("SQL Injection attempt on " + e.resource +
                                " from IP " + e.sourceIp);

                case "XSS_ATTACK" ->
                        timeline.add("XSS payload detected on " + e.resource +
                                " from IP " + e.sourceIp);

                case "SSH_LOGIN_SUCCESS" ->
                        timeline.add("SSH login success for user " +
                                e.username + " from IP " + e.sourceIp);

                case "SUDO" ->
                        timeline.add("Privilege escalation attempt by user " +
                                e.username + " using command " + e.resource);

                case "KERNEL_EVENT" ->
                        timeline.add("Kernel-level security event detected");

                default -> {
                    // ignore noise
                }
            }
        }

        return timeline;
    }
}

