package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PassTimeCommandProcessorTest {

    private PassTimeCommandProcessor passTimeCommandProcessor;
    private Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        passTimeCommandProcessor = new PassTimeCommandProcessor(bank);
    }

    @Test
    void zero_balance_accounts_are_closed() {
        bank.createCheckingAccount("12345678", 5);
        bank.createSavingsAccount("23456789", 5);
        passTimeCommandProcessor.processCommand("pass 1");
        assertFalse(bank.accountExistsByID("12345678"));
        assertFalse(bank.accountExistsByID("23456789"));
    }

    @Test
    void money_is_deducted_from_account_with_less_than_100_balance() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 50);
        passTimeCommandProcessor.processCommand("pass 1");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 25);

    }

    @Test
    void apr_is_accrued() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        passTimeCommandProcessor.processCommand("pass 1");
        float amount = 1000 + (float) 1000 * 5 / 1200;
        assertEquals(bank.getAccounts().get("12345678").getBalance(), amount);
    }

    @Test
    void apr_is_accrued_two_times() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        passTimeCommandProcessor.processCommand("pass 2");
        float amount = 1000 + (float) 1000 * 5 / 1200;
        amount = amount + amount * 5 / 1200;
        assertEquals(bank.getAccounts().get("12345678").getBalance(), amount);
    }
}
