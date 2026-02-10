package com.Analyser.report;



import com.Analyser.model.SecurityEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SecurityReportGenerator {

    public static Map<String, Object> generate(
            List<SecurityEvent> events,
            List<String> timeline,
            Map<String, String> reasoning,
            String risk
    ) {

        Map<String, Object> report = new LinkedHashMap<>();

        report.put("system_compromised",
                reasoning.get("System Compromised"));

        report.put("severity", risk);

        report.put("entry_point",
                reasoning.get("Entry Point"));

        report.put("attack_type",
                reasoning.get("Attack Type"));

        report.put("privilege_escalation",
                reasoning.get("Privilege Escalation"));

        report.put("timeline", timeline);

        report.put("total_events", events.size());

        return report;
    }
}
