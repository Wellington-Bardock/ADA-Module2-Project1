package ProjetoADA.ConfiguracoesContas;
import ProjetoADA.ClassesAuxiliares.Imprimir;

import static ProjetoADA.ConfiguracoesContas.ContaCorrente.TRANSACAO_EFETUADA;
import static ProjetoADA.ConfiguracoesContas.ContaCorrente.VALOR_INSUFICIENTE;
import static ProjetoADA.Aplicacao.*;


public class ContaPoupanca extends Conta implements MetodosConta{

    @Override
    public void depositar(int tpCliente, double valor) {
        setSaldo(getSaldo() + valor);
        Imprimir.i(TRANSACAO_EFETUADA);
    }

    public ContaPoupanca() {
    }

    public ContaPoupanca(int NumConta, double ignoredSaldoCP) {
       setSaldo(getSaldo());
        setNumConta(NumConta);
    }

    @Override
    public void sacar(int tpCliente, double valor) {

            if (getSaldo() < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                setSaldo(getSaldo()- valor);
                Imprimir.i(TRANSACAO_EFETUADA);
            }
        }

    @Override
    public void transferencia(int destinoTransferencia, int tpCliente, double valor) {

        if (destinoTransferencia == 1 && getSaldo() >= valor) {

            setSaldo(getSaldo() - valor);


            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && getSaldo() >= valor) {

            setSaldo(getSaldo() - valor);
            setSaldo(getSaldo() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else {
            Imprimir.i(VALOR_INVALIDO);
        }
    }

    @Override
    public String toString() {
        return "ContaPoupança{" +
                "saldo=" + getSaldo() +
                ", conta=" + getNumConta() +
                '}';
    }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldo()));
    }
}
