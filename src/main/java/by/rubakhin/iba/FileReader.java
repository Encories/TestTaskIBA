package by.rubakhin.iba;

import java.io.*;
import java.util.Scanner;

public class FileReader {
 //   public static final String INPUTFILE_PATH = "C:\Users\pika4\Documents\10.07\final\1\src\main\resources\input.txt";
    public static final String OTPUTFILE_PATH = "src/main/resources/file_out.txt";
    final String regex = "[«a-zA-Z0-9]+[»|«|=]+[a-zA-Z0-9]+(»)?";

    public void Read(String value){

        File file = new File(value);

        System.out.println(file.getAbsolutePath());
        
        try (Scanner input = new Scanner(new File(file.getAbsolutePath()))) {

            String line;
            boolean flag = true;
            while (input.hasNextLine()){
                // boolean flag = true;
                line  = input.nextLine();
                if (flag != line.matches(regex)){
                    flag = false;
                }
            }

            if (flag == true) {
                try (Scanner input2 = new Scanner(new File(value))) {
                    String line2 = null;

                    while (input2.hasNextLine()) {

                        line2 = input2.nextLine();
                        try (FileWriter out = new FileWriter(OTPUTFILE_PATH, true)) {
                            String tofile = line2;

                            String[] subStr;
                            String delimeter = "="; // Разделитель
                            subStr = tofile.toString().split(delimeter); // Разделения строки str с помощью метода split()
                            for (int j = 0; j < subStr.length; j++) {
                                System.out.println(subStr[j]);
                                //Записываем текст у файл
                                out.write(subStr[j] + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            } else if (flag == false) {
                try (Scanner input3 = new Scanner(new File(value))) {
                    String line3;
                    while (input3.hasNextLine()) {

                        line3 = input3.nextLine();
                        try (FileWriter out = new FileWriter(OTPUTFILE_PATH, true)) {
                            System.out.println("It is not a phone number!");

                            String tofile = line3;
                            out.write(tofile + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
