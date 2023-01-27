package banking;

public class CommandProcessor {

    protected Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        String operation = command.toLowerCase().split(" ")[0];
        if (operation.equals("create")) {
            processCreateCommand(command);
        } else if (operation.equals("deposit")) {
            processDepositCommand(command);
        } else if (operation.equals("withdraw")) {
            processWithdrawCommand(command);
        } else if (operation.equals("transfer")) {
            processTransferCommand(command);
        } else if (operation.equals("pass")) {
            processPassCommand(command);
        }
    }

    public void processCreateCommand(String command) {
        CreateCommandProcessor createCommandProcessor = new CreateCommandProcessor(bank);
        createCommandProcessor.processCommand(command);
    }

    public void processDepositCommand(String command) {
        DepositCommandProcessor depositCommandProcessor = new DepositCommandProcessor(bank);
        depositCommandProcessor.processCommand(command);
    }

    public void processWithdrawCommand(String command) {
        WithdrawCommandProcessor withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
        withdrawCommandProcessor.processCommand(command);
    }

    public void processTransferCommand(String command) {
        TransferCommandProcessor transferCommandProcessor = new TransferCommandProcessor(bank);
        transferCommandProcessor.processCommand(command);
    }

    public void processPassCommand(String command) {
        PassTimeCommandProcessor passTimeCommandProcessor = new PassTimeCommandProcessor(bank);
        passTimeCommandProcessor.processCommand(command);
    }


}
