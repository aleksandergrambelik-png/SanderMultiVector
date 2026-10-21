package com.sander;

public class LinuxBanner {
    public static void print() {
        System.out.println();
        // Logo Tux (Linux) - Cyan
        System.out.println(Color.CYAN + "       _.-;;-._" + Color.RESET);
        System.out.println(Color.CYAN + "'-..-'|   ||   |" + Color.RESET);
        System.out.println(Color.CYAN + "'-..-'|_.-;;-._|" + Color.RESET);
        System.out.println(Color.CYAN + "'-..-'|   ||   |" + Color.RESET);
        System.out.println(Color.CYAN + "'-..-'|_.-''-._|" + Color.RESET);
        
        System.out.println();
        // Judul
        System.out.println(Color.GREEN + Color.BOLD + "==========================================" + Color.RESET);
        System.out.println(Color.GREEN + Color.BOLD + "  SANDER GENESIS MULTI-VEKTOR v1.8" + Color.RESET);
        System.out.println(Color.WHITE + "  Hybrid Bug Bounty Arsenal" + Color.RESET);
        System.out.println(Color.GREEN + Color.BOLD + "==========================================" + Color.RESET);
        System.out.println();
    }
    
    public static void printPlatforms() {
        System.out.println(Color.YELLOW + " [PLATFORM SUPPORT]" + Color.RESET);
        System.out.println("  " + Color.CYAN + "[Termux]" + Color.RESET + "  " + Color.BLUE + "[Linux]" + Color.RESET + "  " + Color.PURPLE + "[Windows]" + Color.RESET + "  " + Color.WHITE + "[macOS]" + Color.RESET);
        System.out.println();
    }
    
    public static void printAICredits() {
        System.out.println(Color.GREEN + " [AI CO-PILOT & BUILT BY]" + Color.RESET);
        System.out.println("  " + Color.ORANGE + "DeepSeek V4.1 Pro" + Color.RESET + " (Logic & Debugging)");
        System.out.println("  " + Color.PURPLE + "Qwen 3.7 Plus" + Color.RESET + "    (Initial Concept)");
        System.out.println("  " + Color.CYAN + "Aleksander" + Color.RESET + "       (Creator & Architect)");
        System.out.println();
    }
    
    public static void printLogos() {
        // Logo Gundam (Simplified) - Purple
        System.out.println(Color.PURPLE + "      /\\_____/\\" + Color.RESET);
        System.out.println(Color.PURPLE + "     /  o   o  \\" + Color.RESET);
        System.out.println(Color.PURPLE + "    ( ==  ^  == )" + Color.RESET);
        System.out.println(Color.PURPLE + "     )         (" + Color.RESET);
        System.out.println(Color.PURPLE + "    (           )" + Color.RESET);
        System.out.println(Color.PURPLE + "   ( (  )   (  ) )" + Color.RESET);
        System.out.println(Color.PURPLE + "  (__(__)___(__)__)" + Color.RESET);
        System.out.println();
    }
}
