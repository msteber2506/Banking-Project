package banking;

import java.util.List;

public class MasterControl {
    private CommandValidator commandValidator;
    private CommandProcessor commandProcessor;
    private CommandStorage commandStorage;
    private OutputWindow outputWindow;

    public MasterControl(CommandValidator commandValidator,
                         CommandProcessor commandProcessor,
                         CommandStorage commandStorage,
                         OutputWindow outputWindow) {
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
        this.outputWindow = outputWindow;
    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
                commandStorage.addValidCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        return outputWindow.getMessageList();
    }

}
