package com.example.banking;

import com.example.banking.service.BankService;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n============ SISTEMA BANCARIO ============");
            System.out.println("1. Criar Conta");
            System.out.println("2. Atualizar Conta");
            System.out.println("3. Deposito");
            System.out.println("4. Saque");
            System.out.println("5. Transferencia / PIX");
            System.out.println("6. Exibir detalhes da conta");
            System.out.println("7. Aplicar juros (Conta Poupanca)");
            System.out.println("8. Investir (Conta de Investimentos)");
            System.out.println("9. Sair");
            System.out.print("Selecione uma opcao: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Tente novamente.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.println("Selecione o tipo de conta: ");
                    System.out.println("1 - Conta Corrente");
                    System.out.println("2 - Conta Poupanca");
                    System.out.println("3 - Conta de Investimentos");
                    int tipoConta = Integer.parseInt(scanner.nextLine());
                    bankService.createAccount(nome, tipoConta);
                    break;
                case 2:
                    System.out.print("Digite o numero da conta: ");
                    int contaAtualizar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o novo nome do cliente: ");
                    String novoNome = scanner.nextLine();
                    bankService.updateAccount(contaAtualizar, novoNome);
                    break;
                case 3:
                    System.out.print("Digite o numero da conta: ");
                    int contaDeposito = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o valor do deposito: ");
                    double valorDeposito = Double.parseDouble(scanner.nextLine());
                    bankService.deposit(contaDeposito, valorDeposito);
                    break;
                case 4:
                    System.out.print("Digite o numero da conta: ");
                    int contaSaque = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = Double.parseDouble(scanner.nextLine());
                    bankService.withdraw(contaSaque, valorSaque);
                    break;
                case 5:
                    System.out.print("Digite o numero da conta de origem: ");
                    int contaOrigem = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o numero da conta de destino: ");
                    int contaDestino = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o valor da transferencia/PIX: ");
                    double valorTransferencia = Double.parseDouble(scanner.nextLine());
                    bankService.transfer(contaOrigem, contaDestino, valorTransferencia);
                    break;
                case 6:
                    System.out.print("Digite o numero da conta: ");
                    int contaDetalhes = Integer.parseInt(scanner.nextLine());
                    bankService.displayAccountDetails(contaDetalhes);
                    break;
                case 7:
                    System.out.print("Digite o numero da conta poupanca: ");
                    int contaPoupanca = Integer.parseInt(scanner.nextLine());
                    bankService.applyInterest(contaPoupanca);
                    break;
                case 8:
                    System.out.print("Digite o numero da conta de investimentos: ");
                    int contaInvestimentos = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o valor a investir: ");
                    double valorInvestir = Double.parseDouble(scanner.nextLine());
                    bankService.invest(contaInvestimentos, valorInvestir);
                    break;
                case 9:
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (option != 9);

        scanner.close();
    }
}
