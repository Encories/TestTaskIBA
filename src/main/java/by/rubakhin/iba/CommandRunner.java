package by.rubakhin.iba;

import java.io.*;

public class CommandRunner {

    public static final String CMDFILE_PATH = "src/main/resources/cmd_out.txt";

    public void excCommand(String value) {

           ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe"  , value);
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while (true) {
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            try (FileWriter out = new FileWriter(CMDFILE_PATH, true)) {
                out.write(line + "\n");
            } catch (IOException e) {
                try (FileWriter fstream = new FileWriter("src/main/resources/cmd_err.txt")){
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
