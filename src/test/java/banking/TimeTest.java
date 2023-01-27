package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TimeTest {

    private Time time;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        time = new Time(bank);
    }

    @Test
    void pass_closes_account() {
        bank.createSavingsAccount("23456789", 0);
        time.pass(1);
        assertFalse(bank.accountExistsByID("23456789"));
    }

    @Test
    void pass_deducts_25() {
        bank.createCheckingAccount("12345678", 0);
        bank.depositToAccount("12345678", 75);
        time.pass(1);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 50);
    }

    @Test
    void pass_accrues_apr() {
        bank.createCheckingAccount("12345678", (float) 1.2);
        bank.depositToAccount("12345678", 1000);
        time.pass(1);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 1001.0);
    }
}
