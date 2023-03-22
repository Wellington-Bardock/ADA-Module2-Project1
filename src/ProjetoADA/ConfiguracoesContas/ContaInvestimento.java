package ProjetoADA.ConfiguracoesContas;
import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.TaxasEJuros;

import static ProjetoADA.ConfiguracoesContas.ContaCorrente.TRANSACAO_EFETUADA;
import static ProjetoADA.ConfiguracoesContas.ContaCorrente.VALOR_INSUFICIENTE;
import static ProjetoADA.Aplicacao.*;

public class ContaInvestimento extends Conta implements MetodosConta{

    protected double saldoCI;

    TaxasEJuros t = TaxasEJuros.TAXA;
    TaxasEJuros j = TaxasEJuros.JUROS;

    public double getSaldoCI() {
        return saldoCI;
    }
    public void setSaldoCI(double saldoCI) {
        this.saldoCI = saldoCI;
    }

    public ContaInvestimento() {
    }

    public ContaInvestimento(int NumConta, double saldoCI) {
        this.saldoCI = saldoCI;
        setNumConta(NumConta);
    }

    @Override
    public void depositar(int tpCliente, double valor) {
        if(tpCliente==2) {

            saldoCI = saldoCI +(valor*(1+j.getTaxasEJuros()));

        } else {saldoCI= saldoCI + valor;}

        Imprimir.i(TRANSACAO_EFETUADA);
    }

    @Override
    public void sacar(int tpCliente, double valor) {

        if (tpCliente==2) {

            if(saldoCI<valor*(1+t.getTaxasEJuros())) {

                Imprimir.i(VALOR_INSUFICIENTE);}

            else {saldoCI= saldoCI-(valor*(1+t.getTaxasEJuros()));
                Imprimir.i(TRANSACAO_EFETUADA);}

        } else {

            if (saldoCI < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                saldoCI = saldoCI - valor;
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

        if (destinoTransferencia == 1 && saldoCI >= valorTransf) {

            saldoCI = saldoCI - valorTransf;
            cp.setSaldoCP(cp.getSaldoCP() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && saldoCI >= valorTransf) {

            saldoCI = saldoCI- valorTransf;
            cc.setSaldoCC(cc.getSaldoCC()+valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else {
            Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
        }
    }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldoCI()));
    }
}
