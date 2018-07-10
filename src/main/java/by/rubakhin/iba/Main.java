package by.rubakhin.iba;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        System.out.println("Введите флаг параметра:");
        String flag = in.nextLine();
        System.out.println("Введите значение параметра:");
        String value = in.nextLine();

        switch(flag)
        {
            case "-f":
                FileReader fileReader = new FileReader();
                fileReader.Read(value);
                break;
            case "-rk":
                System.out.println("Введите имя параметра реестра:");
                String regName = in.nextLine();
                RegistryKeyReader registryKeyReader = new RegistryKeyReader();
                registryKeyReader.RegistryKeyRead(value, regName);
                break;
            case "-cmd":
                CommandRunner commandRunner = new CommandRunner();
                commandRunner.excCommand(value);
                break;
            default :
                System.out.println("Неверный флаг параметра");
        }




    }





    }





