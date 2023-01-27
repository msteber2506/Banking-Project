package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {

    private Bank bank;
    private TransferCommandProcessor transferCommandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferCommandProcessor = new TransferCommandProcessor(bank);
        bank.createCheckingAccount("12345678", 5);
        bank.createSavingsAccount("23456789", 5);
        bank.depositToAccount("12345678", 500);
        bank.depositToAccount("23456789", 500);
    }

    @Test
    void transfer_from_checking_to_savings() {
        transferCommandProcessor.processCommand("transfer 12345678 23456789 200");
        assertEquals(bank.getAccounts().get("12345678").getBalance(), 300);
        assertEquals(bank.getAccounts().get("23456789").getBalance(), 700);
    }
}
