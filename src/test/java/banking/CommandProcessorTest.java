package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {


    private final String QUICK_CREATE_COMMAND = "create checking 12345678 5.0";
    private final String QUICK_CREATE_COMMAND2 = "create checking 23456789 5.0";
    private final String QUICK_DEPOSIT_COMMAND = "deposit 12345678 1000";
    private CommandProcessor commandProcessor;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void process_create_command() {
        commandProcessor.processCommand(QUICK_CREATE_COMMAND);
        assertEquals(1, bank.getAccounts().size());
        assertEquals("12345678", bank.getAccounts().get("12345678").getID());
    }

    @Test
    void process_deposit_command() {
        bank.createCheckingAccount("12345678", 5);
        commandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 1000);
    }

    @Test
    void process_two_create_commands() {
        commandProcessor.processCommand(QUICK_CREATE_COMMAND);
        commandProcessor.processCommand(QUICK_CREATE_COMMAND2);
        assertEquals(2, bank.getAccounts().size());
        assertEquals("12345678", bank.getAccounts().get("12345678").getID());
        assertEquals("23456789", bank.getAccounts().get("23456789").getID());
    }

    @Test
    void process_two_deposit_commands() {
        bank.createCheckingAccount("12345678", 5);
        commandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        commandProcessor.processCommand(QUICK_DEPOSIT_COMMAND);
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 2000);
    }

    @Test
    void process_pass_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        commandProcessor.processCommand("pass 30");
        assertEquals(bank.getAccounts().get("12345678").getAge(), 30);
    }

    @Test
    void process_two_pass_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        commandProcessor.processCommand("pass 30");
        commandProcessor.processCommand("pass 60");
        assertEquals(bank.getAccounts().get("12345678").getAge(), 90);
    }

    @Test
    void process_withdraw_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        commandProcessor.processCommand("withdraw 12345678 300");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 200);
    }

    @Test
    void process_two_withdraw_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 500);
        commandProcessor.processCommand("withdraw 12345678 200");
        commandProcessor.processCommand("withdraw 12345678 200");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 100);
    }

    @Test
    void process_transfer_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.createSavingsAccount("98765432", 5);
        bank.depositToAccount("12345678", 400);
        commandProcessor.processCommand("transfer 12345678 98765432 300");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 100);
        assertEquals(bank.getAccounts().get("98765432").getBalance(), 300);
    }

    @Test
    void process_two_transfer_command() {
        bank.createCheckingAccount("12345678", 5);
        bank.createSavingsAccount("98765432", 5);
        bank.depositToAccount("12345678", 400);
        commandProcessor.processCommand("transfer 12345678 98765432 300");
        commandProcessor.processCommand("transfer 98765432 12345678 150");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 250);
        assertEquals(bank.getAccounts().get("98765432").getBalance(), 150);
    }
}
