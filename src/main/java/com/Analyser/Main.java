package com.Analyser;

import com.Analyser.decision.HybridDecisionEngine;
import com.Analyser.detect.LogType;
import com.Analyser.detect.LogTypeDetector;
import com.Analyser.ingest.LogIngestionService;
import com.Analyser.ml.AnomalyDetectionEngine;
import com.Analyser.model.SecurityEvent;
import com.Analyser.normalize.EventNormalizer;
import com.Analyser.parse.KernelLogParser;
import com.Analyser.parse.SshLogParser;
import com.Analyser.parse.SudoLogParser;
import com.Analyser.parse.WebLogParser;
import com.Analyser.pdf.PdfReportGenerator;
import com.Analyser.reason.CompromiseReasoningEngine;
import com.Analyser.report.SecurityReportGenerator;
import com.Analyser.risk.RiskScoringEngine;
import com.Analyser.rules.RuleEngine;
import com.Analyser.timeline.AttackTimelineBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // ================= CONFIG =================
        Path logFile = Paths.get("src/main/resources/test.txt");

        // ================= STAGE 1: INGESTION =================
        List<String> rawLines = LogIngestionService.readLogs(logFile);

        // ================= STAGE 2–4: PARSE + NORMALIZE =================
        List<SecurityEvent> events = new ArrayList<>();

        for (String line : rawLines) {

            LogType type = LogTypeDetector.detect(line);
            SecurityEvent event = null;

            switch (type) {
                case WEB -> event = WebLogParser.parse(line);
                case SSH -> event = SshLogParser.parse(line);
                case SUDO -> event = SudoLogParser.parse(line);
                case KERNEL -> event = KernelLogParser.parse(line);
                default -> { /* ignore unknown logs */ }
            }

            if (event != null) {
                EventNormalizer.normalize(event);
                events.add(event);
            }
        }

        // ================= STAGE 5: RULE ENGINE =================
        RuleEngine.run(events);

        // ================= STAGE 6: ATTACK TIMELINE =================
        List<String> timeline = AttackTimelineBuilder.buildTimeline(events);
        System.out.println("\n===== ATTACK TIMELINE =====");
        timeline.forEach(System.out::println);

        // ================= STAGE 7: COMPROMISE REASONING =================
        Map<String, String> reasoning =
                CompromiseReasoningEngine.analyze(events);

        System.out.println("\n===== COMPROMISE ANALYSIS =====");
        reasoning.forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        // ================= STAGE 8: RISK SCORING =================
        String risk = RiskScoringEngine.calculateRisk(events);
        System.out.println("\n===== RISK SCORE =====");
        System.out.println("Overall Risk Level : " + risk);

        // ================= STAGE 9: FINAL REPORT (DATA) =================
        Map<String, Object> finalReport =
                SecurityReportGenerator.generate(
                        events, timeline, reasoning, risk
                );

        System.out.println("\n===== FINAL SECURITY REPORT =====");
        finalReport.forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        // ================= STAGE 10: ML ANOMALY =================
        double anomalyScore =
                AnomalyDetectionEngine.calculateAnomalyScore(events);

        System.out.println("\n===== ML ANOMALY ANALYSIS =====");
        System.out.println("Anomaly Score : " + anomalyScore);

        // ================= STAGE 11: FINAL DECISION =================
        String finalDecision =
                HybridDecisionEngine.finalVerdict(risk, anomalyScore);

        System.out.println("\n===== FINAL DECISION =====");
        System.out.println(finalDecision);

        // ================= STAGE 12: PDF GENERATION =================
        PdfReportGenerator.generate(
                "Security_Report.pdf",
                finalReport,
                timeline,
                anomalyScore,
                finalDecision
        );

        System.out.println("\n📄 PDF GENERATED SUCCESSFULLY");
    }
}