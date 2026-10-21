package com.sander;

public class Main {
    public static void main(String[] args) {
        // CLI Mode (Non-interactive)
        if (args.length >= 2) {
            String mode = args[0].toLowerCase();
            String targetUrl = args[1];
            String marker = "SNDER_" + (System.currentTimeMillis() % 1000);
            
            String[] payloads;
            switch (mode) {
                case "sqli": payloads = Payloads.SQLI; break;
                case "xss": payloads = Payloads.XSS; break;
                case "rce": payloads = Payloads.RCE; break;
                case "lfi": payloads = Payloads.LFI; break;
                case "api": payloads = Payloads.API_INJ; break;
                case "typo": payloads = Payloads.TYPO_MAP; break;
                default: System.out.println(Color.RED + "[!] Unknown mode." + Color.RESET); return;
            }
            
            PseudoAI ai = new PseudoAI();
            for (String basePayload : payloads) {
                System.out.println(Color.WHITE + ">>> Testing Payload: " + basePayload + Color.RESET);
                String finalPayload = basePayload;
                if (!mode.equals("typo")) {
                    finalPayload = HybridCore.runPerlObfuscator(basePayload + " " + marker);
                }
                String realResponse = HybridCore.runCppFuzzer(targetUrl, finalPayload);
                if (realResponse != null && !realResponse.isEmpty()) {
                    ai.analyzeResponse(realResponse, marker, basePayload, targetUrl);
                    if (ai.getDecision().equals("VULNERABLE_CONFIRMED")) break;
                } else {
                    AuditLogger.log("ERROR", targetUrl, basePayload, "Empty response", "ERROR", 0);
                }
            }
            System.out.println("\nDecision: " + ai.getDecision() + " | Score: " + ai.getScore() + "/100");
            return;
        }
        
        // Interactive Mode
        InteractiveMenu.start();
    }
}
