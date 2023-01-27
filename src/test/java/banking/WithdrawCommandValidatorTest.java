package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_withdraw_from_checking() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void valid_withdraw_from_checking_two_times() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        bank.withdrawFromAccount("12345678", 100);
        boolean actual = commandValidator.validate("withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void valid_withdraw_from_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void can_only_withdraw_once_a_month_for_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        bank.withdrawFromAccount("12345678", 100);
        boolean actual = commandValidator.validate("withdraw 12345678 100");
        assertFalse(actual);
    }


    @Test
    void cant_withdraw_from_cd_before_12_months() {
        bank.createCdAccount("12345678", 2000, 5);
        boolean actual = commandValidator.validate("withdraw 12345678 2000");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_cd_at_12_months() {
        bank.createCdAccount("12345678", 2000, 5);
        bank.passTime(12);
        boolean actual = commandValidator.validate("withdraw 12345678 2500");
        assertTrue(actual);
    }


    @Test
    void can_withdraw_from_cd_after_12_months() {
        bank.createCdAccount("12345678", 2000, 5);
        bank.passTime(15);
        boolean actual = commandValidator.validate("withdraw 12345678 2600");
        assertTrue(actual);
    }


    @Test
    void cant_withdraw_more_than_1000_for_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 1001");
        assertFalse(actual);
    }

    @Test
    void cant_withdraw_less_than_zero_for_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 -1");
        assertFalse(actual);
    }

    @Test
    void can_withdraw_zero_for_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    void can_withdraw_1000_for_savings() {
        bank.createSavingsAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }


    @Test
    void cant_withdraw_more_than_400_for_checking() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 401");
        assertFalse(actual);
    }

    @Test
    void cant_withdraw_less_than_zero_for_checking() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 -1");
        assertFalse(actual);
    }

    @Test
    void can_withdraw_zero_for_checking() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    void can_withdraw_400_for_savings() {
        bank.createCheckingAccount("12345678", 5);
        bank.depositToAccount("12345678", 1000);
        boolean actual = commandValidator.validate("withdraw 12345678 400");
        assertTrue(actual);
    }

    @Test
    void cant_withdraw_less_than_balance_for_cd() {
        bank.createCdAccount("12345678", 500, 5);
        bank.passTime(12);
        boolean actual = commandValidator.validate("withdraw 12345678 400");
        assertFalse(actual);
    }

    @Test
    void invalid_if_id_doesnt_exists() {
        boolean actual = commandValidator.validate("withdraw 12345678 400");
        assertFalse(actual);
    }

    @Test
    void more_than_3_arguments_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("withdraw 12345678 400 5");
        assertFalse(actual);
    }

    @Test
    void id_having_letters_is_invalid() {
        bank.createCheckingAccount("12345678", 5);
        boolean actual = commandValidator.validate("withdraw 1234567a 400");
        assertFalse(actual);
    }

    @Test
    void id_can_only_have_8_digits() {
        bank.createCheckingAccount("123456789", 5);
        boolean actual = commandValidator.validate("withdraw 123456789 400");
        assertFalse(actual);
    }


}
