package com.sander;

import java.util.Scanner;

public class InteractiveMenu {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            LinuxBanner.print();
            LinuxBanner.printPlatforms();
            LinuxBanner.printAICredits();
            
            System.out.println(Color.YELLOW + " Select an attack mode:" + Color.RESET);
            System.out.println(" 1. SQL Injection (SQLi)");
            System.out.println(" 2. Cross-Site Scripting (XSS)");
            System.out.println(" 3. Remote Code Execution (RCE)");
            System.out.println(" 4. Local File Inclusion (LFI)");
            System.out.println(" 5. API Injection");
            System.out.println(" 6. Typo Mapping (Hidden Endpoints)");
            System.out.println(" 7. Show Logos (Gundam & Co.)");
            System.out.println(" 0. Exit");
            System.out.println("------------------------------------------");
            System.out.print(Color.CYAN + " Enter choice (0-7): " + Color.RESET);
            
            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) {
                System.out.println(Color.RED + "[*] Exiting SanderGenesis. Stay safe!" + Color.RESET);
                break;
            }
            
            if (choice.equals("7")) {
                LinuxBanner.printLogos();
                System.out.print("\nPress Enter to return to menu...");
                scanner.nextLine();
                continue;
            }
            
            String mode;
            switch (choice) {
                case "1": mode = "sqli"; break;
                case "2": mode = "xss"; break;
                case "3": mode = "rce"; break;
                case "4": mode = "lfi"; break;
                case "5": mode = "api"; break;
                case "6": mode = "typo"; break;
                default:
                    System.out.println(Color.RED + "[!] Invalid choice." + Color.RESET);
                    continue;
            }
            
            System.out.print(Color.CYAN + " Enter Target URL (e.g., http://127.0.0.1:8080/?q=): " + Color.RESET);
            String targetUrl = scanner.nextLine().trim();
            
            if (targetUrl.isEmpty()) {
                System.out.println(Color.RED + "[!] URL cannot be empty." + Color.RESET);
                continue;
            }
            
            System.out.println(Color.YELLOW + "\n[*] Starting attack on: " + targetUrl + " | Mode: " + mode.toUpperCase() + Color.RESET);
            runAttack(mode, targetUrl);
            
            System.out.print("\nPress Enter to return to menu...");
            scanner.nextLine();
        }
        scanner.close();
    }
    
    private static void runAttack(String mode, String targetUrl) {
        String marker = "SNDER_" + (System.currentTimeMillis() % 1000);
        String[] payloads;
        
        switch (mode) {
            case "sqli": payloads = Payloads.SQLI; break;
            case "xss": payloads = Payloads.XSS; break;
            case "rce": payloads = Payloads.RCE; break;
            case "lfi": payloads = Payloads.LFI; break;
            case "api": payloads = Payloads.API_INJ; break;
            case "typo": payloads = Payloads.TYPO_MAP; break;
            default: return;
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
                if (ai.getDecision().equals("VULNERABLE_CONFIRMED")) {
                    System.out.println(Color.GREEN + "[!!!] VULNERABILITY CONFIRMED!" + Color.RESET);
                    break;
                }
            } else {
                System.out.println(Color.RED + "[AI] Response empty." + Color.RESET);
                AuditLogger.log("ERROR", targetUrl, basePayload, "Empty response", "ERROR", 0);
            }
            System.out.println(Color.WHITE + "-----------------------------------------" + Color.RESET);
        }
        
        System.out.println("\n" + Color.GREEN + "==========================================" + Color.RESET);
        System.out.println(Color.GREEN + " FINAL REPORT                             " + Color.RESET);
        System.out.println(Color.GREEN + "==========================================" + Color.RESET);
        System.out.println(ai.getAuditLog());
        System.out.println(Color.YELLOW + " Decision : " + ai.getDecision() + Color.RESET);
        System.out.println(Color.YELLOW + " Score    : " + ai.getScore() + "/100" + Color.RESET);
        System.out.println(Color.CYAN + " Log File : sander_genesis_audit.jsonl" + Color.RESET);
        System.out.println(Color.GREEN + "==========================================" + Color.RESET);
    }
}
