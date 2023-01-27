package banking;

public class TransferCommandProcessor extends CommandProcessor {

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        String id1 = command.toLowerCase().split(" ")[1];
        String id2 = command.toLowerCase().split(" ")[2];
        float amount = Float.parseFloat(command.split(" ")[3]);
        bank.transferBetweenAccounts(id1, id2, amount);
    }
}
