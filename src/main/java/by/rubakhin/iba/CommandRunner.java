package by.rubakhin.iba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

class CommandRunner {
    private static final Logger LOG = LogManager.getLogger(CommandRunner.class);
    private static final String CMD_FILE_PATH = "src/main/resources/cmd_out.txt";
    private static final String ERR_FILE_PATH = "src/main/resources/cmd_err.txt";

    public void excCommand(String value) {

           ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe"  , value);
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            LOG.error(e);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while (true) {
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                LOG.error(e);
            }
            if (line == null) {
                break;
            }
            try (FileWriter out = new FileWriter(CMD_FILE_PATH, true)) {
                out.write(line + "\n");
            } catch (IOException e) {
                RegistryKeyReader.handleIOException(e, ERR_FILE_PATH, LOG);
            }
        }
    }
}
