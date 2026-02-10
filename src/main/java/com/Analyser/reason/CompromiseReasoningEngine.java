package com.Analyser.reason;



import com.Analyser.model.SecurityEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CompromiseReasoningEngine {

    public static Map<String, String> analyze(List<SecurityEvent> events) {

        Map<String, String> result = new LinkedHashMap<>();

        boolean webAttack = false;
        boolean sshAccess = false;
        boolean privEsc = false;

        for (SecurityEvent e : events) {

            if ("SQL_INJECTION".equals(e.action) || "XSS_ATTACK".equals(e.action)) {
                webAttack = true;
            }

            if ("SSH_LOGIN_SUCCESS".equals(e.action)) {
                sshAccess = true;
            }

            if ("SUDO".equals(e.action)) {
                privEsc = true;
            }
        }

        // ENTRY POINT
        if (webAttack) {
            result.put("Entry Point", "Web Application");
        } else if (sshAccess) {
            result.put("Entry Point", "SSH Service");
        } else {
            result.put("Entry Point", "Unknown");
        }

        // ATTACK TYPE
        if (webAttack) {
            result.put("Attack Type", "SQL Injection / XSS");
        }

        if (sshAccess) {
            result.put("Lateral Movement", "SSH Login Success");
        }

        // PRIVILEGE
        if (privEsc) {
            result.put("Privilege Escalation", "User → Root");
        } else {
            result.put("Privilege Escalation", "No");
        }

        // FINAL VERDICT
        if (webAttack && sshAccess && privEsc) {
            result.put("System Compromised", "YES");
            result.put("Severity", "CRITICAL");
        } else {
            result.put("System Compromised", "PARTIAL / ATTEMPTED");
            result.put("Severity", "MEDIUM");
        }

        return result;
    }
}

