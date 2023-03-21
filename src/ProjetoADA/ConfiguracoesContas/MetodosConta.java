package ProjetoADA.ConfiguracoesContas;

public interface MetodosConta {

    void depositar (int tpCliente, double valor);

    void sacar (int tpCliente, double valor);

    void transferencia (int destinoTransferencia, int tpCliente, double valor);

    void exibirSaldo ();

}
