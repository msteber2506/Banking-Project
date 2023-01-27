package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    private List<String> validCommandList;
    private List<String> invalidCommandList;

    public CommandStorage() {
        this.validCommandList = new ArrayList<>();
        this.invalidCommandList = new ArrayList<>();
    }

    public void addInvalidCommand(String command) {
        invalidCommandList.add(command);
    }

    public void addValidCommand(String command) {
        validCommandList.add(command);
    }

    public List<String> getInvalidCommands() {
        return invalidCommandList;
    }

    public List<String> getValidCommands() {
        return validCommandList;
    }
}
