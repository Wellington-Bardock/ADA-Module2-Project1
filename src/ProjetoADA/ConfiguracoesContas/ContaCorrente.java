package ProjetoADA.ConfiguracoesContas;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.TaxasEJuros;
import static ProjetoADA.Aplicacao.*;

public class ContaCorrente extends Conta implements MetodosConta {

    public static final String VALOR_INSUFICIENTE = "Valor Insuficiente, Transação Não Efetuada!\n";
    public static final String TRANSACAO_EFETUADA = "Transação Efetuada!\n";

    protected double saldoCC;
    TaxasEJuros t = TaxasEJuros.TAXA;
    TaxasEJuros j = TaxasEJuros.JUROS;

    public double getSaldoCC() {
        return saldoCC;
    }
    public void setSaldoCC(double saldoCC) {
        this.saldoCC = saldoCC;
    }

    @Override
    public void depositar(int tpCliente, double valor) {

        if(tpCliente==2) {

            saldoCC = saldoCC +(valor*(1+j.getTaxasEJuros()));

        } else {saldoCC= saldoCC + valor;}

        Imprimir.i(TRANSACAO_EFETUADA);
    }

    @Override
    public void sacar(int tpCliente, double valor) {

        if (tpCliente==2) {

            if(saldoCC<valor*(1+t.getTaxasEJuros())) {

                Imprimir.i(VALOR_INSUFICIENTE);}

            else {saldoCC= saldoCC-(valor*(1+t.getTaxasEJuros()));
                Imprimir.i(TRANSACAO_EFETUADA);}

        } else {

            if (saldoCC < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                saldoCC = saldoCC - valor;
                Imprimir.i(TRANSACAO_EFETUADA);
            }

        }
    }

    @Override
    public void transferencia(int tpCliente, int destinoTransferencia, double valor) {


        double valorTransf;

        if(tpCliente==2) {
            valorTransf = valor * (1 + t.getTaxasEJuros());
        } else {
            valorTransf = valor;}

        if (destinoTransferencia == 1 && saldoCC >= valorTransf) {

            saldoCC = saldoCC - valorTransf;
            cp.setSaldoPP(cp.getSaldoPP() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && saldoCC >= valorTransf) {

            saldoCC = saldoCC- valorTransf;
                ci.setSaldoCI(ci.getSaldoCI()+valor);

            Imprimir.i(TRANSACAO_EFETUADA);

            } else {
                Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
            }
        }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldoCC()));
    }

}
