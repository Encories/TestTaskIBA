package by.rubakhin.iba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOG.info("Введите флаг параметра:");
        String flag = in.nextLine();
        LOG.info("Введите значение параметра:");
        String value = in.nextLine();

        switch(flag) {
            case "-f":
                CommandReader fileReader = new CommandReader();
                fileReader.readCommandFromConsole(value);
                break;
            case "-rk":
                LOG.info("Введите имя параметра реестра:");
                String regName = in.nextLine();
                RegistryKeyReader registryKeyReader = new RegistryKeyReader();
                registryKeyReader.registryKeyRead(value, regName);
                break;
            case "-cmd":
                CommandRunner commandRunner = new CommandRunner();
                commandRunner.excCommand(value);
                break;
            default :
                LOG.info("Неверный флаг параметра");
        }
   }
}





