package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDAccountTest {

    public static final String QUICK_ID = "12345678";
    public static final float QUICK_APR = 5;
    public static final float QUICK_AMOUNT = 1000;
    Account account;


    @BeforeEach
    void setUp() {
        account = new CDAccount(QUICK_ID, QUICK_AMOUNT, QUICK_APR);
    }

    @Test
    void account_has_correct_balance_initially() {
        assertEquals(QUICK_AMOUNT, account.getBalance());
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
        account.withdraw(QUICK_AMOUNT);
        assertEquals(balance - QUICK_AMOUNT, account.getBalance());
    }


}
