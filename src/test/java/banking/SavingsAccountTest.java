package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsAccountTest {

    public static final String QUICK_ID = "12345678";
    public static final float QUICK_APR = 5;
    public static final float QUICK_AMOUNT = 1000;
    Account account;


    @BeforeEach
    void setUp() {
        account = new SavingsAccount(QUICK_ID, 0, QUICK_APR);
    }


    @Test
    void account_has_zero_balance_initially() {
        assertEquals(0, account.getBalance());
    }

    @Test
    void account_has_correct_max_deposit() {
        assertEquals(2500, account.getMaxDeposit());
    }

    @Test
    void account_has_correct_id() {
        assertEquals(QUICK_ID, account.getID());
    }

    @Test
    void account_has_correct_apr() {
        assertEquals(QUICK_APR, account.getAPR());
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
    void account_has_correct_age() {
        account.incrementAge();
        assertEquals(account.getAge(), 1);
    }


}
