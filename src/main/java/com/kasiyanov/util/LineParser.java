package com.kasiyanov.util;

import com.kasiyanov.consoleReader.ParameterConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LineParser {

    private static final LineParser INSTANCE = new LineParser();

    public String getTrimmedLine(BufferedReader reader, ParameterConsoleReader consoleReader) throws IOException {
        return consoleReader.getTrimmedLineWithParameters(reader);
    }

    public List<String> getParameterValuesList(String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .map(it -> it.replaceAll("\\[|\\]", ""))
                .filter(it -> !(it.isEmpty()))
                .collect(Collectors.toList());
    }

    public String getParameterValue(String line, String parameterName) {
        return Arrays.stream(line.split("', '"))
                .filter(it -> it.contains(parameterName))
                .map(it -> it.replaceAll(parameterName + "=|'", "").trim())
                .findFirst()
                .orElse(null);
    }

    public static LineParser getInstance() {
        return INSTANCE;
    }
}
