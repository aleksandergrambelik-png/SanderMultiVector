package com.sander;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLogger {
    private static final String LOG_FILE = "sander_genesis_audit.jsonl";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static synchronized void log(String event, String target, String payload, String evidence, String status, int score) {
        String timestamp = DATE_FORMAT.format(new Date());
        
        // Format JSONL (satu baris per event)
        String json = String.format(
            "{\"timestamp\":\"%s\",\"event\":\"%s\",\"target\":\"%s\",\"payload\":\"%s\",\"evidence\":\"%s\",\"status\":\"%s\",\"score\":%d}",
            timestamp, event, target.replace("\"", "\\\""), payload.replace("\"", "\\\""), 
            evidence.replace("\"", "\\\""), status, score
        );

        // Tulis ke file (append)
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(json);
            bw.newLine();
        } catch (Exception e) {
            System.err.println("[!] Failed to write log: " + e.getMessage());
        }
    }
}
