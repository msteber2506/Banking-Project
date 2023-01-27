package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    MasterControl masterControl;
    private ArrayList<String> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        Bank bank = new Bank();
        CommandStorage commandStorage = new CommandStorage();
        masterControl = new MasterControl(
                new CommandValidator(bank),
                new CommandProcessor(bank),
                commandStorage,
                new OutputWindow(bank, commandStorage));
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("creat checking 12345678 1.0", actual);

    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("depositt 12345678 100", actual);
    }

    @Test
    void invalid_to_create_accounts_with_same_ID() {
        input.add("create checking 12345678 1.0");
        input.add("create checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertEquals("create checking 12345678 1.0", actual.get(1));
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("depositt 12345678 100", actual.get(1));
    }


    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }

    @Test
    void test_scenario_one() {
        input.add("Create Checking 12345678 5");
        input.add("Deposit 12345678 500");
        input.add("Transfer 12345678 98765432 300");
        input.add("Create Savings 98765432 5");
        input.add("Transfer 12345678 98765432 300");
        List<String> actual = masterControl.start(input);
        assertEquals(6, actual.size());
        assertEquals("Checking 12345678 200.00 5.00", actual.get(0));
        assertEquals("Deposit 12345678 500", actual.get(1));
        assertEquals("Transfer 12345678 98765432 300", actual.get(2));
        assertEquals("Savings 98765432 300.00 5.00", actual.get(3));
        assertEquals("Transfer 12345678 98765432 300", actual.get(4));
        assertEquals("Transfer 12345678 98765432 300", actual.get(5));
    }

    @Test
    void test_scenario_two() {
        input.add("Create Cd 12345678 1.2 1000");
        input.add("create savings 87654321 0");
        input.add("Deposit 12345678 500");
        input.add("Deposit 87654321 500");
        input.add("pass 12");
        input.add("transfer 12345678 87654321 100");
        List<String> actual = masterControl.start(input);
        assertEquals("Cd 12345678 1049.14 1.20", actual.get(0));
        assertEquals("Savings 87654321 500.00 0.00", actual.get(1));
        assertEquals("Deposit 87654321 500", actual.get(2));
        assertEquals("Deposit 12345678 500", actual.get(3));
        assertEquals("transfer 12345678 87654321 100", actual.get(4));
    }


}
