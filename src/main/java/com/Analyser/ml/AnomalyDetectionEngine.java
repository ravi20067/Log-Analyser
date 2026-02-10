package com.Analyser.ml;




import com.Analyser.model.SecurityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnomalyDetectionEngine {

    public static double calculateAnomalyScore(List<SecurityEvent> events) {

        if (events.isEmpty()) return 0.0;

        double score = 0.0;

        // 1️⃣ Frequency anomaly (too many events)
        if (events.size() > 30) score += 0.2;

        // 2️⃣ IP concentration anomaly
        Map<String, Integer> ipCount = new HashMap<>();
        for (SecurityEvent e : events) {
            if (e.sourceIp != null) {
                ipCount.merge(e.sourceIp, 1, Integer::sum);
            }
        }

        for (int count : ipCount.values()) {
            if (count > 10) {
                score += 0.25;
                break;
            }
        }

        // 3️⃣ Attack behavior anomaly
        for (SecurityEvent e : events) {
            if ("SQL_INJECTION".equals(e.action) ||
                    "XSS_ATTACK".equals(e.action)) {
                score += 0.15;
            }
        }

        // 4️⃣ Privilege escalation anomaly
        for (SecurityEvent e : events) {
            if ("SUDO".equals(e.action)) {
                score += 0.3;
                break;
            }
        }

        // 5️⃣ Kernel-level anomaly
        for (SecurityEvent e : events) {
            if ("KERNEL_EVENT".equals(e.action)) {
                score += 0.4;
                break;
            }
        }

        return Math.min(score, 1.0); // cap at 1.0
    }
}
