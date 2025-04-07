import java.util.Scanner;
import service.BankService;

public class Main {
    private static BankService bankService = new BankService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running) {
            System.out.println("=== Sistema Bancário Completo ===");
            System.out.println("1. Criar conta (checking/savings/investment)");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Aplicar juros (Conta Poupança)");
            System.out.println("6. Investir");
            System.out.println("7. Emitir Cartão (credit/debit)");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch(option) {
                case 1:
                    System.out.print("Número da conta: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Nome do titular: ");
                    String holderName = scanner.nextLine();
                    System.out.print("Saldo inicial: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Tipo de conta (checking/savings/investment): ");
                    String accountType = scanner.nextLine();
                    if(bankService.createAccount(accountNumber, holderName, initialBalance, accountType)) {
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("Erro ao criar conta.");
                    }
                    break;
                case 2:
                    System.out.print("Número da conta para depósito: ");
                    String depAccount = scanner.nextLine();
                    System.out.print("Valor a depositar: ");
                    double depAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if(bankService.deposit(depAccount, depAmount)) {
                        System.out.println("Depósito realizado com sucesso!");
                    } else {
                        System.out.println("Erro no depósito.");
                    }
                    break;
                case 3:
                    System.out.print("Número da conta para saque: ");
                    String witAccount = scanner.nextLine();
                    System.out.print("Valor a sacar: ");
                    double witAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if(bankService.withdraw(witAccount, witAmount)) {
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Erro no saque.");
                    }
                    break;
                case 4:
                    System.out.print("Conta de origem: ");
                    String fromAccount = scanner.nextLine();
                    System.out.print("Conta de destino: ");
                    String toAccount = scanner.nextLine();
                    System.out.print("Valor a transferir: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if(bankService.transfer(fromAccount, toAccount, transferAmount)) {
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Erro na transferência.");
                    }
                    break;
                case 5:
                    System.out.print("Número da conta poupança: ");
                    String savAccount = scanner.nextLine();
                    if(bankService.applyInterest(savAccount)) {
                        System.out.println("Juros aplicados com sucesso!");
                    } else {
                        System.out.println("Erro ao aplicar juros.");
                    }
                    break;
                case 6:
                    System.out.print("Número da conta de investimento: ");
                    String invAccount = scanner.nextLine();
                    System.out.print("Tipo de investimento (ex.: stocks, bonds): ");
                    String investmentType = scanner.nextLine();
                    System.out.print("Valor a investir: ");
                    double investAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if(bankService.invest(invAccount, investmentType, investAmount)) {
                        System.out.println("Investimento realizado com sucesso!");
                    } else {
                        System.out.println("Erro ao investir.");
                    }
                    break;
                case 7:
                    System.out.print("Número da conta para emitir cartão: ");
                    String cardAccount = scanner.nextLine();
                    System.out.print("Tipo de cartão (credit/debit): ");
                    String cardType = scanner.nextLine();
                    System.out.print("Número do cartão: ");
                    String cardNumber = scanner.nextLine();
                    Double limitAmount = null;
                    if(cardType.equalsIgnoreCase("credit")) {
                        System.out.print("Limite do cartão: ");
                        limitAmount = scanner.nextDouble();
                        scanner.nextLine();
                    }
                    if(bankService.issueCard(cardAccount, cardType, cardNumber, limitAmount)) {
                        System.out.println("Cartão emitido com sucesso!");
                    } else {
                        System.out.println("Erro ao emitir cartão.");
                    }
                    break;
                case 8:
                    running = false;
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        }
        scanner.close();
    }
}
