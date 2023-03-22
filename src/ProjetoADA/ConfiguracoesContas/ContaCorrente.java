package ProjetoADA.ConfiguracoesContas;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.TaxasEJuros;
import static ProjetoADA.Aplicacao.*;

public class ContaCorrente extends Conta implements MetodosConta {

    public static final String VALOR_INSUFICIENTE = "Valor Insuficiente, Transação Não Efetuada!\n";
    public static final String TRANSACAO_EFETUADA = "Transação Efetuada!\n";
    TaxasEJuros t = TaxasEJuros.TAXA;
    TaxasEJuros j = TaxasEJuros.JUROS;

    public ContaCorrente() {
    }

    public ContaCorrente(int NumConta, double saldoCC) {
        setSaldo(saldoCC);
        setNumConta(NumConta);
    }

    @Override
    public void depositar(int tpCliente, double valor) {

        if(tpCliente==2) {

            setSaldo(getSaldo()+ valor*(1+j.getTaxasEJuros()));

        } else {

            setSaldo(getSaldo() + valor);}

        Imprimir.i(TRANSACAO_EFETUADA);

    }

    @Override
    public void sacar(int tpCliente, double valor) {

        if (tpCliente==2) {

            if(getSaldo()<valor*(1+t.getTaxasEJuros())) {

                Imprimir.i(VALOR_INSUFICIENTE);}

            else {setSaldo(getSaldo()-(valor*(1+t.getTaxasEJuros())));
                Imprimir.i(TRANSACAO_EFETUADA);}

        } else {

            if (getSaldo() < valor) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                setSaldo(getSaldo() - valor);
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

        if (destinoTransferencia == 1 && getSaldo() >= valorTransf) {

            setSaldo(getSaldo() - valorTransf);
            setSaldo(getSaldo() + valor);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && getSaldo() >= valorTransf) {

            setSaldo(getSaldo()- valorTransf);
            setSaldo(getSaldo()+valor);

            Imprimir.i(TRANSACAO_EFETUADA);

            } else {
                Imprimir.i(VALOR_INVALIDO);
            }
        }

    @Override
    public void exibirSaldo() {
        Imprimir.i(String.format("Saldo: %.2f\n", getSaldo()));
    }

}
