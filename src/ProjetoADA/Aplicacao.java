package ProjetoADA;

import ProjetoADA.ClassesAuxiliares.Imprimir;
import ProjetoADA.ClassesAuxiliares.Perguntar;

import static ProjetoADA.ClassesAuxiliares.StringConstants.*;

import ProjetoADA.ConfiguracoesContas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Aplicacao {
    public static Scanner sc = new Scanner(System.in);
    static boolean checkconta;
    static boolean loop;
    static boolean checkSaldo;
    private static String nomeCliente;
    private static String docCliente;
    private static int NumConta;
    private static int tpCliente;
    private static int menuIntroducao;
    private static int menuInicial;
    private static int menuOperacao;
    private static double saldoCC;
    private static double saldoCP;
    private static double saldoCI;
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
                    Imprimir.i(VALOR_INVALIDO);
                    Imprimir.i(PRIMEIRO_MENU);
                    checkconta = false;
                }

            } catch (NumberFormatException e) {
                Imprimir.i(VALOR_INVALIDO);
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
                        Imprimir.i(VALOR_INVALIDO);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO);
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
                    Imprimir.i(VALOR_INVALIDO);
                    checkconta = false;

                } while (!checkconta);

                NumConta = Usuario.gerarNConta();

                Usuario U = new Usuario(nomeCliente, docCliente, tpCliente, NumConta);
                UList.add(U);

                Imprimir.i(CRIACAO_DE_CONTA, String.valueOf(NumConta));

                ContaCorrente cc2 = new ContaCorrente(NumConta, saldoCC);
                ListSaldoCC.add(cc2);

                ContaPoupanca cp2 = new ContaPoupanca(NumConta, saldoCP);
                ListSaldoCP.add(cp2);

                ContaInvestimento ci2 = new ContaInvestimento(NumConta, saldoCI);
                ListSaldoCI.add(ci2);

                //Caso escolha opção de realizar ‘login’:
            } else {

                // Do while e try catch referente ao numero da conta
                do try {

                    checkconta = true;

                    Imprimir.i(MENU_CONTA);
                    int NumContalogin = Integer.parseInt(sc.nextLine());

                    for (int i = 0; i <= UList.size(); i++) {

                        Usuario contai = UList.get(i);
                        ContaCorrente ccl = ListSaldoCC.get(i);
                        ContaPoupanca cpl = ListSaldoCP.get(i);
                        ContaInvestimento cil = ListSaldoCI.get(i);

                        if (contai.getNumConta() == NumContalogin) {

                            nomeCliente = contai.getNomeCliente();
                            docCliente = contai.getDocumentoCliente();
                            tpCliente = contai.getTipoCliente();
                            NumConta = NumContalogin;
                            saldoCC = ccl.getSaldo();
                            saldoCP = cpl.getSaldo();
                            saldoCI = cil.getSaldo();

                            break;
                        }
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO);
                    checkconta = false;

                } while (!checkconta);
            }

        } catch (IndexOutOfBoundsException e) {
            Imprimir.i(CONTA_NAO_ENCONTRADA);
            checkconta = false;
        } while (!checkconta);

        Imprimir.i(INTRODUCAO_NOME, nomeCliente);

        ContaCorrente cc = new ContaCorrente(saldoCC);
        ContaInvestimento ci = new ContaInvestimento(saldoCI);
        ContaPoupanca cp = new ContaPoupanca(saldoCP);

        do {

            do try {

                checkconta = true;

                if (tpCliente == 1) {

                    Imprimir.i(MENU_INICIAL_PF);

                    menuInicial = Integer.parseInt(sc.nextLine());

                    if (menuInicial != 1 && menuInicial != 2 && menuInicial != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO);
                    }

                } else {

                    Imprimir.i(MENU_INICIAL_PJ);

                    menuInicial = Integer.parseInt(sc.nextLine());

                    if (menuInicial != 1 && menuInicial != 2) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO);
                    }
                }

            } catch (NumberFormatException e) {
                Imprimir.i(VALOR_INVALIDO);
                checkconta = false;

            } while (!checkconta);

            if (menuInicial == 1) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);
                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {

                    do try {

                        checkconta = true;

                        Imprimir.i(VALOR_DE_DEPOSITO);

                        cc.depositar(tpCliente, Double.parseDouble(sc.nextLine()), menuOperacao);

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);

                } else if (menuOperacao == 2) {

                    do try {

                        Imprimir.i(VALOR_DE_SAQUE);
                        cc.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);

                } else if (menuOperacao == 3) {

                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    int destinoTransferencia;

                    if (tpCliente == 1) {

                        Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_POUPANCA, CONTA_INVESTIMENTO);
                        destinoTransferencia = Integer.parseInt(sc.nextLine());

                    } else {
                        destinoTransferencia = 2;
                    }

                    cc.transferencia(tpCliente, destinoTransferencia, valorTransferencia);

                    if (destinoTransferencia == 1) {

                        cp.depositar(tpCliente, valorTransferencia, menuOperacao);

                    } else if (destinoTransferencia == 2) {

                        ci.depositar(tpCliente, valorTransferencia, menuOperacao);

                    }
                }

            } else if (menuInicial == 3 && tpCliente == 1) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);
                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {

                    do try {

                        Imprimir.i(VALOR_DE_DEPOSITO);
                        cp.depositar(tpCliente, Double.parseDouble(sc.nextLine()), menuOperacao);

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);

                } else if (menuOperacao == 2) {

                    do try {

                        Imprimir.i(VALOR_DE_SAQUE);
                        cp.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);

                } else if (menuOperacao == 3) {

                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_CORRENTE, CONTA_INVESTIMENTO);
                    int destinoTransferencia = Integer.parseInt(sc.nextLine());

                    cp.transferencia(tpCliente, destinoTransferencia, valorTransferencia);

                    if (destinoTransferencia == 1) {

                        cc.depositar(tpCliente, valorTransferencia, menuOperacao);

                    } else if (destinoTransferencia == 2) {

                        ci.depositar(tpCliente, valorTransferencia, menuOperacao);

                    }
                }

            } else if (menuInicial == 2) {

                do try {

                    checkconta = true;

                    Imprimir.i(MENU_OPERACAO);

                    menuOperacao = Integer.parseInt(sc.nextLine());

                    if (menuOperacao != 1 && menuOperacao != 2 && menuOperacao != 3) {
                        checkconta = false;
                        Imprimir.i(VALOR_INVALIDO);
                    }

                } catch (NumberFormatException e) {
                    Imprimir.i(VALOR_INVALIDO);
                    checkconta = false;
                } while (!checkconta);

                if (menuOperacao == 1) {

                    do try {

                        Imprimir.i(VALOR_INVESTIMENTO);
                        ci.depositar(tpCliente, Double.parseDouble(sc.nextLine()), menuOperacao);

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);


                } else if (menuOperacao == 2) {

                    do try {

                        Imprimir.i(VALOR_DE_SAQUE);
                        ci.sacar(tpCliente, Double.parseDouble(sc.nextLine()));

                    } catch (NumberFormatException e) {
                        Imprimir.i(VALOR_INVALIDO);
                        checkconta = false;

                    } while (!checkconta);

                } else if (menuOperacao == 3) {
                    Imprimir.i(VALOR_TRANSFERENCIA);
                    double valorTransferencia = Double.parseDouble(sc.nextLine());

                    int destinoTransferencia;

                    if (tpCliente == 1) {

                        Imprimir.i(DESTINO_TRANSFERENCIA, CONTA_CORRENTE, CONTA_POUPANCA);
                        destinoTransferencia = Integer.parseInt(sc.nextLine());

                    } else {
                        destinoTransferencia = 1;
                    }

                    ci.transferencia(destinoTransferencia, tpCliente, valorTransferencia);

                    if (destinoTransferencia == 1) {

                        cc.depositar(tpCliente, valorTransferencia, menuOperacao);

                    } else if (destinoTransferencia == 2) {

                        cp.depositar(tpCliente, valorTransferencia, menuOperacao);

                    }
                }
            }

            for (int i = 0; i <= ListSaldoCI.size(); i++) {

                ContaInvestimento saldoCIi = ListSaldoCI.get(i);
                ContaCorrente saldoCCi = ListSaldoCC.get(i);
                ContaPoupanca saldoCPi = ListSaldoCP.get(i);

                if (saldoCIi.getNumConta() == NumConta) {

                    saldoCIi.setSaldo(ci.getSaldo());
                    saldoCCi.setSaldo(cc.getSaldo());
                    saldoCPi.setSaldo(cp.getSaldo());

                    saldoCI = saldoCIi.getSaldo();
                    saldoCC = saldoCCi.getSaldo();
                    saldoCP = saldoCPi.getSaldo();

                    break;

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
                Imprimir.i(String.format(VALOR_SALDO, saldoCC));
                Imprimir.i(CONTA_INVESTIMENTO);
                Imprimir.i(String.format(VALOR_SALDO, saldoCI));
                Imprimir.i(CONTA_POUPANCA);
                Imprimir.i(String.format(VALOR_SALDO, saldoCP));

            } else {
                Imprimir.i(CONTA_CORRENTE);
                Imprimir.i(String.format(VALOR_SALDO, saldoCC));
                Imprimir.i(CONTA_INVESTIMENTO);
                Imprimir.i(String.format(VALOR_SALDO, saldoCI));

            }
        }
    }
}