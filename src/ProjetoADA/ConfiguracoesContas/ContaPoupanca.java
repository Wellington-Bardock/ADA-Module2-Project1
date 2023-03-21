package ProjetoADA.ConfiguracoesContas;
import ProjetoADA.ClassesAuxiliares.Imprimir;

import static ProjetoADA.ConfiguracoesContas.ContaCorrente.TRANSACAO_EFETUADA;
import static ProjetoADA.ConfiguracoesContas.ContaCorrente.VALOR_INSUFICIENTE;
import static ProjetoADA.Aplicacao.*;


public class ContaPoupanca extends Conta implements MetodosConta{

    protected double saldoPP;

    public double getSaldoPP() {
        return saldoPP;
    }

    public void setSaldoPP(double saldoPP) {
        this.saldoPP = saldoPP;
    }

    @Override
    public void depositar(int tpCliente, double valor) {

        saldoPP= saldoPP + valor;

        Imprimir.i(TRANSACAO_EFETUADA);
    }

    @Override
    public void sacar(int tpCliente, double valor) {

            if (saldoPP < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                saldoPP = saldoPP - valor;
                Imprimir.i(TRANSACAO_EFETUADA);
            }
        }

    @Override
    public void transferencia(int destinoTransferencia, int tpCliente, double valor) {

        if (destinoTransferencia == 1 && saldoPP >= valor) {

            saldoPP = saldoPP - valor;
            ci.setSaldoCI(ci.getSaldoCI() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && saldoPP >= valor) {

            saldoPP = saldoPP- valor;
            cc.setSaldoCC(cc.getSaldoCC()+valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else {
            Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
        }
    }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldoPP()));
    }
}
