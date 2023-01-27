package banking;

public class CreateCommandProcessor extends CommandProcessor {

    public CreateCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        String type = command.toLowerCase().split(" ")[1];
        String id = command.split(" ")[2];
        float apr = Float.parseFloat(command.split(" ")[3]);
        if (type.equals("checking")) {
            bank.createCheckingAccount(id, apr);
        } else if (type.equals("savings")) {
            bank.createSavingsAccount(id, apr);
        } else if (type.equals("cd")) {
            float amount = Float.parseFloat(command.split(" ")[4]);
            bank.createCdAccount(id, amount, apr);
        }
    }
}
