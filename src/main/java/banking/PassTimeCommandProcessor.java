package banking;

public class PassTimeCommandProcessor extends CommandProcessor {


    public PassTimeCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processCommand(String command) {
        int month = Integer.parseInt(command.split(" ")[1]);
        bank.passTime(month);
    }
}
