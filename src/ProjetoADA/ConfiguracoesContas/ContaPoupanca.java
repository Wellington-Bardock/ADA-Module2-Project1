package ProjetoADA.ConfiguracoesContas;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import static ProjetoADA.ClassesAuxiliares.StringConstants.*;

public class ContaPoupanca extends Conta implements MetodosConta {

    @Override
    public void depositar(int tpCliente, double valor, int tpOperacao) {
        setSaldo(getSaldo() + valor);

        if (tpOperacao == 1) {

            Imprimir.i(TRANSACAO_EFETUADA);

        }

    }

    public ContaPoupanca(double ignoredSaldoCP) {
        setSaldo(getSaldo());
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
            setSaldo(getSaldo() - valor);
            Imprimir.i(TRANSACAO_EFETUADA);
        }
    }

    @Override
    public void transferencia(int tpCliente, int destinoTransferencia, double valor) {

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
}
