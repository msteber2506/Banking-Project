package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawCommandProcessorTest {

    private Bank bank;
    private WithdrawCommandProcessor withdrawCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
    }

    @Test
    void withdraw_from_checking_one_time() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        withdrawCommandProcessor.processCommand("withdraw 12345678 300");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 200);
    }

    @Test
    void withdraw_from_checking_two_times() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        withdrawCommandProcessor.processCommand("withdraw 12345678 100");
        withdrawCommandProcessor.processCommand("withdraw 12345678 100");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 300);
    }

    @Test
    void withdraw_from_savings_one_time() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        withdrawCommandProcessor.processCommand("withdraw 12345678 300");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 200);
    }

    @Test
    void withdraw_from_savings_two_times() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        withdrawCommandProcessor.processCommand("withdraw 12345678 100");
        withdrawCommandProcessor.processCommand("withdraw 12345678 100");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 300);
    }

    @Test
    void withdraw_from_checking() {
        bank.createCdAccount("12345678", 2000, 0);
        withdrawCommandProcessor.processCommand("withdraw 12345678 2000");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 0);
    }
}
