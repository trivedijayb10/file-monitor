package com.cds.cdstest.monitor;

import com.cds.cdstest.configuration.Constants;
import com.cds.cdstest.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class FileMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(FileMonitor.class);
    private static final String PREDEFINED_REGEX = "^CDS$";

    @Autowired
    private FileUtils fileUtils;

    @Scheduled(initialDelay = 11000, fixedRate = 20000)
    public void monitorFilesAndWriteSearchResult() throws IOException {

        writeSearchResultToFile(Constants.WRITER_FILE1_NAME, getRegexMatchCounter(Constants.WRITER_FILE1_NAME));
        writeSearchResultToFile(Constants.WRITER_FILE2_NAME, getRegexMatchCounter(Constants.WRITER_FILE2_NAME));
    }

    private void writeSearchResultToFile(String fileName, int regexMatchCounter) throws IOException {

        LOG.info("Regex match counter for file : " + fileName + " is " + regexMatchCounter + ", writing it to file: "
                + fileUtils.getAbsolutePath(Constants.SEARCH_RESULT_FILE_NAME));
        File searchResultsFile = fileUtils.getResourceFile(Constants.SEARCH_RESULT_FILE_NAME);
        fileUtils.writeToFile(searchResultsFile, Constants.DATE_TIME_FORMATTER.format(LocalDateTime.now()) + Constants.SPACE
                + fileName + " - " + regexMatchCounter);
    }

    private int getRegexMatchCounter(String fileName) throws IOException {

        Scanner fileScanner = new Scanner(fileUtils.getResourceFile(fileName));
        int regexMatchCounter = 0;

        while (fileScanner.hasNextLine()) {

            String lineFromFile = fileScanner.nextLine();

            if(lineFromFile != null || !lineFromFile.trim().isEmpty()) {
                String substring = lineFromFile.substring(lineFromFile.indexOf(Constants.SPACE) + 1);
                String stringToCompare = substring.substring(substring.indexOf(Constants.SPACE) + 1);
                if (Pattern.compile(PREDEFINED_REGEX).matcher(stringToCompare).find()) {
                    regexMatchCounter++;
                }
            }
        }
        return regexMatchCounter;
    }

}
