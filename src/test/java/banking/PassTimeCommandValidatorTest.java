package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_pass_time_command() {
        boolean actual = commandValidator.validate("pass 30");
        assertTrue(actual);
    }

    @Test
    void months_cant_be_zero() {
        boolean actual = commandValidator.validate("pass 0");
        assertFalse(actual);
    }

    @Test
    void months_cant_be_less_than_zero() {
        boolean actual = commandValidator.validate("pass -5");
        assertFalse(actual);
    }

    @Test
    void months_cant_be_greater_than_sixty() {
        boolean actual = commandValidator.validate("pass 100");
        assertFalse(actual);
    }

    @Test
    void months_can_be_one() {
        boolean actual = commandValidator.validate("pass 1");
        assertTrue(actual);
    }

    @Test
    void months_can_be_60() {
        boolean actual = commandValidator.validate("pass 60");
        assertTrue(actual);
    }

    @Test
    void months_can_only_be_integer() {
        boolean actual = commandValidator.validate("pass 30.5");
        assertFalse(actual);
    }

    @Test
    void missing_pass_is_invalid() {
        boolean actual = commandValidator.validate("60");
        assertFalse(actual);
    }

    @Test
    void missing_months_is_invalid() {
        boolean actual = commandValidator.validate("pass");
        assertFalse(actual);
    }

    @Test
    void more_than_2_arguments_is_invalid() {
        boolean actual = commandValidator.validate("pass 20 20");
        assertFalse(actual);
    }
}
