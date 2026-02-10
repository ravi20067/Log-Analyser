package com.Analyser.normalize;


import com.Analyser.model.SecurityEvent;

public class EventNormalizer {

    public static void normalize(SecurityEvent e) {

        if (e.action == null) return;

        if (e.action.equals("POST") && e.statusCode == 401) {
            e.action = "LOGIN_FAIL";
        }

        if (e.resource != null) {
            String r = e.resource.toLowerCase();
            if (r.contains("union select") || r.contains("'--"))
                e.action = "SQL_INJECTION";

            if (r.contains("<script") || r.contains("onerror"))
                e.action = "XSS_ATTACK";
        }
    }
}

