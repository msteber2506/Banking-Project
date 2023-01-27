package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void createCheckingAccount(String id, float apr) {
        CheckingAccount checkingAccount = new CheckingAccount(id, 0, apr);
        accounts.put(id, checkingAccount);
    }

    public void createSavingsAccount(String id, float apr) {
        SavingsAccount savingsAccount = new SavingsAccount(id, 0, apr);
        accounts.put(id, savingsAccount);
    }

    public void createCdAccount(String id, float amount, float apr) {
        CDAccount cdAccount = new CDAccount(id, amount, apr);
        accounts.put(id, cdAccount);
    }

    public void depositToAccount(String ID, float amount) {
        accounts.get(ID).deposit(amount);
    }

    public void withdrawFromAccount(String ID, float amount) {
        accounts.get(ID).withdraw(amount);
        accounts.get(ID).incrementMonthlyWithdrawCount();
    }

    public boolean accountExistsByID(String ID) {
        if (accounts.get(ID) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDepositAmount(String ID, float amount) {
        if (accounts.get(ID).getMaxDeposit() >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWithdrawAmount(String ID, float amount) {
        if (accounts.get(ID).getMaxWithdraw() >= amount && accounts.get(ID).getMinWithdraw() <= amount) {
            return true;
        } else {
            return false;
        }
    }

    public void addAPRToAccount(String ID) {
        float balance = accounts.get(ID).getBalance();
        float apr = accounts.get(ID).getAPR();
        accounts.get(ID).deposit(apr * balance / 1200);
        if (accounts.get(ID).getMaxDeposit() < 0) {
            for (int i = 0; i < 3; i++) {
                balance = accounts.get(ID).getBalance();
                accounts.get(ID).deposit(apr * balance / 1200);
            }
        }
    }

    public void transferBetweenAccounts(String ID1, String ID2, float amount) {
        if (accounts.get(ID1).getBalance() < amount) {
            amount = accounts.get(ID1).getBalance();
        }
        withdrawFromAccount(ID1, amount);
        depositToAccount(ID2, amount);
    }

    public boolean checkWithdrawCount(String ID) {
        return accounts.get(ID).getMonthlyWithdrawCount() < accounts.get(ID).getMaxMonthlyWithdrawCount();
    }

    public boolean checkWithdrawAge(String ID) {
        return accounts.get(ID).getAge() >= accounts.get(ID).getMinWithdrawAge();
    }

    public void closeAccount(String ID) {
        accounts.remove(ID);
    }

    public boolean checkIfAccountCanTransfer(String ID) {
        return accounts.get(ID).getCanTransfer();
    }

    public void passTime(int month) {
        Time time = new Time(this);
        time.pass(month);
    }
}
