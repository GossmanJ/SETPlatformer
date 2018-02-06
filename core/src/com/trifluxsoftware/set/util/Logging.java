package com.trifluxsoftware.set.util;

import java.io.*;
import java.util.Date;

public class Logging {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String OSPath;
    private static String LogName = "UN- " + "(" + System.getProperty("user.name") + ") " + "System_Log.log";

    private static boolean isWindows() {
        return (OS.contains("win"));
    }
    private static boolean isMac() {
        return (OS.contains("mac"));
    }
    private static boolean isUnix(){
        return (OS.contains("nux"));
    }

    public static BufferedWriter bw;
    private static FileWriter fw;
    public static PrintStream ps;
    private static BufferedReader br;

    public static void detectOS() {
        if (isWindows() || isMac() || isUnix()) {
            OSPath = System.getProperty("user.home") + "/TSD/";
        }else{
            throw new RuntimeException();
        }
    }

    public void onGameStart(){
        try {
            br = new BufferedReader(new FileReader(LogName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            String everything = sb.toString();
            fw = new FileWriter(LogName, true);
            bw = new BufferedWriter(fw);
            bw.append(everything);
            bw.append(new Date().toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogStart(){
        try{
            ps = new PrintStream(new FileOutputStream(new File("Console_Log.log")));
        }catch(IOException e){
            e.printStackTrace(ps);
        }
    }

    public static void close(){
        try {
            if(bw == null){
                bw = new BufferedWriter(fw);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createELog(){
        File triDir = new File(OSPath);
        // attempt to create the directory here
        triDir.mkdir();

        File logFile = new File(LogName);
        try {
            new FileOutputStream(logFile, false);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
