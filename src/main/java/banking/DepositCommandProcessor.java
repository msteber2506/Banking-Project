package banking;

public class DepositCommandProcessor extends CommandProcessor {

    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        String id = command.toLowerCase().split(" ")[1];
        float amount = Float.parseFloat(command.split(" ")[2]);
        bank.depositToAccount(id, amount);
    }
}
