package com.Analyser.fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {

    public static List<String> readLogFile(String filePath) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}

