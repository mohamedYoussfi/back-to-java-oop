package net.youssfi.business;

import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.exceptions.BalanceNotSufficientException;
import net.youssfi.model.AccountStatus;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankAccountServiceImpl implements BankAccountService {
    private List<BankAccount> bankAccountList=new ArrayList<>();
    @Override
    public BankAccount addBankAccount(BankAccount account) {
        bankAccountList.add(account);
        return account;
    }



    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountList;
    }

    @Override
    public BankAccount getAccountById(String id) throws AccountNotFoundException {

        // Declarative Approach
        return bankAccountList
                .stream()
                .filter(acc -> acc.getAccountId().equals(id))
                .findFirst()
                .orElseThrow(()-> new AccountNotFoundException(String.format("BankAccount %s not found",id)));

        /*
        // Imperative Approach
        for (BankAccount bankAccount:bankAccountList){
            if(bankAccount.getAccountId().equals(id)){
                return bankAccount;
            }
        }
        throw new AccountNotFoundException("BankAccount not found");
         */
    }

    @Override
    public void addRandomData(int size) {
        AccountStatus[] values = AccountStatus.values();
        Random random=new Random();
        for (int i = 0; i <size ; i++) {
            BankAccount bankAccount;
            if(Math.random()>0.5){
                bankAccount=new CurrentAccount(Math.random()>0.5?"MAD":"USD",Math.random()*1000000,Math.random()*50000);
                bankAccount.setStatus(values[random.nextInt(values.length)]);
            } else {
                bankAccount=new SavingAccount(Math.random()>0.5?"MAD":"USD",Math.random()*1000000,3+Math.random()*7);
                bankAccount.setStatus(values[random.nextInt(values.length)]);
            }
            bankAccountList.add(bankAccount);
        }
    }

    @Override
    public void credit(String accountId, double amount) throws AccountNotFoundException {
        BankAccount accountById = getAccountById(accountId);
        accountById.setBalance(accountById.getBalance()+amount);
    }

    @Override
    public void debit(String accountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        BankAccount accountById = getAccountById(accountId);
        if(amount>accountById.getBalance()) throw new BalanceNotSufficientException("Balance not sufficient");
        accountById.setBalance(accountById.getBalance()-amount);
    }

    // Doit être transactionnelle
    @Override
    public void transfer(String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountSource,amount);
        credit(accountDestination,amount);
    }

    @Override
    public List<BankAccount> getSavingAccounts() {
        // Declarative Approach
        List<BankAccount> collect = bankAccountList
                .stream().filter(acc -> acc instanceof SavingAccount).collect(Collectors.toList());
        return collect;
        /*
        // Imperative Approach
        List<BankAccount> result=new ArrayList<>();
        for (BankAccount acc:bankAccountList){
           if(acc.getType().equals("SAVING_ACCOUNT")){
               result.add(acc);
           }
        }
        return result;
         */
    }

    @Override
    public List<BankAccount> getCurrentsAccounts() {
        List<BankAccount> collect = bankAccountList
                .stream().filter(bankAccount -> false).collect(Collectors.toList());
        return collect;
    }

    @Override
    public double getTotalBalance() {
       return bankAccountList
               .stream()
               .map(account -> account.getBalance())
               .reduce(0.0,(a,v)->a+v);
        /*
        double sum=0;
        for (BankAccount acc:bankAccountList){
            sum=sum+acc.getBalance();
        }
        return sum;
         */
    }

    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> filter) {
        List<BankAccount> result=new ArrayList<>();
        for (BankAccount acc:bankAccountList){
            if (filter.test(acc)){
                result.add(acc);
            }
        }
        return result;
    }
}
