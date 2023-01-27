package banking;

import static java.lang.Character.isDigit;

public class CommandValidator {

    protected Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        command = command.toLowerCase();
        boolean isValid = false;
        String operation = command.split(" ")[0];
        if (operation.equals("create")) {
            isValid = validateCreateCommand(command);
        } else if (operation.equals("deposit")) {
            isValid = validateDepositCommand(command);
        } else if (operation.equals("pass")) {
            isValid = validatePassCommand(command);
        } else if (operation.equals("withdraw")) {
            isValid = validateWithdrawCommand(command);
        } else if (operation.equals("transfer")) {
            isValid = validateTransferCommand(command);
        }
        return isValid;
    }

    public boolean validateCreateCommand(String command) {
        CreateCommandValidator createCommandValidator = new CreateCommandValidator(bank);
        return createCommandValidator.validate(command);
    }

    public boolean validateDepositCommand(String command) {
        DepositCommandValidator depositCommandValidator = new DepositCommandValidator(bank);
        return depositCommandValidator.validate(command);
    }

    public boolean validateWithdrawCommand(String command) {
        WithdrawCommandValidator withdrawCommandValidator = new WithdrawCommandValidator(bank);
        return withdrawCommandValidator.validate(command);
    }

    public boolean validateTransferCommand(String command) {
        TransferCommandValidator transferCommandValidator = new TransferCommandValidator(bank);
        return transferCommandValidator.validate(command);
    }

    public boolean validatePassCommand(String command) {
        PassTimeCommandValidator passTimeCommandValidator = new PassTimeCommandValidator(bank);
        return passTimeCommandValidator.validate(command);
    }

    public boolean validateIDDigits(String command) {
        String id = command.split(" ")[2];
        for (int i = 0; i < id.length(); ++i) {
            if (!isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean validateIDExists(String command) {
        String id = command.split(" ")[1];
        if (bank.accountExistsByID(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateIDLength(String command) {
        String id = command.split(" ")[1];
        if (id.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateWithdrawAmount(String id, float amount) {
        return bank.checkWithdrawAmount(id, amount) && amount >= 0;
    }

    public boolean validateDepositAmount(String id, float amount) {
        return bank.checkDepositAmount(id, amount) && amount >= 0;
    }

}
