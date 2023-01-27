package banking;

public class PassTimeCommandValidator extends CommandValidator {

    public PassTimeCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        return validateMonths(command) && validateCommandArgumentCount(command);
    }

    public boolean validateMonths(String command) {
        try {
            int months = Integer.parseInt(command.split(" ")[1]);
            if (1 <= months && months <= 60) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateCommandArgumentCount(String command) {
        return command.split(" ").length == 2;
    }
}
