package net.youssfi;

import net.youssfi.business.BankAccountService;
import net.youssfi.business.BankAccountServiceImpl;
import net.youssfi.business.Condition;
import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.exceptions.BalanceNotSufficientException;
import net.youssfi.model.AccountStatus;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;
import net.youssfi.utils.DataTransformationUtils;

import java.util.List;

public class App3 {
    public static void main(String[] args) {
        BankAccountService bankAccountService=new BankAccountServiceImpl();
        bankAccountService.addRandomData(20);
        BankAccount bankAccount1=new CurrentAccount("MAD",32000,100);
        bankAccount1.setAccountId("CC1");

        BankAccount bankAccount2=new SavingAccount("MAD",1000,3.2);
        bankAccount2.setAccountId("CC2");

        bankAccountService.addBankAccount(bankAccount1);
        bankAccountService.addBankAccount(bankAccount2);
        /*
        bankAccountService.getAllAccounts()
                .stream()
                .map(DataTransformationUtils::toJson)
                .forEach(System.out::println);
         */

        BankAccount acc1=null;
        BankAccount acc2=null;
        try {
            acc1 = bankAccountService.getAccountById("CC1");
            acc2 = bankAccountService.getAccountById("CC2");
            System.out.println(DataTransformationUtils.toJson(acc1));
            System.out.println(DataTransformationUtils.toJson(acc2));
            System.out.println("=====================");
            bankAccountService.debit(acc1.getAccountId(),2000);
            System.out.println(DataTransformationUtils.toJson(acc1));
            System.out.println("=====================");
            bankAccountService.transfer("CC1","CC43",3000);
            System.out.println(DataTransformationUtils.toJson(acc1));
            System.out.println(DataTransformationUtils.toJson(acc2));
        } catch (AccountNotFoundException | BalanceNotSufficientException  e) {
            System.out.println(e.getMessage());
        }
        System.out.println("***************");
        System.out.println(DataTransformationUtils.toJson(acc1));
        System.out.println(DataTransformationUtils.toJson(acc2));
        System.out.println("===============");
        System.out.println("++++++++++++++++++++++");
        bankAccountService.getSavingAccounts()
                .stream()
                .map(DataTransformationUtils::toJson).forEach(System.out::println);
        ;
        System.out.println("Total Balance : "+bankAccountService.getTotalBalance());

        System.out.println("==============");
        List<BankAccount> bankAccountList = bankAccountService.searchAccounts(acc -> acc.getStatus().equals(AccountStatus.ACTIVATED));
        bankAccountList.stream().map(DataTransformationUtils::toJson).forEach(System.out::println);

    }
}
