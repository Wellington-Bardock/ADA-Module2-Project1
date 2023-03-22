package ProjetoADA;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.Perguntar;
import ProjetoADA.ConfiguracoesContas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    public static final String MENU_INICIAL_PF = """
            Por favor, informar qual o tipo de conta:
            1 - Conta Corrente
            2 - Conta Investimento
            3 - Conta Poupança""";

    public static final String MENU_OPERACAO = """
            Por favor, selecionar a operação:
            1 - Depósito
            2 - Saque
            3 - Transferencia""";

    public static final String MENU_NOME = "Por favor, informar o seu nome:";
    public static final String MENU_CONTA = "Por favor informar a sua conta:\n";
    public static final String INTRODUCAO_NOME = "Olá %s!\n";
    public static final String VALOR_DE_DEPOSITO = "Qual o valor de depósito?";
    public static final String VALOR_DE_SAQUE = "Qual o valor de saque?";
    public static final String REALIZAR_OUTRA_OPERACAO = "Gostaria de realizar outra operação? (S/N)";
    public static final String VALOR_INVALIDO_TENTE_NOVAMENTE = "Valor Invalido!";
    public static final String CHECAGEM_SALDO = "Gostaria de ver o seu saldo? (S/N)";
    public static final String INTRODUCAO_APP = """
            Bem Vindo ao APP Banco! Por favor, informar a opção desejada!
             1 - Criar Conta
             2 - Realizar Login""";

    public static final String PRIMEIRO_MENU = """
            Por favor, informar a opção desejada!
             1 - Criar Conta
             2 - Realizar Login""";

    public static final String INFORMAR_DOCUMENTO = "Por favor, informar o %s:\n";
    public static final String INFORMAR_TP_CLIENTE = """
            Por favor, informar qual o tipo de conta:
            1 - Conta Física
            2 - Conta Juridica""";
    public static final String VALOR_TRANSFERENCIA = "Qual valor você deseja transferir?";
    public static final String DESTINO_TRANSFERENCIA = """
            Por favor, informar qual conta irá receber"
            1 - %s
            2 - %s
            """;
    public static final String CRIACAO_DE_CONTA = "Conta Criada com sucesso, Nº%s!\n";
    public static final String VALOR_INVESTIMENTO = "Qual será o valor investido?";
    public static final String MENU_RAZAO_SOCIAL = "Por favor, informar a razão social:";
    public static final String CONTA_CORRENTE = "Conta Corrente";
    public static final String CONTA_INVESTIMENTO = "Conta Investimento";
    public static final String CONTA_POUPANCA = "Conta Poupança";
    public static final String DADOS_CONTA = """
            Nome: %s
            Nº da Conta: %s
            """;
    public static final String CPF = "CPF";
    public static final String CNPJ = "CNPJ";
    public static final String DESLOGAR = "Gostaria de realizar deslogar?";
    public static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada!";
    public static final String MENU_INICIAL_PJ = """
            Por favor, informar qual o tipo de conta:
            1 - Conta Corrente
            2 - Conta Investimento""";
    public static Scanner sc = new Scanner(System.in);
    public static ContaCorrente cc = new ContaCorrente();
    public static ContaPoupanca cp = new ContaPoupanca();
    public static ContaInvestimento ci = new ContaInvestimento();
    static boolean checkconta;
    static boolean loop;
    static boolean checkSaldo;
    private static String nomeCliente;
    private static int NumConta;
    private static int tpCliente;
    private static int menuIntroducao;
    private static String docCliente;
    private static int menuInicial;
    private static int menuOperacao;
    private static final List<Usuario> UList = new ArrayList<>();
    private static final List<ContaCorrente> ListSaldoCC = new ArrayList<>();
    private static final List<ContaPoupanca> ListSaldoCP = new ArrayList<>();
    private static final List<ContaInvestimento> ListSaldoCI = new ArrayList<>();

    public static void main(String[] args) {

        Imprimir.i(INTRODUCAO_APP);

        do try {

            // Try catch referente a primeira opção do menu;
            do try {

                checkconta = true;
                menuIntroducao = Integer.parseInt(sc.nextLine());

                if (menuIntroducao != 1 && menuIntroducao != 2) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    Imprimir.i(PRIMEIRO_MENU);
                    checkconta = false;
                }

            } catch (NumberFormatException e) {
                Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                Imprimir.i(PRIMEIRO_MENU);
                checkconta = false;

            } while (!checkconta);

            if (menuIntroducao == 1) {

                do try {

                    checkconta = true;
                    Imprimir.i(INFORMAR_TP_CLIENTE);
                    tpCliente = Integer.parseInt(sc.nextLine());

                    if (tpCliente != 1 && tpCliente != 2) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;

                } while (!checkconta);

                if (tpCliente == 1) {

                    Imprimir.i(MENU_NOME);

                } else {
                    Imprimir.i(MENU_RAZAO_SOCIAL);

                }
                nomeCliente = sc.nextLine();

                if (tpCliente == 1) {
                    Imprimir.i(INFORMAR_DOCUMENTO, CPF);

                } else if (tpCliente == 2) {
                    Imprimir.i(INFORMAR_DOCUMENTO, CNPJ);
                }

                do try {

                    checkconta = true;
                    docCliente = sc.nextLine();

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;

                } while (!checkconta);

                NumConta = ConfigConta.gerarNConta();

                Usuario U = new Usuario(nomeCliente, docCliente, tpCliente, NumConta);
                UList.add(U);

                Imprimir.i(CRIACAO_DE_CONTA, String.valueOf(NumConta));

                ContaCorrente cc = new ContaCorrente(NumConta, 0);
                ListSaldoCC.add(cc);

                ContaPoupanca cp = new ContaPoupanca(NumConta, 0);
                ListSaldoCP.add(cp);

                ContaInvestimento ci = new ContaInvestimento(NumConta, 0);
                ListSaldoCI.add(ci);

                //Caso escolha opção de realizar ‘login’:
            } else {

                // Do while e try catch referente ao numero da conta
                do try {

                    checkconta = true;

                    Imprimir.i(MENU_CONTA);
                    int NumContalogin = Integer.parseInt(sc.nextLine());

                    for (int i = 0; i <= UList.size(); i++) {

                        Usuario contai = UList.get(i);

                        if (contai.getNumConta() == NumContalogin) {

                            nomeCliente = contai.getNomeCliente();
                            docCliente = contai.getDocumentoCliente();
                            tpCliente = contai.getTipoCliente();

                            break;

                        }

                    }

                    for (int i = 0; i <= ListSaldoCC.size(); i++) {

                        ContaCorrente ccl = ListSaldoCC.get(i);

                        if (ccl.getNumConta() == NumConta) {

                            cc.setSaldoCC(ccl.getSaldoCC());

                        }

                    }

                    for (int i = 0; i <= ListSaldoCP.size(); i++) {

                        ContaPoupanca ccl = ListSaldoCP.get(i);

                        if (ccl.getNumConta() == NumConta) {

                            cp.setSaldoCP(ccl.getSaldoCP());

                        }

                    }

                    for (int i = 0; i <= ListSaldoCI.size(); i++) {

                        ContaInvestimento ccl = ListSaldoCI.get(i);

                        if (ccl.getNumConta() == NumConta) {

                            ci.setSaldoCI(ccl.getSaldoCI());

                        }

                    }


                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;

                } while (!checkconta);

            }

        } catch (IndexOutOfBoundsException e) {
            Imprimir.i(CONTA_NAO_ENCONTRADA);
            checkconta = false;
        } while (!checkconta);

        Imprimir.i(INTRODUCAO_NOME, nomeCliente);

        do {

            do try {

                checkconta = true;

                if (tpCliente == 1) {

                    Imprimir.i(MENU_INICIAL_PF);

                    menuInicial = Integer.parseInt(sc.nextLine());

                    if (menuInicial != 1 && menuInicial != 2 && menuInicial != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }

                } else {

                    Imprimir.i(MENU_INICIAL_PJ);

                    menuInicial = Integer.parseInt(sc.nextLine());

                    if (menuInicial != 1 && menuInicial != 2) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }
                }

            } catch (NumberFormatException e) {
                Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                checkconta = false;

            } while (!checkconta);

            if (menuInicial == 1) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);
                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {

                    Imprimir.i(VALOR_DE_DEPOSITO);
                    cc.depositar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 2) {
                    Imprimir.i(VALOR_DE_SAQUE);
                    cc.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 3) {
                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    int destinoTransferencia;

                    if(tpCliente==1) {

                        Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_POUPANCA, CONTA_INVESTIMENTO);
                        destinoTransferencia = Integer.parseInt(sc.nextLine());

                    } else {destinoTransferencia = 2;}

                    cc.transferencia(destinoTransferencia, tpCliente, valorTransferencia);
                }

                for (int i = 0; i <= ListSaldoCC.size(); i++) {

                    ContaCorrente saldoCCi = ListSaldoCC.get(i);

                    if (saldoCCi.getNumConta() == NumConta) {

                        saldoCCi.setSaldoCC(cc.getSaldoCC());

                        break;

                    }

                }

            } else if (menuInicial == 3 && tpCliente == 1) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);
                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {

                    Imprimir.i(VALOR_DE_DEPOSITO);
                    cp.depositar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 2) {
                    Imprimir.i(VALOR_DE_SAQUE);
                    cp.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 3) {

                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_CORRENTE, CONTA_INVESTIMENTO);
                    int destinoTransferencia = Integer.parseInt(sc.nextLine());

                    cc.transferencia(destinoTransferencia, tpCliente, valorTransferencia);

                }
                for (int i = 0; i <= ListSaldoCP.size(); i++) {

                    ContaPoupanca saldoCPi = ListSaldoCP.get(i);

                    if (saldoCPi.getNumConta() == NumConta) {

                        saldoCPi.setSaldoCP(cc.getSaldoCC());

                        break;

                    }

                }

            } else if (menuInicial == 2) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);
                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO_TENTE_NOVAMENTE);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {
                    Imprimir.i(VALOR_INVESTIMENTO);
                    ci.depositar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 2) {
                    Imprimir.i(VALOR_DE_SAQUE);
                    ci.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                } else if (menuOperacao == 3) {
                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    int destinoTransferencia;

                    if(tpCliente==1) {

                        Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_CORRENTE, CONTA_POUPANCA);
                        destinoTransferencia = Integer.parseInt(sc.nextLine());

                    } else {destinoTransferencia=1;}

                    cc.transferencia(destinoTransferencia, tpCliente, valorTransferencia);

                }
                for (int i = 0; i <= ListSaldoCI.size(); i++) {

                    ContaInvestimento saldoCIi = ListSaldoCI.get(i);

                    if (saldoCIi.getNumConta() == NumConta) {

                        saldoCIi.setSaldoCI(cc.getSaldoCC());

                        break;

                    }
                }
            }

            amostragemSaldos();

            Imprimir.i(REALIZAR_OUTRA_OPERACAO);
            loop = Perguntar.q(sc.nextLine(), REALIZAR_OUTRA_OPERACAO);

        } while (loop);

        Imprimir.i(DESLOGAR);
        loop = Perguntar.q(sc.nextLine(), DESLOGAR);

        if (loop) {
            main(args);

        } else System.exit(0);

    }

    public static void amostragemSaldos() {

        Imprimir.i(CHECAGEM_SALDO);
        checkSaldo = Perguntar.q(sc.nextLine(), CHECAGEM_SALDO);

        if (checkSaldo) {

            Imprimir.i(DADOS_CONTA, nomeCliente, String.valueOf(NumConta));

            if (tpCliente == 1) {

                Imprimir.i(CONTA_CORRENTE);
                cc.exibirSaldo();
                Imprimir.i(CONTA_POUPANCA);
                cp.exibirSaldo();
                Imprimir.i(CONTA_INVESTIMENTO);
                ci.exibirSaldo();

            } else {
                Imprimir.i(CONTA_CORRENTE);
                cc.exibirSaldo();
                Imprimir.i(CONTA_INVESTIMENTO);
                ci.exibirSaldo();

            }
        }
    }
}