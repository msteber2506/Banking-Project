package banking;

public class CheckingAccount extends Account {

    public CheckingAccount(String id, float balance, float apr) {
        super(id, balance, apr);
        this.maxDeposit = 1000;
        this.maxWithdraw = 400;
        this.maxMonthlyWithdrawCount = Integer.MAX_VALUE;
        this.minWithdrawAge = 0;
    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public float getBalance() {
        return super.getBalance();
    }

    @Override
    public float getAPR() {
        return super.getAPR();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void incrementAge() {
        super.incrementAge();
    }

    @Override
    public void deposit(Float amount) {
        super.deposit(amount);
    }

    @Override
    public void withdraw(Float amount) {
        super.withdraw(amount);
    }

}
