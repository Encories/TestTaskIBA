package by.rubakhin.iba;

import com.sun.jna.platform.win32.*;

import java.io.*;


public class RegistryKeyReader {
    public static final String REGISTRYKEYSFILE_PATH = "src/main/resources/registryKeys.txt";
    public void RegistryKeyRead(String value, String regName) {
        String productName = Advapi32Util.registryGetStringValue(
                /*"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion",  "ProductName" */
                WinReg.HKEY_LOCAL_MACHINE, value, regName );

        try (PrintWriter out = new PrintWriter(REGISTRYKEYSFILE_PATH)){

            out.println(productName);
        } catch (FileNotFoundException e) {
            try (FileWriter fstream = new FileWriter("src/main/resources/rk_err.txt")){
                BufferedWriter bufferedWriter=new BufferedWriter(fstream);
                bufferedWriter.write(e.toString());
                bufferedWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}







