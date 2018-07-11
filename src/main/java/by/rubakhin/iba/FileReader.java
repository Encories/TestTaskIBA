package by.rubakhin.iba;

import java.io.*;
import java.util.Scanner;

public class FileReader {

    public static final String OTPUTFILE_PATH = "src/main/resources/file_out.txt";
    final String regex = "[«a-zA-Z0-9]+[»|«|=]+[a-zA-Z0-9]+(»)?";

    public void Read(String value){

        File file = new File(value);

        System.out.println(file.getAbsolutePath());
        
        try (Scanner input = new Scanner(new File(file.getAbsolutePath()))) {

            String line;
            boolean flag = true;
            while (input.hasNextLine()){
                line  = input.nextLine();
                if (flag != line.matches(regex)){
                    flag = false;
                }
            }
            if (flag == true) {
                try (Scanner inputTrue = new Scanner(new File(value))) {
                    String lineTrue;

                    while (inputTrue.hasNextLine()) {

                        lineTrue = inputTrue.nextLine();
                        try (FileWriter out = new FileWriter(OTPUTFILE_PATH, true)) {
                            String tofile = lineTrue;
                            String[] subStr;
                            String delimeter = "=";
                            subStr = tofile.split(delimeter);
                            for (int j = 0; j < subStr.length; j++) {
                                out.write(subStr[j] + "\n");
                            }
                        } catch (IOException e) {
                            try (FileWriter fstream = new FileWriter("src/main/resources/file_err.txt")){
                                BufferedWriter out=new BufferedWriter(fstream);
                                out.write(e.toString());
                                out.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                }
            } else if (flag == false) {
                try (Scanner inputFalse = new Scanner(new File(value))) {
                    String lineFalse;
                    while (inputFalse.hasNextLine()) {
                        lineFalse = inputFalse.nextLine();
                        try (FileWriter out = new FileWriter(OTPUTFILE_PATH, true)) {
                            String tofile = lineFalse;
                            out.write(tofile + "\n");
                        } catch (IOException e) {
                            try (FileWriter fstream = new FileWriter("src/main/resources/file_err.txtt")){
                                BufferedWriter out=new BufferedWriter(fstream);
                                out.write(e.toString());
                                out.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }

                }

            }
        } catch (FileNotFoundException e) {
            try (FileWriter fstream = new FileWriter("src/main/resources/file_err.txt")){
                BufferedWriter out=new BufferedWriter(fstream);
                out.write(e.toString());
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
   }

}
