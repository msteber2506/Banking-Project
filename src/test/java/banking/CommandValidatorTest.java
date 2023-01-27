package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_create_command() {
        boolean actual = commandValidator.validate("create checking 12345678 5");
        assertTrue(actual);
    }

    @Test
    void create_is_case_insensitive() {
        boolean actual = commandValidator.validate("CREATE CHECKING 12345678 5");
        assertTrue(actual);
    }

    @Test
    void typo_in_create_is_invalid() {
        boolean actual = commandValidator.validate("crate cheking 12345678 5");
        assertFalse(actual);
    }

    @Test
    void wrong_number_of_arguments_is_invalid_for_create() {
        boolean actual = commandValidator.validate("crate cheking 12345678 5 1000");
        assertFalse(actual);
    }

    @Test
    void valid_deposit_command() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void deposit_is_case_insensitive() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("DEPOSIT 12345678 500");
        assertTrue(actual);
    }

    @Test
    void typo_in_deposit_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("depsit 12345678 5");
        assertFalse(actual);
    }

    @Test
    void wrong_number_of_arguments_is_invalid_for_deposit() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 12345678 5 5");
        assertFalse(actual);
    }

    @Test
    void unknown_command_is_invalid() {
        boolean actual = commandValidator.validate("delete 12345678 5");
        assertFalse(actual);
    }


}
