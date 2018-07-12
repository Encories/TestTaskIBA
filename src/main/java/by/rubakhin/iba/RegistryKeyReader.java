package by.rubakhin.iba;



import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


class RegistryKeyReader {

    private static final Logger LOG = LogManager.getLogger(CommandReader.class);

    private static final String REGISTRY_KEYS_FILE_PATH = "src/main/resources/rk_out.txt";
    private static final String RK_ERROR_PATH = "src/main/resources/rk_err.txt";

    public void registryKeyRead(String value, String regName) {
        String productName = Advapi32Util.registryGetStringValue(
                /*"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion",  "ProductName" */
                WinReg.HKEY_LOCAL_MACHINE, value, regName );

        try (PrintWriter out = new PrintWriter(REGISTRY_KEYS_FILE_PATH)){
            out.println(productName);
        } catch (IOException e) {
            handleIOException(e, RK_ERROR_PATH, LOG);
        }
    }

    static void handleIOException(IOException e, String rkErrorPath, Logger log) {
        try (FileWriter fileWriter = new FileWriter(rkErrorPath)){
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(e.toString());
            bufferedWriter.close();
        } catch (IOException e1) {
            log.error(e1);
        }
    }
}







