package account_manager.commands;

import account_manager.AccountManager;
import account_manager.Command;
import account_manager.utils.DateUtil;

import java.math.BigDecimal;
import java.util.List;
import static account_manager.constants.Constants.*;

public class PaymentCommand implements Command {

    AccountManager accountManager;
    List<String> transaction;

    public PaymentCommand(AccountManager accountManager, List<String> transaction) {
        this.accountManager = accountManager;
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        if(accountManager.getAccountId().trim().equals(transaction.get(FROM_ACCOUNT_COLUMN))
                && DateUtil.stringToLocalDateTime(accountManager.getFrom())
                    .isBefore(DateUtil.stringToLocalDateTime(transaction.get(CREATED_AT_COLUMN)))
                && DateUtil.stringToLocalDateTime(accountManager.getTo())
                    .isAfter(DateUtil.stringToLocalDateTime(transaction.get(CREATED_AT_COLUMN)))) {
            BigDecimal updatedBalance =  accountManager.getBalance().subtract(new BigDecimal(transaction.get(AMOUNT_COLUMN)));
            accountManager.setBalance(updatedBalance);
            accountManager.setNumberOfTransactions(accountManager.getNumberOfTransactions() + 1);
        }
    }
}
