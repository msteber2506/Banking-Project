package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputWindowTest {

    private Bank bank;
    private CommandStorage commandStorage;
    private OutputWindow outputWindow;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandStorage = new CommandStorage();
        outputWindow = new OutputWindow(bank, commandStorage);
    }

    @Test
    void output_one_invalid_command() {
        commandStorage.addInvalidCommand("crate cd 1234");
        List<String> actual = outputWindow.getMessageList();
        assertEquals("crate cd 1234", actual.get(0));
    }

    @Test
    void output_account_status() {
        bank.createCheckingAccount("12345678", 5);
        commandStorage.addValidCommand("create checking 12345678 5");
        List<String> actual = outputWindow.getMessageList();
        assertEquals("Checking 12345678 0.00 5.00", actual.get(0));
    }
}
