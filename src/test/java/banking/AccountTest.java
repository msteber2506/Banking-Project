package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    public static final String QUICK_ID = "12345678";
    public static final float QUICK_APR = 5;
    public static final float QUICK_AMOUNT = 1000;
    Account account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(QUICK_ID, QUICK_AMOUNT, QUICK_APR);
    }

    @Test
    void account_has_correct_id() {
        assertEquals(QUICK_ID, account.getID());
    }

    @Test
    void account_has_correct_balance() {
        assertEquals(QUICK_AMOUNT, account.getBalance());
    }

    @Test
    void account_has_correct_apr() {
        assertEquals(QUICK_APR, account.getAPR());
    }

    @Test
    void account_has_correct_age() {
        assertEquals(0, account.getAge());
    }

    @Test
    void account_has_correct_max_deposit() {
        assertEquals(1000, account.getMaxDeposit());
    }

    @Test
    void account_has_correct_max_withdraw() {
        assertEquals(400, account.getMaxWithdraw());
    }

    @Test
    void account_has_correct_min_withdraw() {
        assertEquals(0, account.getMinWithdraw());
    }

    @Test
    void withdraw_updates_balance() {
        float balance = account.getBalance();
        account.deposit(QUICK_AMOUNT + 500);
        account.withdraw(QUICK_AMOUNT);
        assertEquals(balance + 500, account.getBalance());
    }

    @Test
    void deposit_updates_balance() {
        float balance = account.getBalance();
        account.deposit(QUICK_AMOUNT);
        assertEquals(QUICK_AMOUNT + balance, account.getBalance());
    }

    @Test
    void cd_has_correct_min_withdraw() {
        Account cd = new CDAccount("12345678", 5000, 5);
        assertEquals(cd.getMinWithdraw(), 5000);
    }


}
