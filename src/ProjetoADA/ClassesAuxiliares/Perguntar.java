package ProjetoADA.ClassesAuxiliares;

import java.util.Scanner;

public class Perguntar {

    public static final String OPCAO_INVALIDA = "Opção Inválida!";
    public static Scanner sc = new Scanner(System.in);

    public static boolean q(String msg, String complementoMsg) {

        if (msg.equalsIgnoreCase("S")) {

            return true;

        } else if (msg.equalsIgnoreCase("N")) {

            return false;

        } else {
            Imprimir.i(OPCAO_INVALIDA + "\n" + complementoMsg);
            msg = sc.nextLine();

            return q(msg, complementoMsg);

        }
    }
}
