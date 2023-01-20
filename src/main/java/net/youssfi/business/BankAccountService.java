package net.youssfi.business;
import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.exceptions.BalanceNotSufficientException;
import net.youssfi.model.BankAccount;
import java.util.List;
import java.util.function.Predicate;

public interface BankAccountService {
   BankAccount addBankAccount(BankAccount account);
   List<BankAccount> getAllAccounts();
   BankAccount getAccountById(String id) throws AccountNotFoundException;
   void addRandomData(int size);
   void credit(String accountId, double amount) throws AccountNotFoundException;
   void debit(String accountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
   void transfer(String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
   List<BankAccount> getSavingAccounts();
   List<BankAccount> getCurrentsAccounts();
   double getTotalBalance();
   List<BankAccount> searchAccounts(Predicate<BankAccount> filter);
}
