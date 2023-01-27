package banking;

public class WithdrawCommandValidator extends CommandValidator {
    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (!validateCommandArgumentCount(command)) {
            return false;
        }
        if (validateID(command) && validateAmount(command) && validateWithdrawCount(command) && validateWithdrawAge(command)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean validateWithdrawAge(String command) {
        String id = command.split(" ")[1];
        return bank.checkWithdrawAge(id);
    }

    public boolean validateWithdrawCount(String command) {
        String id = command.split(" ")[1];
        return bank.checkWithdrawCount(id);
    }


    public boolean validateAmount(String command) {
        float amount = Float.parseFloat(command.split(" ")[2]);
        String id = command.split(" ")[1];
        return validateWithdrawAmount(id, amount);
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
