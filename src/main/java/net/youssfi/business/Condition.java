package net.youssfi.business;

import net.youssfi.model.BankAccount;

@FunctionalInterface
public interface Condition<T> {
    boolean test(T o);
}
