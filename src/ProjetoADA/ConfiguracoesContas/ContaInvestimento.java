package ProjetoADA.ConfiguracoesContas;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.TaxasEJuros;

import static ProjetoADA.ClassesAuxiliares.StringConstants.*;

public class ContaInvestimento extends Conta implements MetodosConta {

    TaxasEJuros t = TaxasEJuros.TAXA;
    TaxasEJuros j = TaxasEJuros.JUROS;

    public ContaInvestimento(double saldoCI) {
        setSaldo(saldoCI);
    }

    public ContaInvestimento(int NumConta, double saldoCI) {
        setSaldo(saldoCI);
        setNumConta(NumConta);
    }

    @Override
    public void depositar(int tpCliente, double valor, int tpOperacao) {
        if (tpCliente == 2) {

            setSaldo(getSaldo() + (valor * (1 + j.getTaxasEJuros())));

        } else {
            setSaldo(getSaldo() + valor);
        }

        if(tpOperacao==1) {

            Imprimir.i(TRANSACAO_EFETUADA);

        }

    }

    @Override
    public void sacar(int tpCliente, double valor) {

        if (tpCliente == 2) {

            if (getSaldo() < valor * (1 + t.getTaxasEJuros())) {

                Imprimir.i(VALOR_INSUFICIENTE);
            } else {
                setSaldo(getSaldo() - (valor * (1 + t.getTaxasEJuros())));
                Imprimir.i(TRANSACAO_EFETUADA);
            }

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

        if (tpCliente == 2) {
            valorTransf = valor * (1 + t.getTaxasEJuros());
        } else {
            valorTransf = valor;
        }

        if (destinoTransferencia == 1 && getSaldo() >= valorTransf) {

            setSaldo(getSaldo() - valorTransf);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else if (destinoTransferencia == 2 && getSaldo() >= valorTransf) {

            setSaldo(getSaldo() - valorTransf);

            Imprimir.i(TRANSACAO_EFETUADA);

        } else {
            Imprimir.i(VALOR_INVALIDO);
        }
    }

    @Override
    public String toString() {
        return "ContaInvestimento{" +
                "saldo=" + getSaldo() +
                ", conta=" + getNumConta() +
                '}';
    }
}
