package com.Analyser.oldwork.normalize;


import com.Analyser.oldwork.model.SecurityEvent;

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
        if ("WINDOWS_LOGIN_FAIL".equals(e.action))
            e.action = "AUTH_FAILURE";

        if ("DNS_TUNNELING".equals(e.action))
            e.action = "DNS_ATTACK";

        if ("FIREWALL_BLOCK".equals(e.action))
            e.action = "FIREWALL_ATTACK";

    }
}

