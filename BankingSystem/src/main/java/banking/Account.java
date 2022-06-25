package banking;

/**
 * Abstract bank account class.<br>
 * <br>
 * <p>
 * Private Variables:<br>
 * {@link #accountHolder}: AccountHolder<br>
 * {@link #accountNumber}: Long<br>
 * {@link #pin}: int<br>
 * {@link #balance}: double
 */
public abstract class Account {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance=startingDeposit;
    }

    protected Account() {
    }

    public AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        if(attemptedPin==this.pin){
            return true;
        }
        else{
            return false;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void creditAccount(double amount) {
        this.balance = this.balance+amount;
    }

    public boolean debitAccount(double amount) {
        double debitedAmount = this.balance - amount;
        if(debitedAmount >=0.0){
            this.balance = debitedAmount;
            return true;
        }
        else {
            System.out.println("Insufficient amount");
            return false;
        }
    }
}
