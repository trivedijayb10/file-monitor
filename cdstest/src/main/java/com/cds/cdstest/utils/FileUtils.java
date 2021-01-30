package com.cds.cdstest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    @Value( "${base.filepath}" )
    private String baseFilePath;

    @Autowired
    ResourceLoader resourceLoader;

    public void writeToFile(File fileToBeModified, String generatedString) {

        try (FileWriter fileWriter = new FileWriter(fileToBeModified, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(generatedString);
            bufferedWriter.newLine();
        } catch (IOException e) {
            LOG.error("Error while writing : " + generatedString + " to file: " + fileToBeModified.getName()
                    + ", Reason: " + e.getMessage(), e);
        }
    }

    public String getAbsolutePath(String fileName) {
        return baseFilePath + File.separator + fileName;
    }

    public File getResourceFile(String name) {

        File writerDirectory = new File(baseFilePath);
        if (!writerDirectory.exists()) {
            writerDirectory.mkdirs();
        }

        File writerFile = new File(baseFilePath + File.separator + name);
        if (!writerFile.exists()) {
            try {

                writerFile.createNewFile();
            } catch (IOException e) {
                LOG.error("Error while creating file : " + writerFile.getAbsolutePath() + ", Reason: " + e.getMessage(), e);
            }
        }
        return writerFile;
    }

}
