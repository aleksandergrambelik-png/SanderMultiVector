package com.sander;

public class PseudoAI {
    private int threatScore = 0;
    private String lastDecision = "SAFE";
    private StringBuilder auditLog = new StringBuilder();

    public void analyzeResponse(String response, String marker, String payloadUsed, String targetUrl) {
        auditLog.append("\n--- AUDIT ENTRY ---\n");
        auditLog.append("Payload Tested : ").append(payloadUsed).append("\n");
        auditLog.append("Response Length: ").append(response.length()).append(" bytes\n");

        String evidence = "No marker or error found.";
        String status = "SAFE";

        if (response.contains(marker)) {
            threatScore = 100;
            lastDecision = "VULNERABLE_CONFIRMED";
            evidence = "Marker '" + marker + "' FOUND.";
            status = "VULNERABLE";
            auditLog.append("Evidence       : ").append(evidence).append("\n");
            auditLog.append("Confidence     : HIGH\n");
            auditLog.append("Status         : ").append(status).append("\n");
        } else if (response.toLowerCase().contains("sql syntax") || response.toLowerCase().contains("error")) {
            threatScore += 20;
            lastDecision = "SUSPICIOUS_ERROR";
            evidence = "Error message detected.";
            status = "SUSPICIOUS";
            auditLog.append("Evidence       : ").append(evidence).append("\n");
            auditLog.append("Confidence     : MEDIUM\n");
        } else {
            auditLog.append("Evidence       : ").append(evidence).append("\n");
            auditLog.append("Status         : ").append(status).append("\n");
        }

        // Tulis ke JSONL
        AuditLogger.log("TEST", targetUrl, payloadUsed, evidence, status, threatScore);
    }

    public int getScore() { return threatScore; }
    public String getDecision() { return lastDecision; }
    public String getAuditLog() { return auditLog.toString(); }
}
