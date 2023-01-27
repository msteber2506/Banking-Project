package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        bank.createCheckingAccount("12345678", 5);
        bank.createSavingsAccount("23456789", 5);
        bank.depositToAccount("12345678", 500);
        bank.depositToAccount("23456789", 500);
    }

    @Test
    void valid_transfer_command() {
        boolean actual = commandValidator.validate("transfer 12345678 23456789 300");
        assertTrue(actual);
    }

    @Test
    void account_cant_transfer_to_itself() {
        boolean actual = commandValidator.validate("transfer 12345678 12345678 300");
        assertFalse(actual);
    }

    @Test
    void cant_transfer_to_cd() {
        bank.createCdAccount("87654321", 1000, 0);
        bank.passTime(12);
        boolean actual = commandValidator.validate("transfer 12345678 87654321 300");
        assertFalse(actual);
    }

    @Test
    void cant_transfer_from_cd() {
        bank.createCdAccount("87654321", 1000, 0);
        bank.passTime(12);
        boolean actual = commandValidator.validate("transfer 87654321 12345678 1000");
        assertFalse(actual);
    }

    @Test
    void cant_have_too_many_arguments() {
        boolean actual = commandValidator.validate("transfer 12345678 23456789 300 5");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_deposit_amount() {
        boolean actual = commandValidator.validate("transfer 12345678 23456789 0");
        assertTrue(actual);
    }

    @Test
    void too_many_transfer_in_a_month_is_invalid() {
        bank.transferBetweenAccounts("23456789", "12345678", 100);
        boolean actual = commandValidator.validate("transfer 23456789 12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_id_length() {
        bank.createCheckingAccount("987654321", 1);
        boolean actual = commandValidator.validate("transfer 23456789 987654321 100");
        assertFalse(actual);
    }

    @Test
    void id_can_only_have_digits() {
        bank.createCheckingAccount("8765432a", 1);
        boolean actual = commandValidator.validate("transfer 23456789 8765432a 100");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_withdraw_amount() {
        boolean actual = commandValidator.validate("transfer 23456789 12345678 1001");
        assertFalse(actual);
    }

}
