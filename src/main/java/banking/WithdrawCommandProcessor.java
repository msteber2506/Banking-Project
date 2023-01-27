package banking;

public class WithdrawCommandProcessor extends CommandProcessor {


    public WithdrawCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        String id = command.toLowerCase().split(" ")[1];
        float amount = Float.parseFloat(command.split(" ")[2]);
        bank.withdrawFromAccount(id, amount);
    }
}
