package com.Analyser.oldwork.decision;

public class HybridDecisionEngine {

    public static String finalVerdict(String risk, double anomalyScore) {

        if ("CRITICAL".equals(risk) && anomalyScore > 0.7) {
            return "CONFIRMED SYSTEM COMPROMISE";
        }

        if ("HIGH".equals(risk) && anomalyScore > 0.5) {
            return "LIKELY COMPROMISE";
        }

        if (anomalyScore > 0.6) {
            return "SUSPICIOUS ACTIVITY";
        }

        return "NO MAJOR THREAT";
    }
}

