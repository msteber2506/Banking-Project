package banking;

public abstract class Account {

    protected float maxDeposit;
    protected float maxWithdraw;
    protected float minWithdraw;
    protected int maxMonthlyWithdrawCount;
    protected int minWithdrawAge;
    protected boolean canTransfer;
    private String ID;
    private float balance;
    private float APR;
    private int age;
    private int monthlyWithdrawCount;

    public Account(String id, float balance, float apr) {
        this.ID = id;
        this.balance = balance;
        this.APR = apr;
        this.age = 0;
        this.monthlyWithdrawCount = 0;
        this.minWithdraw = 0;
        this.canTransfer = true;
    }

    public String getID() {
        return ID;
    }

    public float getBalance() {
        return balance;
    }

    public float getAPR() {
        return APR;
    }

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        age = age + 1;
    }

    public void deposit(Float amount) {
        balance = balance + amount;
    }

    public void withdraw(Float amount) {
        if (balance < amount) {
            balance = 0;
        } else {
            balance = balance - amount;
        }
    }

    public float getMaxDeposit() {
        return maxDeposit;
    }


    public float getMaxWithdraw() {
        return maxWithdraw;
    }

    public float getMinWithdraw() {
        return minWithdraw;
    }

    public int getMonthlyWithdrawCount() {
        return monthlyWithdrawCount;
    }

    public void incrementMonthlyWithdrawCount() {
        monthlyWithdrawCount = monthlyWithdrawCount + 1;
    }

    public int getMaxMonthlyWithdrawCount() {
        return maxMonthlyWithdrawCount;
    }

    public void resetMonthlyWithdrawCount() {
        monthlyWithdrawCount = 0;
    }

    public int getMinWithdrawAge() {
        return minWithdrawAge;
    }

    public boolean getCanTransfer() {
        return canTransfer;
    }

}
