package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_deposit_to_checking_command() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_to_savings_command() {
        bank.createSavingsAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void id_cant_be_more_than_8_digits() {
        bank.createCheckingAccount("123456789", 5);
        boolean actual = commandValidator.validate("deposit 123456789 500");
        assertFalse(actual);
    }

    @Test
    void id_cant_be_less_than_8_digits() {
        bank.createCheckingAccount("123456", 5);
        boolean actual = commandValidator.validate("deposit 123456 500");
        assertFalse(actual);
    }

    @Test
    void id_is_only_numeral_digits() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit -a!9%^:i 500");
        assertFalse(actual);
    }

    @Test
    void id_not_existing_is_invalid() {
        boolean actual = commandValidator.validate("deposit 12345678 5");
        assertFalse(actual);
    }

    @Test
    void missing_id_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 500");
        assertFalse(actual);
    }

    @Test
    void missing_deposit_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("12345678 500");
        assertFalse(actual);
    }

    @Test
    void missing_amount_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("deposit 12345678");
        assertFalse(actual);
    }

    @Test
    void depositing_to_cd_is_invalid() {
        bank.createCdAccount("12345678", 1000, 5);
        boolean actual = commandValidator.validate("deposit 12345678 500");
        assertFalse(actual);

    }

    @Test
    void typo_in_deposit_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("dsit 12345678 500");
        assertFalse(actual);
    }

    @Test
    void command_is_case_insensitive() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("dEpOsIt 12345678 500");
        assertTrue(actual);
    }

    @Test
    void depositing_0_to_savings_is_valid() {
        bank.createSavingsAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    void depositing_0_to_checking_is_valid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    void depositing_2500_to_savings_is_valid() {
        bank.createSavingsAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 2500");
        assertTrue(actual);
    }

    @Test
    void depositing_1000_to_checking_is_valid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void cant_deposit_less_than_zero_to_checking() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 -500");
        assertFalse(actual);
    }

    @Test
    void cant_deposit_less_than_zero_to_savings() {
        bank.createSavingsAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 -500");
        assertFalse(actual);
    }

    @Test
    void deposit_amount_cant_have_special_characters_or_letters() {
        bank.createSavingsAccount("12345678", 5);
        boolean actual = commandValidator.validate("Deposit 12345678 2,000");
        assertFalse(actual);
    }

    @Test
    void empty_command_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("");
        assertFalse(actual);
    }

}
