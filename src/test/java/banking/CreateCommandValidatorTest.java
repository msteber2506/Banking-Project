package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_checking_account_create_command() {
        boolean actual = commandValidator.validate("create checking 12345678 5.67");
        assertTrue(actual);
    }

    @Test
    void valid_savings_account_create_command() {
        boolean actual = commandValidator.validate("create savings 12345678 5");
        assertTrue(actual);
    }

    @Test
    void valid_cd_account_create_command() {
        boolean actual = commandValidator.validate("create cd 12345678 5.0 1000");
        assertTrue(actual);
    }

    @Test
    void duplicate_account_id_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("create checking 12345678 5");
        assertFalse(actual);
    }

    @Test
    void account_id_cant_be_more_than_8_digits() {
        boolean actual = commandValidator.validate("create checking 123456789 5");
        assertFalse(actual);
    }

    @Test
    void account_id_cant_be_less_than_8_digits() {
        boolean actual = commandValidator.validate("create checking 123456 5");
        assertFalse(actual);
    }

    @Test
    void account_id_is_only_numeral_digits() {
        boolean actual = commandValidator.validate("create checking -a!9%^:i 5");
        assertFalse(actual);
    }

    @Test
    void zero_apr_is_valid() {
        boolean actual = commandValidator.validate("create checking 12345678 0");
        assertTrue(actual);
    }

    @Test
    void ten_apr_is_valid() {
        boolean actual = commandValidator.validate("create checking 12345678 10.0");
        assertTrue(actual);
    }

    @Test
    void apr_cant_be_less_than_zero() {
        boolean actual = commandValidator.validate("create checking 12345678 -5");
        assertFalse(actual);
    }

    @Test
    void apr_cant_be_greater_than_ten() {
        boolean actual = commandValidator.validate("create checking 12345678 100");
        assertFalse(actual);
    }

    @Test
    void missing_id_is_invalid() {
        boolean actual = commandValidator.validate("create checking 5");
        assertFalse(actual);
    }

    @Test
    void missing_apr_is_invalid() {
        boolean actual = commandValidator.validate("create checking 12345678");
        assertFalse(actual);
    }

    @Test
    void missing_type_is_invalid() {
        boolean actual = commandValidator.validate("create 12345678 5");
        assertFalse(actual);
    }

    @Test
    void missing_create_is_invalid() {
        boolean actual = commandValidator.validate("checking 12345678 5");
        assertFalse(actual);
    }

    @Test
    void missing_amount_is_invalid() {
        boolean actual = commandValidator.validate("create cd 12345678 5");
        assertFalse(actual);
    }

    @Test
    void too_many_argument_is_invalid() {
        boolean actual = commandValidator.validate("create cd 12345678 5 1000 abc");
        assertFalse(actual);
    }

    @Test
    void five_create_checking_arguments_is_invalid() {
        boolean actual = commandValidator.validate("create checking 12345678 5 abc");
        assertFalse(actual);
    }

    @Test
    void six_create_cd_arguments_is_invalid() {
        boolean actual = commandValidator.validate("create cd 12345678 5");
        assertFalse(actual);
    }

    @Test
    void empty_command_is_invalid() {
        boolean actual = commandValidator.validate("");
        assertFalse(actual);
    }

    @Test
    void typo_in_create_is_invalid() {
        boolean actual = commandValidator.validate("crate checking 12345678 5");
        assertFalse(actual);
    }

    @Test
    void typo_in_type_is_invalid() {
        boolean actual = commandValidator.validate("create ceking 12345678 5");
        assertFalse(actual);
    }

    @Test
    void command_is_case_insensitive() {
        boolean actual = commandValidator.validate("CrEaTe ChEcKiNg 12345678 5");
        assertTrue(actual);
    }

    @Test
    void cd_amount_cant_be_less_than_1000() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 500");
        assertFalse(actual);
    }

    @Test
    void cd_amount_can_be_1000() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 1000");
        assertTrue(actual);
    }

    @Test
    void cd_amount_cant_be_more_than_10000() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 50000");
        assertFalse(actual);
    }

    @Test
    void cd_amount_can_be_10000() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 10000");
        assertTrue(actual);
    }

    @Test
    void cd_amount_cant_be_0() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 0");
        assertFalse(actual);
    }

    @Test
    void cd_amount_cant_be_smaller_than_0() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 -500");
        assertFalse(actual);
    }

    @Test
    void cd_amount_cant_have_special_characters_or_letters() {
        boolean actual = commandValidator.validate("Create cd 12345678 5 1k");
        assertFalse(actual);
    }

    @Test
    void apr_cant_have_special_characters_or_letters() {
        boolean actual = commandValidator.validate("Create checking 12345678 5%");
        assertFalse(actual);
    }


}

