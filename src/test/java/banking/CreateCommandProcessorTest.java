package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCommandProcessorTest {

    private final String QUICK_CREATE_CHECKING_COMMAND1 = "create checking 12345678 5.0";
    private final String QUICK_CREATE_CHECKING_COMMAND2 = "create checking 23456789 5.0";
    private final String QUICK_CREATE_SAVINGS_COMMAND1 = "create savings 12345678 5.0";
    private final String QUICK_CREATE_SAVINGS_COMMAND2 = "create savings 23456789 5.0";
    private final String QUICK_CREATE_CD_COMMAND1 = "create cd 12345678 5.0 1000";
    private final String QUICK_CREATE_CD_COMMAND2 = "create cd 23456789 5.0 1000";
    private CreateCommandProcessor createCommandProcessor;
    private Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        createCommandProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void create_one_checking_account() {
        createCommandProcessor.processCommand(QUICK_CREATE_CHECKING_COMMAND1);
        String id = QUICK_CREATE_CHECKING_COMMAND1.split(" ")[2];
        float apr = Float.parseFloat(QUICK_CREATE_CHECKING_COMMAND1.split(" ")[3]);
        assertEquals(1, bank.getAccounts().size());
        assertEquals(id, bank.getAccounts().get(id).getID());
        assertEquals(apr, bank.getAccounts().get(id).getAPR());
    }

    @Test
    void create_two_checking_accounts() {
        createCommandProcessor.processCommand(QUICK_CREATE_CHECKING_COMMAND1);
        createCommandProcessor.processCommand(QUICK_CREATE_CHECKING_COMMAND2);
        String id1 = QUICK_CREATE_CHECKING_COMMAND1.split(" ")[2];
        String id2 = QUICK_CREATE_CHECKING_COMMAND1.split(" ")[2];
        float apr1 = Float.parseFloat(QUICK_CREATE_CHECKING_COMMAND1.split(" ")[3]);
        float apr2 = Float.parseFloat(QUICK_CREATE_CHECKING_COMMAND2.split(" ")[3]);
        assertEquals(2, bank.getAccounts().size());
        assertEquals(id1, bank.getAccounts().get(id1).getID());
        assertEquals(id2, bank.getAccounts().get(id2).getID());
        assertEquals(apr1, bank.getAccounts().get(id1).getAPR());
        assertEquals(apr2, bank.getAccounts().get(id2).getAPR());
    }

    @Test
    void create_one_savings_account() {
        createCommandProcessor.processCommand(QUICK_CREATE_SAVINGS_COMMAND1);
        String id = QUICK_CREATE_SAVINGS_COMMAND1.split(" ")[2];
        float apr = Float.parseFloat(QUICK_CREATE_SAVINGS_COMMAND1.split(" ")[3]);
        assertEquals(1, bank.getAccounts().size());
        assertEquals(id, bank.getAccounts().get(id).getID());
        assertEquals(apr, bank.getAccounts().get(id).getAPR());
    }

    @Test
    void create_two_savings_accounts() {
        createCommandProcessor.processCommand(QUICK_CREATE_SAVINGS_COMMAND1);
        createCommandProcessor.processCommand(QUICK_CREATE_SAVINGS_COMMAND2);
        String id1 = QUICK_CREATE_SAVINGS_COMMAND1.split(" ")[2];
        String id2 = QUICK_CREATE_SAVINGS_COMMAND1.split(" ")[2];
        float apr1 = Float.parseFloat(QUICK_CREATE_SAVINGS_COMMAND1.split(" ")[3]);
        float apr2 = Float.parseFloat(QUICK_CREATE_SAVINGS_COMMAND2.split(" ")[3]);
        assertEquals(2, bank.getAccounts().size());
        assertEquals(id1, bank.getAccounts().get(id1).getID());
        assertEquals(id2, bank.getAccounts().get(id2).getID());
        assertEquals(apr1, bank.getAccounts().get(id1).getAPR());
        assertEquals(apr2, bank.getAccounts().get(id2).getAPR());
    }

    @Test
    void create_one_cd_account() {
        createCommandProcessor.processCommand(QUICK_CREATE_CD_COMMAND1);
        String id = QUICK_CREATE_CD_COMMAND1.split(" ")[2];
        float apr = Float.parseFloat(QUICK_CREATE_CD_COMMAND1.split(" ")[3]);
        assertEquals(1, bank.getAccounts().size());
        assertEquals(id, bank.getAccounts().get(id).getID());
        assertEquals(apr, bank.getAccounts().get(id).getAPR());
    }

    @Test
    void create_two_cd_accounts() {
        createCommandProcessor.processCommand(QUICK_CREATE_CD_COMMAND1);
        createCommandProcessor.processCommand(QUICK_CREATE_CD_COMMAND2);
        String id1 = QUICK_CREATE_CD_COMMAND1.split(" ")[2];
        String id2 = QUICK_CREATE_CD_COMMAND1.split(" ")[2];
        float apr1 = Float.parseFloat(QUICK_CREATE_CD_COMMAND1.split(" ")[3]);
        float apr2 = Float.parseFloat(QUICK_CREATE_CD_COMMAND1.split(" ")[3]);
        assertEquals(2, bank.getAccounts().size());
        assertEquals(id1, bank.getAccounts().get(id1).getID());
        assertEquals(id2, bank.getAccounts().get(id2).getID());
        assertEquals(apr1, bank.getAccounts().get(id1).getAPR());
        assertEquals(apr2, bank.getAccounts().get(id2).getAPR());
    }
}
