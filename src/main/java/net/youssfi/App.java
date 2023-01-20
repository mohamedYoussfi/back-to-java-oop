package net.youssfi;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

public class App {
    public static void main(String[] args) {


        System.out.println("=============================");
        BankAccount account1=null;
        account1=new CurrentAccount();
        //account1.setAccountId("123-543");
        account1.setCurrency("MAD");
        account1.setBalance(7600);
        printAccount(account1);

        BankAccount account2=new CurrentAccount("MAD",1200,400);
        printAccount(account2);
        BankAccount account3=new SavingAccount("MAD",1200,3.4);
        System.out.println("================");
        account3.setAccountId(account2.getAccountId());
        System.out.println(account2);
        System.out.println(account3);
        System.out.println(".....................");
        if(account2.hashCode()==account3.hashCode()){
            System.out.println("les deux comptes ont le même état");
        } else {
            System.out.println("les deux comptes n'ont pas le même état");
        }
        account3.setCurrency("USD");

        if(account2.hashCode()==account3.hashCode()){
            System.out.println("les deux comptes ont le même état");
        } else {
            System.out.println("les deux comptes n'ont pas le même état");
        }
        System.out.println(".....................");
        System.out.println(account2==account3);
        System.out.println(account2.equals(account3));
        System.out.println("================");

    }

    public static void printAccount(BankAccount account){
        System.out.println("*******************************");
        System.out.println("Account ID="+account.getAccountId());
        System.out.println("Balance ="+account.getBalance());
        System.out.println("Status="+account.getStatus());
        System.out.println("Currency ="+account.getCurrency());
        System.out.println(account);
        System.out.println(account.hashCode());
        System.out.println(account.equals(account));
        System.out.println("*******************************");
    }
}