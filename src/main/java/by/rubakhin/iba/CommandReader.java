package by.rubakhin.iba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.Scanner;

class CommandReader {

    private static final Logger LOG = LogManager.getLogger(CommandReader.class);
    private static final String OUTPUT_FILE_PATH = "src/main/resources/file_out.txt";
    private static final String ERR_FILE_PATH = "src/main/resources/file_err.txt";
    private static final String REGEX = "[«a-zA-Z0-9]+[»|«|=]+[a-zA-Z0-9]+(»)?";


    void readCommandFromConsole(String value){
        File file = new File(value);
        LOG.info(file.getAbsolutePath());
        
        try (Scanner input = new Scanner(new File(file.getAbsolutePath()))) {
            String line;
            boolean flag = true;
            while (input.hasNextLine()){
                line  = input.nextLine();
                if (flag != line.matches(REGEX)){
                    flag = false;
                }
            }
            if (flag) {
                writeTrueLines(value);
            } else {
                writeFalseLines(value);
            }
        } catch (FileNotFoundException e) {
            writeErrorInFile(e);
        }
   }

    private void writeFalseLines(String value) throws FileNotFoundException {
        try (Scanner inputFalse = new Scanner(new File(value))) {
            String lineFalse;
            while (inputFalse.hasNextLine()) {
                lineFalse = inputFalse.nextLine();
                try (FileWriter out = new FileWriter(OUTPUT_FILE_PATH, true)) {
                    out.write(lineFalse + "\n");
                } catch (IOException e) {
                    writeErrorInFile(e);
                }
            }
        }
    }

    private void writeTrueLines(String value) throws FileNotFoundException {
        try (Scanner inputTrue = new Scanner(new File(value))) {
            String lineTrue;
            while (inputTrue.hasNextLine()) {
                lineTrue = inputTrue.nextLine();
                try (FileWriter out = new FileWriter(OUTPUT_FILE_PATH, true)) {
                    String[] subStr;
                    String delimiter = "=";
                    subStr = lineTrue.split(delimiter);
                    for (String aSubStr : subStr) {
                        out.write(aSubStr + "\n");
                    }
                } catch (IOException e) {
                    writeErrorInFile(e);
                }
            }
        }
    }

    private void writeErrorInFile(IOException e) {
        try (FileWriter fileWriter = new FileWriter(ERR_FILE_PATH)){
            BufferedWriter out=new BufferedWriter(fileWriter);
            out.write(e.toString());
            out.close();
        } catch (IOException e1) {
            LOG.warn(e1);
        }
    }
}
