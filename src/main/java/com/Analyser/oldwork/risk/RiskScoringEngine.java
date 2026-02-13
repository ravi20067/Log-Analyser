package com.Analyser.oldwork.risk;


import com.Analyser.oldwork.model.SecurityEvent;

import java.util.List;

public class RiskScoringEngine {

    public static String calculateRisk(List<SecurityEvent> events) {

        int score = 0;

        for (SecurityEvent e : events) {

            switch (e.action) {

                case "LOGIN_FAIL" -> score += 1;

                case "SQL_INJECTION", "XSS_ATTACK" -> score += 3;

                case "SSH_LOGIN_SUCCESS" -> score += 4;

                case "SUDO" -> score += 6;

                case "KERNEL_EVENT" -> score += 8;
            }
        }

        if (score >= 15) return "CRITICAL";
        if (score >= 10) return "HIGH";
        if (score >= 5)  return "MEDIUM";
        return "LOW";
    }
}

