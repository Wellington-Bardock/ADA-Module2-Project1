package ProjetoADA.ConfiguracoesContas;

public class Usuario extends Conta{

    public Usuario(String nome, String documento, int tpConta, int nConta) {
        setNomeCliente(nome);
        setDocumentoCliente(documento);
        setTipoCliente(tpConta);
        setNumConta(nConta);

    }
}
