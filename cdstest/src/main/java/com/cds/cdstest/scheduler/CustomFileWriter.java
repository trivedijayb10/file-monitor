package com.cds.cdstest.scheduler;

import com.cds.cdstest.configuration.Constants;
import com.cds.cdstest.utils.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class CustomFileWriter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomFileWriter.class);
    public static final String CDS_CONSTANT = "CDS";

    @Autowired
    private FileUtils fileUtils;

    @Scheduled(fixedRate = 10000)
    public void generateAndWriteRandomStringsToWriterFile1() {
        generateRandomStringAndWriteToFile(fileUtils.getResourceFile(Constants.WRITER_FILE1_NAME));
    }

    @Scheduled(fixedRate = 10000)
    public void generateAndWriteRandomStringsToWriterFile2() {
        generateRandomStringAndWriteToFile(fileUtils.getResourceFile(Constants.WRITER_FILE2_NAME));
    }

    private void generateRandomStringAndWriteToFile(File fileToBeModified) {

        String generatedString = getRandomStringByProbabilityCalculation();

        LocalDateTime now = LocalDateTime.now();
        fileUtils.writeToFile(fileToBeModified, Constants.DATE_TIME_FORMATTER.format(now) + Constants.SPACE + generatedString);
        LOG.info("String: " + generatedString + " is appended to file :" + fileToBeModified.getAbsolutePath());
    }

    private String getRandomStringByProbabilityCalculation() {

        int number = new Random().nextInt(101);
        String stringToWrite = null;

        /*For generating CDS string on probability higher than 50%*/
        if (number <= 55) {
            stringToWrite = CDS_CONSTANT;
        } else {
            stringToWrite = RandomStringUtils.random(10, true, false);
        }
        return stringToWrite;
    }
}
