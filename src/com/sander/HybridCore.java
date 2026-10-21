package com.sander;
import java.io.*;

public class HybridCore {
    public static String runPerlObfuscator(String payload) {
        try {
            String baseDir = System.getProperty("user.dir");
            String scriptPath = baseDir + "/scripts/obfuscator.pl";
            ProcessBuilder pb = new ProcessBuilder("perl", scriptPath);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            OutputStream os = p.getOutputStream();
            os.write(payload.getBytes());
            os.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String result = reader.readLine();
            p.waitFor();
            return (result != null) ? result : payload;
        } catch (Exception e) { return payload; }
    }

    public static String runCppFuzzer(String url, String payload) {
        StringBuilder fullResponse = new StringBuilder();
        try {
            String baseDir = System.getProperty("user.dir");
            String binPath = baseDir + "/bin/fuzzer_bin";
            ProcessBuilder pb = new ProcessBuilder(binPath, url, payload);
            pb.directory(new File(baseDir)); 
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            boolean inBody = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("BODY_START")) { inBody = true; continue; }
                if (line.contains("BODY_END")) { inBody = false; break; }
                if (inBody) fullResponse.append(line).append("\n");
            }
            p.waitFor();
        } catch (Exception e) { }
        return fullResponse.toString();
    }
}
