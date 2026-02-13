package com.Analyser.oldwork.detect;


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
        if (line.contains("[UFW BLOCK]"))
            return LogType.FIREWALL;

        if (line.contains("Microsoft-Windows-Security-Auditing") ||
                line.contains("EventID: 4625"))
            return LogType.WINDOWS_AUTH;

        if (line.contains("named[") && line.contains("IN TXT"))
            return LogType.DNS;

        return LogType.UNKNOWN;
    }
}

