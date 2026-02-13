package com.Analyser.oldwork.parse;


import com.Analyser.oldwork.detect.LogType;
import com.Analyser.oldwork.model.SecurityEvent;

public class KernelLogParser {

    public static SecurityEvent parse(String line) {
        SecurityEvent e = new SecurityEvent();
        e.action = "KERNEL_EVENT";
        e.resource = line;
        e.sourceType = LogType.KERNEL;
        e.raw = line;
        return e;
    }
}
