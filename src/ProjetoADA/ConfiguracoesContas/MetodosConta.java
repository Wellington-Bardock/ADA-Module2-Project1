package ProjetoADA.ConfiguracoesContas;

public interface MetodosConta {

    void depositar(int tpCliente, double valor, int tpOperacao);

    void sacar(int tpCliente, double valor);

    void transferencia(int destinoTransferencia, int tpCliente, double valor);

}
