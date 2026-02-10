package com.Analyser.detect;


public class LogTypeDetector {

    public static LogType detect(String line) {

        if (line.contains("\"GET") || line.contains("\"POST"))
            return LogType.WEB;

        if (line.contains("sshd"))
            return LogType.SSH;

        if (line.contains("sudo:"))
            return LogType.SUDO;

        if (line.contains("kernel:") || line.contains("audit"))
            return LogType.KERNEL;

        return LogType.UNKNOWN;
    }
}

