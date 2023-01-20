package net.youssfi.model;

import java.util.Objects;
import java.util.UUID;

public abstract class BankAccount {
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public BankAccount(){
        this.accountId= UUID.randomUUID().toString();
        this.status=AccountStatus.CREATED;
    }

    public BankAccount(String currency, double initialBalance){
        this();
        this.currency=currency;
        this.balance=initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }


    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object acc) {
        BankAccount account=(BankAccount) acc;
        if(this.accountId==account.accountId){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.accountId)+
                Objects.hashCode(this.balance)+
                Objects.hashCode(this.status)+
                Objects.hashCode(this.currency);
    }

    public abstract String getType();

    public final void print(){
        System.out.println("----------- BANK -----------------");
    }
}
