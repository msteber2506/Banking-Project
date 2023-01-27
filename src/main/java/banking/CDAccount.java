package banking;

public class CDAccount extends Account {

    public CDAccount(String id, float balance, float apr) {
        super(id, balance, apr);
        this.maxDeposit = -1;
        this.maxMonthlyWithdrawCount = 1;
        this.minWithdrawAge = 12;
        this.canTransfer = false;
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
    public void withdraw(Float amount) {
        super.withdraw(amount);
    }

    @Override
    public float getMaxWithdraw() {
        return 20000;
    }

    @Override
    public float getMinWithdraw() {
        return getBalance();
    }


}
