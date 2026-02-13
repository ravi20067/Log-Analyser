package com.Analyser;


import com.Analyser.ENUM.LinuxAttackType;
import com.Analyser.ENUM.NetworkAttackType;
import com.Analyser.Entity.LinuxLog;
import com.Analyser.Entity.NetworkLog;
import com.Analyser.Repository.IOSLogRepo;
import com.Analyser.Repository.LinuxLogRepo;
import com.Analyser.Repository.NetworkLogRepo;
import com.Analyser.fileReader.LogFileReader;
import com.Analyser.parser.LinuxLogParser;
import com.Analyser.parser.NetworkLogParser;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {


        String path = "src/main/resources/logs/linux/";
        String filename ="all.log";

        List<String> lines = LogFileReader.readLogFile(path+filename);

        List<LinuxLog> logs = LinuxLogParser.parse(lines, LinuxAttackType.LOG_TAMPERING);

        LinuxLogRepo repo = new LinuxLogRepo();

        for (LinuxLog log : logs){
            repo.save(log);
        }

    }
}
