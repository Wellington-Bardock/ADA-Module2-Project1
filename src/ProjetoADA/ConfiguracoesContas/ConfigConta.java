package ProjetoADA.ConfiguracoesContas;

public class ConfigConta extends Conta {

    public static int gerarNConta () {

        double nConta = Math.random()*100;

        return (int)nConta;

    }

}
