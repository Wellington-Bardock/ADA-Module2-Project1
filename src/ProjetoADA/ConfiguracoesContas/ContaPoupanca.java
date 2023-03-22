package ProjetoADA.ConfiguracoesContas;
import ProjetoADA.ClassesAuxiliares.Imprimir;

import static ProjetoADA.ConfiguracoesContas.ContaCorrente.TRANSACAO_EFETUADA;
import static ProjetoADA.ConfiguracoesContas.ContaCorrente.VALOR_INSUFICIENTE;
import static ProjetoADA.Aplicacao.*;


public class ContaPoupanca extends Conta implements MetodosConta{

    private double saldoCP;

    public double getSaldoCP() {return saldoCP;
    }

    public void setSaldoCP(double saldoCP) {this.saldoCP = saldoCP;
    }

    @Override
    public void depositar(int tpCliente, double valor) {

        saldoCP = saldoCP + valor;

        Imprimir.i(TRANSACAO_EFETUADA);
    }

    public ContaPoupanca() {
    }

    public ContaPoupanca(int NumConta, double saldoCP) {
        this.saldoCP = saldoCP;
        setNumConta(NumConta);
    }

    @Override
    public void sacar(int tpCliente, double valor) {

            if (saldoCP < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                saldoCP = saldoCP - valor;
                Imprimir.i(TRANSACAO_EFETUADA);
            }
        }

    @Override
    public void transferencia(int destinoTransferencia, int tpCliente, double valor) {

        if (destinoTransferencia == 1 && saldoCP >= valor) {

            saldoCP = saldoCP - valor;
            ci.setSaldoCI(ci.getSaldoCI() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && saldoCP >= valor) {

            saldoCP = saldoCP - valor;
            cc.setSaldoCC(cc.getSaldoCC()+valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else {
            Imprimir.i(VALOR_INVALIDO);
        }
    }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldoCP()));
    }
}
