package net.youssfi;

import net.youssfi.business.BankAccountService;
import net.youssfi.business.BankAccountServiceImpl;
import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

import java.util.List;
import java.util.function.Consumer;

public class Application {
    public static void main(String[] args) {
        BankAccountService bankAccountService=new BankAccountServiceImpl();
        bankAccountService.addBankAccount(new CurrentAccount("MAD",43000,100));
        bankAccountService.addBankAccount(new SavingAccount("MAD",12000,3.5));
        BankAccount bankAccount3=new CurrentAccount("MAD",43000,100);
        bankAccount3.setAccountId("CC1");
        bankAccountService.addBankAccount(bankAccount3);
        List<BankAccount> allAccounts = bankAccountService.getAllAccounts();
        /*
        for (int i = 0; i <allAccounts.size() ; i++) {
            System.out.println(allAccounts.get(i).toString());

        }
         */
        /*
        for (BankAccount bankAccount:allAccounts){
            System.out.println(bankAccount.toString());
        }
         */
        System.out.println("========");
        allAccounts.forEach(new Consumer<BankAccount>() {
            @Override
            public void accept(BankAccount account) {
                System.out.println(account.toString());
            }
        });

        System.out.println("========");
        allAccounts.forEach(account-> System.out.println(account));
        System.out.println("========");
        allAccounts.forEach(System.out::println);

        System.out.println("----------------------");

        BankAccount accountById = null;
        try {
            accountById = bankAccountService.getAccountById("CC6");
            System.out.println(accountById.toString());
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("*******************************");
        System.out.println("Suite du programme");

    }
}
