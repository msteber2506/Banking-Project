package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OutputWindow {

    private Bank bank;
    private List<String> messageList;
    private CommandStorage commandStorage;

    public OutputWindow(Bank bank, CommandStorage commandStorage) {
        this.bank = bank;
        this.messageList = new ArrayList<>();
        this.commandStorage = commandStorage;
    }

    public List<String> getMessageList() {
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.FLOOR);
        for (String command : getCreateCommands()) {
            String[] args = command.toLowerCase().split(" ");
            if (bank.accountExistsByID(args[2])) {
                String status = args[1].substring(0, 1).toUpperCase() +
                        args[1].substring(1) + " " + args[2] + " " +
                        formatter.format(bank.getAccounts().get(args[2]).getBalance()) + " " +
                        formatter.format(bank.getAccounts().get(args[2]).getAPR());
                messageList.add(status);
                addTransactionHistoryToMessageList(args[2]);
            }
        }
        addInvalidCommandsToMessageList();
        return messageList;
    }

    public void addInvalidCommandsToMessageList() {
        for (int i = 0; i < commandStorage.getInvalidCommands().size(); i++) {
            String command = commandStorage.getInvalidCommands().get(i);
            messageList.add(command);
        }
    }

    public List<String> getCreateCommands() {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < commandStorage.getValidCommands().size(); i++) {
            String command = commandStorage.getValidCommands().get(i);
            if (command.toLowerCase().contains("create")) {
                out.add(command);
            }
        }
        return out;
    }

    public void addTransactionHistoryToMessageList(String id) {
        for (int i = 0; i < commandStorage.getValidCommands().size(); i++) {
            String command = commandStorage.getValidCommands().get(i);
            if (command.contains(id) && !command.toLowerCase().contains("create")) {
                messageList.add(command);
            }
        }
    }


}
