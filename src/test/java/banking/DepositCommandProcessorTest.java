package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositCommandProcessorTest {

    private final String QUICK_DEPOSIT_COMMAND = "deposit 12345678 1000";
    private Bank bank;
    private DepositCommandProcessor depositCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositCommandProcessor = new DepositCommandProcessor(bank);
    }

    @Test
    void deposit_to_checking_one_time() {
        bank.createCheckingAccount("12345678", 5);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 1000);
    }

    @Test
    void deposit_to_savings_one_time() {
        bank.createSavingsAccount("12345678", 5);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 1000);
    }

    @Test
    void deposit_to_checking_two_times() {
        bank.createCheckingAccount("12345678", 5);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 2000);
    }

    @Test
    void deposit_to_savings_two_times() {
        bank.createSavingsAccount("12345678", 5);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 2000);
    }

    @Test
    void deposit_to_two_different_accounts() {
        bank.createSavingsAccount("23456789", 5);
        bank.createSavingsAccount("12345678", 5);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        depositCommandProcessor.processCommand("deposit 23456789 1000");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 1000);
        assertEquals(bank.getAccounts().get("23456789").getBalance(), 1000);
    }

    @Test
    void deposit_to_account_with_non_zero_balance() {
        bank.createCheckingAccount("12345678", 5);
        float balance = 1000;
        bank.depositToAccount("12345678", balance);
        depositCommandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), balance + 1000);
    }


}
