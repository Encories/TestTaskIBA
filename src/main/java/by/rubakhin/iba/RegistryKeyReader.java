package by.rubakhin.iba;

import com.sun.jna.platform.win32.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class RegistryKeyReader {

    public static final String REGISTRYKEYSFILE_PATH = "C:\\Users\\pika4\\Documents\\10.07\\final\\1\\src\\main\\java\\by\\rubakhin\\iba\\registryKeys.txt";

    public void RegistryKeyRead(String value, String regName) {




        // Read a string
        String productName = Advapi32Util.registryGetStringValue(
                WinReg.HKEY_LOCAL_MACHINE, value /*"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion",  "ProductName" */, regName );

        System.out.printf("Product Name: %s%n", productName);

        PrintWriter out = null;
        try {
            out = new PrintWriter(REGISTRYKEYSFILE_PATH);
            out.println(productName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            //После чего мы должны закрыть файл
            //Иначе файл не запишется
            out.close();}




    }
}







