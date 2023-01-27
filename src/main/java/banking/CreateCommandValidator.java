package banking;

public class CreateCommandValidator extends CommandValidator {


    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (!validateCommandArgumentCount(command)) {
            return false;
        }
        if (validateID(command) && validateAPR(command) && validateCommandArgumentCount(command) && validateAmount(command)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAPR(String command) {
        try {
            float apr = Float.parseFloat(command.split(" ")[3]);
            if (apr < 0 || apr > 10) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateAmount(String command) {
        String type = command.split(" ")[1];
        if (type.equals("cd")) {
            try {
                float amount = Float.parseFloat(command.split(" ")[4]);
                return !(amount < 1000 || amount > 10000);
            } catch (Exception e) {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean validateID(String command) {
        if (validateIDLength(command) && validateIdIsUnique(command) && validateIDDigits(command)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateIDLength(String command) {
        String id = command.split(" ")[2];
        return id.length() == 8;
    }


    public boolean validateIdIsUnique(String command) {
        String id = command.split(" ")[2];
        return !bank.accountExistsByID(id);
    }


    public boolean validateCommandArgumentCount(String command) {
        String type = command.toLowerCase().split(" ")[1];
        if (type.equals("cd")) {
            return command.split(" ").length == 5;
        } else if (type.equals("checking") || type.equals("savings")) {
            return command.split(" ").length == 4;
        } else {
            return false;
        }
    }

}
