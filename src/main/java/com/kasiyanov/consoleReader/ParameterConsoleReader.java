package com.kasiyanov.consoleReader;

import java.io.BufferedReader;
import java.io.IOException;

public interface ParameterConsoleReader {

    String getTrimmedLineWithParameters(BufferedReader reader) throws IOException;
}
