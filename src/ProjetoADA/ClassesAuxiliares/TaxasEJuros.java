package ProjetoADA.ClassesAuxiliares;

public enum TaxasEJuros {

    JUROS(0.02),
    TAXA(0.005);

    private final double taxasEJuros;

    TaxasEJuros(double taxasEJuros) {
        this.taxasEJuros = taxasEJuros;
    }

    public double getTaxasEJuros() {
        return taxasEJuros;
    }
}
