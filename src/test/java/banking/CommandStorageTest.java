package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {

    private final String QUICK_INVALID_CREATE_COMMAND = "creat checking 12345678 1.0";
    private final String QUICK_INVALID_DEPOSIT_COMMAND = "depositt 12345678 100";
    private final String QUICK_VALID_CREATE_COMMAND = "create checking 12345678 1.0";
    private final String QUICK_VALID_DEPOSIT_COMMAND = "deposit 12345678 100";

    private Bank bank;
    private CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandStorage = new CommandStorage();
    }

    @Test
    void invalid_command_list_is_initially_empty() {
        assertEquals(0, commandStorage.getInvalidCommands().size());
    }

    @Test
    void add_one_invalid_command() {
        commandStorage.addInvalidCommand(QUICK_INVALID_CREATE_COMMAND);
        assertEquals(QUICK_INVALID_CREATE_COMMAND, commandStorage.getInvalidCommands().get(0));
        assertEquals(1, commandStorage.getInvalidCommands().size());
    }

    @Test
    void add_two_invalid_commands() {
        commandStorage.addInvalidCommand(QUICK_INVALID_CREATE_COMMAND);
        commandStorage.addInvalidCommand(QUICK_INVALID_DEPOSIT_COMMAND);
        assertEquals(QUICK_INVALID_CREATE_COMMAND, commandStorage.getInvalidCommands().get(0));
        assertEquals(QUICK_INVALID_DEPOSIT_COMMAND, commandStorage.getInvalidCommands().get(1));
        assertEquals(2, commandStorage.getInvalidCommands().size());
    }

    @Test
    void add_one_valid_command() {
        commandStorage.addValidCommand(QUICK_VALID_CREATE_COMMAND);
        assertEquals(1, commandStorage.getValidCommands().size());
        assertEquals(commandStorage.getValidCommands().get(0), QUICK_VALID_CREATE_COMMAND);
    }

    @Test
    void add_two_valid_commands() {
        commandStorage.addValidCommand(QUICK_VALID_CREATE_COMMAND);
        commandStorage.addValidCommand(QUICK_VALID_DEPOSIT_COMMAND);
        assertEquals(2, commandStorage.getValidCommands().size());
        assertEquals(commandStorage.getValidCommands().get(0), QUICK_VALID_CREATE_COMMAND);
        assertEquals(commandStorage.getValidCommands().get(1), QUICK_VALID_DEPOSIT_COMMAND);
    }
}
