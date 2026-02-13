package com.Analyser.oldwork.pdf;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class PdfReportGenerator {

    public static void generate(
            String filePath,
            Map<String, Object> report,
            List<String> timeline,
            double anomalyScore,
            String finalDecision
    ) {

        try (PDDocument doc = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(doc, page);

            // ===== LOAD UNICODE FONT =====
            InputStream fontStream =
                    PdfReportGenerator.class
                            .getResourceAsStream("/fonts/DejaVuSans.ttf");

            if (fontStream == null) {
                throw new RuntimeException("Font not found: DejaVuSans.ttf");
            }

            PDType0Font font = PDType0Font.load(doc, fontStream, true);

            float y = 770;

            // ===== TITLE =====
            cs.beginText();
            cs.setFont(font, 20);
            cs.newLineAtOffset(50, y);
            cs.showText("Security Compromise Analysis Report");
            cs.endText();

            y -= 40;

            // ===== SUMMARY =====
            y = section(cs, font, "Summary", y);
            y = line(cs, font, "System Compromised", report.get("system_compromised"), y);
            y = line(cs, font, "Severity", report.get("severity"), y);
            y = line(cs, font, "Final Decision", finalDecision, y);
            y = line(cs, font, "Anomaly Score", String.valueOf(anomalyScore), y);

            // ===== ATTACK DETAILS =====
            y = section(cs, font, "Attack Details", y);
            y = line(cs, font, "Entry Point", report.get("entry_point"), y);
            y = line(cs, font, "Attack Type", report.get("attack_type"), y);
            y = line(cs, font, "Privilege Escalation", report.get("privilege_escalation"), y);

            // ===== TIMELINE =====
            y = section(cs, font, "Attack Timeline", y);

            cs.setFont(font, 11);
            for (String step : timeline) {
                y -= 15;
                cs.beginText();
                cs.newLineAtOffset(60, y);
                cs.showText("• " + step);
                cs.endText();
            }

            cs.close();
            doc.save(filePath);

            System.out.println("📄 PDF Report Generated: " + filePath);

        } catch (IOException e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }

    // ===== SECTION HEADING =====
    private static float section(
            PDPageContentStream cs,
            PDType0Font font,
            String title,
            float y
    ) throws IOException {

        y -= 30;
        cs.beginText();
        cs.setFont(font, 14);
        cs.newLineAtOffset(50, y);
        cs.showText(title);
        cs.endText();
        return y - 10;
    }

    // ===== KEY : VALUE LINE =====
    private static float line(
            PDPageContentStream cs,
            PDType0Font font,
            String key,
            Object value,
            float y
    ) throws IOException {

        y -= 18;
        cs.beginText();
        cs.setFont(font, 11);
        cs.newLineAtOffset(60, y);
        cs.showText(key + " : " + value);
        cs.endText();
        return y;
    }
}
