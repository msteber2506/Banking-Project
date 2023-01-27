package banking;

public class DepositCommandValidator extends CommandValidator {

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (!validateCommandArgumentCount(command)) {
            return false;
        }
        if (validateID(command) && validateAmount(command)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean validateAmount(String command) {
        float amount = Float.parseFloat(command.split(" ")[2]);
        String id = command.split(" ")[1];
        return validateDepositAmount(id, amount);
    }

    public boolean validateID(String command) {
        if (validateIDLength(command) && validateIDDigits(command) && validateIDExists(command)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateCommandArgumentCount(String command) {
        if (command.split(" ").length != 3) {
            return false;
        } else {
            return true;
        }
    }

}
