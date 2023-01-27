package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public static final String QUICK_ID = "12345678";
    public static final String QUICK_ID2 = "12345679";
    public static final float QUICK_APR = 5;
    public static final float QUICK_AMOUNT = 1000;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void create_checking_account() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void create_two_checking_accounts() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.createCheckingAccount(QUICK_ID2, QUICK_APR + 1);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_ID2, bank.getAccounts().get(QUICK_ID2).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(QUICK_APR + 1, bank.getAccounts().get(QUICK_ID2).getAPR());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
        assertEquals(0, bank.getAccounts().get(QUICK_ID2).getBalance());
    }

    @Test
    void create_savings_account() {
        bank.createSavingsAccount(QUICK_ID, QUICK_APR);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void create_two_savings_accounts() {
        bank.createSavingsAccount(QUICK_ID, QUICK_APR);
        bank.createSavingsAccount(QUICK_ID2, QUICK_APR + 1);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_ID2, bank.getAccounts().get(QUICK_ID2).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(QUICK_APR + 1, bank.getAccounts().get(QUICK_ID2).getAPR());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
        assertEquals(0, bank.getAccounts().get(QUICK_ID2).getBalance());
    }

    @Test
    void create_cd_account() {
        bank.createCdAccount(QUICK_ID, QUICK_AMOUNT, QUICK_APR);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void create_two_cd_accounts() {
        bank.createCdAccount(QUICK_ID, QUICK_AMOUNT, QUICK_APR);
        bank.createCdAccount(QUICK_ID2, QUICK_AMOUNT + 1, QUICK_APR + 1);
        assertEquals(QUICK_ID, bank.getAccounts().get(QUICK_ID).getID());
        assertEquals(QUICK_ID2, bank.getAccounts().get(QUICK_ID2).getID());
        assertEquals(QUICK_APR, bank.getAccounts().get(QUICK_ID).getAPR());
        assertEquals(QUICK_APR + 1, bank.getAccounts().get(QUICK_ID2).getAPR());
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID).getBalance());
        assertEquals(QUICK_AMOUNT + 1, bank.getAccounts().get(QUICK_ID2).getBalance());
    }


    @Test
    void deposit_to_checking_account() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void deposit_to_savings_account() {
        bank.createSavingsAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void withdraw_from_checking_account() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.withdrawFromAccount(QUICK_ID, QUICK_AMOUNT - 500);
        assertEquals(QUICK_AMOUNT - 500, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void withdraw_from_savings_account() {
        bank.createSavingsAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.withdrawFromAccount(QUICK_ID, QUICK_AMOUNT - 500);
        assertEquals(QUICK_AMOUNT - 500, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void withdraw_from_cd_account() {
        bank.createCdAccount(QUICK_ID, QUICK_AMOUNT, QUICK_APR);
        bank.withdrawFromAccount(QUICK_ID, QUICK_AMOUNT - 500);
        assertEquals(QUICK_AMOUNT - 500, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void account_balance_cannot_be_less_than_zero() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.withdrawFromAccount(QUICK_ID, QUICK_AMOUNT + 500);
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void withdraw_amount_equal_to_balance() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.withdrawFromAccount(QUICK_ID, QUICK_AMOUNT);
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void transfer_between_accounts() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.createSavingsAccount(QUICK_ID2, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.transferBetweenAccounts(QUICK_ID, QUICK_ID2, QUICK_AMOUNT);
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void transfer_amount_greater_than_balance() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.createSavingsAccount(QUICK_ID2, QUICK_APR);
        bank.depositToAccount(QUICK_ID, QUICK_AMOUNT);
        bank.transferBetweenAccounts(QUICK_ID, QUICK_ID2, QUICK_AMOUNT + 500);
        assertEquals(QUICK_AMOUNT, bank.getAccounts().get(QUICK_ID2).getBalance());
        assertEquals(0, bank.getAccounts().get(QUICK_ID).getBalance());
    }

    @Test
    void increment_age_works() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, 1000);
        bank.passTime(60);
        assertEquals(bank.getAccounts().get(QUICK_ID).getAge(), 60);
    }

    @Test
    void pass_time_resets_withdraw_limit() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, 1000);
        bank.withdrawFromAccount(QUICK_ID, 300);
        assertEquals(bank.getAccounts().get(QUICK_ID).getMonthlyWithdrawCount(), 1);
        bank.passTime(1);
        assertEquals(bank.getAccounts().get(QUICK_ID).getMonthlyWithdrawCount(), 0);
    }

    @Test
    void pass_time_deducts_25_from_account_below_100() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        bank.depositToAccount(QUICK_ID, 75);
        bank.passTime(1);
        assertEquals(bank.getAccounts().get(QUICK_ID).getBalance(), 50);
    }

    @Test
    void pass_time_doesnt_deduct_25_from_account_at_100() {
        bank.createCheckingAccount(QUICK_ID, 0);
        bank.depositToAccount(QUICK_ID, 100);
        bank.passTime(1);
        assertEquals(bank.getAccounts().get(QUICK_ID).getBalance(), 100);
    }

    @Test
    void withdraw_from_cd_at_12_months() {
        bank.createCdAccount("12345678", 2000, 5);
        bank.passTime(12);
        assertTrue(bank.checkWithdrawAge("12345678"));
    }

    @Test
    void pass_time_closes_accounts() {
        bank.createCheckingAccount(QUICK_ID, QUICK_APR);
        assertEquals(bank.getAccounts().size(), 1);
        bank.passTime(1);
        assertEquals(bank.getAccounts().size(), 0);
    }


}
