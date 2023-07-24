package com.sistema.banco;

import java.util.Scanner;

/*Grupo 1 - Sistema do Banco
O sistema severe ter um login e senha.
O login e senha devem ser validados baseado em 3 logins e senhas que estão gravados no sistema. 
Cada usuário deverá ter um saldo na conta. 
Quando ligado deverá aparecer um menu com as opções: Sacar, Depositar, Sair do Aplicativo. 
Deverá ser possível sacar somente se houver dinheiro na conta o suficiente. 
Ao sair da conta ele deve perguntar novos login e senha. 

Critérios de avaliação 
- Separar cada função com sua respectiva responsabilidade. 
- Usar if else
- Usar switch case no menu 
- Usar algum laço de repetição 
- Utilizar Array para salvar login e senha
 */

public class SistemaBancario {
    Scanner sb = new Scanner(System.in);
    String[] usuarios = { "Junior dos Anjos", "Maria Clara", "Pedro Algusto", "Leci Gomes" };// criação de arrays de
                                                                                             // usuários
    String[] senhas = { "1234", "5678", "9101", "1213" };// criação de arrays de senhas
    double[] saldoConta = { 50.00, 1500.00, 25000.00, 4.00 };// Criação de saldo em conta
    final int tentativasMax = 3;// Final atribuída apenas 1 vez, para não haver modificação da variável.
    int tentativa = 0;// Tentativa inicializada em 0
    String loginDigitado, senhaDigitada;// criação das variáveis

    public static void main(String[] args) {
        SistemaBancario sistemaBancario = new SistemaBancario();
        sistemaBancario.iniciar();
    }

    void iniciar() {// criação do método iniciar
        boolean logado = loginSenha();// criação do booleaan para verificar se está logado
        if (logado) {
            exibirMenu();
        } else {
            System.out.println("Acesso bloqueado. Tente novamente mais tarde!");
        }
    }

    boolean loginSenha() {// boleano loginSenha
        while (tentativa < tentativasMax) {// Estrutura de repetição no login e senha
            System.out.print("Digite o Login: ");
            loginDigitado = sb.nextLine();

            System.out.print("Digite a senha: ");
            senhaDigitada = sb.nextLine();

            for (int i = 0; i < usuarios.length; i++) {// usando o For(para), verificando se login e senha digitado e
                                                       // criados no Array estão coincidindo
                if (usuarios[i].equals(loginDigitado) && senhas[i].equals(senhaDigitada)) {
                    System.out.println("Olá, " + loginDigitado + ", bem-vindo! ");
                    return true;
                }
            }

            tentativa++;// Incrementando tentativa
            if (tentativa < tentativasMax) {// testa se a tentativa é menor que tentativas máximas
                System.out.println("Login ou senha inválidos, tente novamente.");

                if (tentativa == tentativasMax - 1) {
                    System.out.println("Última tentativa, seu acesso será bloqueado se errar! ");
                }
            } else {
                System.out.println("Terceira tentativa incorreta. Acesso bloqueado! ");
            }
        }
        return false;
    }

    void exibirMenu() {// Método exibirMenu, cria um menu de opções
        int opcao;
        while (true) {// estrutura de repetição, verificando opções disponíveis.
            System.out.println("\nOpções:");
            System.out.println("1. Sacar");
            System.out.println("2. Depositar");
            System.out.println("3. Sair do Aplicativo");
            System.out.print("Escolha uma opção: ");
            opcao = sb.nextInt();

            switch (opcao) {// Estrutura de controle de fluxo
                case 1:
                    sacar();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    System.out.println("Saindo do aplicativo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    void sacar() {// Método sacar, permite o saque nas contas
        System.out.print("Digite o valor a ser sacado: ");
        double valor = sb.nextDouble();

        for (int i = 0; i < usuarios.length; i++) {// estrutura de repetição For(para)
            if (usuarios[i].equals(loginDigitado) && senhas[i].equals(senhaDigitada)) {
                if (valor <= saldoConta[i]) {// Verifica o saldo, se saldo na conta for menor, indica que não pode
                                             // realizar saque, caso contrário,saque realizado.
                    saldoConta[i] -= valor;
                    System.out.println("Saque realizado com sucesso. Saldo atual: " + saldoConta[i]);
                } else {
                    System.out.println("Saldo insuficiente para realizar o saque.");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada. Não foi possível realizar o saque.");// Conta não encontrada
    }

    void depositar() {// Método depositar,acresenta no saldo da conta
        System.out.print("Digite o valor a ser depositado: ");
        double valor = sb.nextDouble();

        for (int i = 0; i < usuarios.length; i++) {// estrutura de repetição for(para)
            if (usuarios[i].equals(loginDigitado) && senhas[i].equals(senhaDigitada)) {
                saldoConta[i] += valor;
                System.out.println("Depósito realizado com sucesso. Saldo atual: " + saldoConta[i]);
                return;
            }
        }

        System.out.println("Conta não encontrada. Não foi possível realizar o depósito.");
    }
}
