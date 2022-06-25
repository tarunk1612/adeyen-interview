package banking;

import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
        // complete the function
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        Long newAccountNuber = 1L;
        if (accounts == null) {
            accounts = new LinkedHashMap<>();
        } else {
            newAccountNuber = accounts.size() + 1L;
        }
        Account account = new CommercialAccount(company, newAccountNuber, pin, startingDeposit);
        accounts.put(newAccountNuber, account);
        return newAccountNuber;
    }

    public synchronized Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        Long newAccountNuber = 1L;
        if (accounts == null) {
            accounts = new LinkedHashMap<>();
        } else {
            newAccountNuber = accounts.size() + 1L;
        }
        Account account = new ConsumerAccount(person, newAccountNuber, pin, startingDeposit);
        accounts.put(newAccountNuber, account);
        return newAccountNuber;
    }

    public synchronized boolean authenticateUser(Long accountNumber, int pin) {
        return accounts.get(accountNumber).validatePin(pin);
    }

    public synchronized double getBalance(Long accountNumber) {
        return accounts.get(accountNumber).getBalance();
    }

    public synchronized void credit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        account.creditAccount(amount);
        accounts.put(account.getAccountNumber(), account);
    }

    public synchronized boolean debit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account.debitAccount(amount)) {
            accounts.put(account.getAccountNumber(), account);
            return true;
        } else {
            return false;
        }
    }
}
