package banking;

import static java.lang.Character.isDigit;

public class TransferCommandValidator extends CommandValidator {

    public TransferCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (!validateCommandArgumentCount(command)) {
            return false;
        }
        return validateID(command) && validateWithdraw(command) && validateTransferAmount(command);

    }

    public boolean validateWithdraw(String command) {
        return validateWithdrawCount(command) && validateWithdrawType(command);
    }


    public boolean validateWithdrawCount(String command) {
        String id = command.split(" ")[1];
        return bank.checkWithdrawCount(id);
    }

    public boolean validateWithdrawType(String command) {
        String id = command.split(" ")[1];
        return bank.checkIfAccountCanTransfer(id);
    }


    public boolean validateTransferAmount(String command) {
        float amount = Float.parseFloat(command.split(" ")[3]);
        String id = command.split(" ")[1];
        String id2 = command.split(" ")[2];
        return validateWithdrawAmount(id, amount) && validateDepositAmount(id2, amount);
    }

    public boolean validateID(String command) {
        if (validateIDLength(command) && validateIDExists(command) && validateIDDigits(command)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean validateIDExists(String command) {
        String id1 = command.split(" ")[1];
        String id2 = command.split(" ")[2];
        return bank.accountExistsByID(id1) && bank.accountExistsByID(id2) && !(id1.equals(id2));

    }

    @Override
    public boolean validateIDLength(String command) {
        String id1 = command.split(" ")[1];
        String id2 = command.split(" ")[2];
        return id1.length() == 8 && id2.length() == 8;
    }

    @Override
    public boolean validateIDDigits(String command) {
        String id1 = command.split(" ")[1];
        String id2 = command.split(" ")[2];
        for (int i = 0; i < id1.length(); ++i) {
            if (!(isDigit(id1.charAt(i)) && isDigit(id2.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean validateCommandArgumentCount(String command) {
        return command.split(" ").length == 4;
    }


}
