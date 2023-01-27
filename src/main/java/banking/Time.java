package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Time {

    private Bank bank;

    public Time(Bank bank) {
        this.bank = bank;
    }

    public void pass(int month) {
        for (int i = 0; i < month; ++i) {
            List<String> closedAccounts = new ArrayList<>();
            for (Map.Entry<String, Account> set :
                    bank.getAccounts().entrySet()) {
                Account account = set.getValue();
                ageAndReset(account);
                passOperations(account, closedAccounts);
            }
            closeAccounts(closedAccounts);
        }

    }

    public void closeAccounts(List<String> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            bank.closeAccount(accounts.get(i));
        }
    }

    public void ageAndReset(Account account) {
        account.incrementAge();
        account.resetMonthlyWithdrawCount();
    }

    public void passOperations(Account account, List<String> closedAccounts) {
        if (account.getBalance() == 0) {
            closedAccounts.add(account.getID());
        } else if (account.getBalance() < 100) {
            bank.withdrawFromAccount(account.getID(), 25);
        } else {
            bank.addAPRToAccount(account.getID());
        }
    }
}
