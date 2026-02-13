package com.Analyser.oldwork.rules;




import com.Analyser.oldwork.model.SecurityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEngine {

    public static void run(List<SecurityEvent> events) {

        Map<String, Integer> loginFails = new HashMap<>();

        for (SecurityEvent e : events) {

            if ("LOGIN_FAIL".equals(e.action)) {
                loginFails.merge(e.sourceIp, 1, Integer::sum);
                if (loginFails.get(e.sourceIp) >= 5) {
                    System.out.println("🚨 BRUTE FORCE from " + e.sourceIp);
                }
            }

            if ("SQL_INJECTION".equals(e.action)) {
                System.out.println("🚨 SQL INJECTION detected → " + e.raw);
            }

            if ("XSS_ATTACK".equals(e.action)) {
                System.out.println("🚨 XSS detected → " + e.raw);
            }

            if ("SUDO".equals(e.action)) {
                System.out.println("🚨 PRIVILEGE ESCALATION → " + e.raw);
            }
            if ("WINDOWS_LOGIN_FAIL".equals(e.action))
                e.action = "AUTH_FAILURE";

        }
    }
}

