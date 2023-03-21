package ProjetoADA.ClassesAuxiliares;

public class Imprimir {

    public static void i (String msg) {

        System.out.println(msg);

    }

    public static void i (String msg, String obj) {

        System.out.printf(msg, obj);
    }

    public static void i (String msg, String obj1, String obj2) {

        System.out.printf(msg, obj1, obj2);
    }

    public static void i (String msg, String obj1, String obj2, String obj3, String obj4) {

        System.out.printf(msg, obj1, obj2);
    }


}
