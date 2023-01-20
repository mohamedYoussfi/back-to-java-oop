package net.youssfi.model;

public class CurrentAccount extends BankAccount{
    private double overDraft;

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(String currency, double initialBalance, double overDraft) {
        super(currency, initialBalance);
        this.overDraft = overDraft;
    }

    @Override
    public String toString() {
        return "Current Account, Overdraft ="+overDraft+super.toString();
    }

    @Override
    public String getType() {
        return "CURRENT_ACCOUNT";
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    public double getOverDraft() {
        return overDraft;
    }
}
