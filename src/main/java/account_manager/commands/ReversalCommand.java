package account_manager.commands;

import account_manager.AccountManager;
import account_manager.Command;

import java.math.BigDecimal;
import java.util.List;

import static account_manager.constants.Constants.*;

public class ReversalCommand implements Command {

    AccountManager accountManager;
    List<String> transaction;

    public ReversalCommand(AccountManager accountManager, List<String> transaction) {
        this.accountManager = accountManager;
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        List<String> relatedTransaction = accountManager.getRelatedTransaction(transaction.get(RELATED_TRANSACTION_COLUMN));

        if(accountManager.getAccountId().trim().equals(relatedTransaction.get(FROM_ACCOUNT_COLUMN))) {
            BigDecimal updatedBalance = accountManager.getBalance().add(new BigDecimal(transaction.get(AMOUNT_COLUMN)));
            accountManager.setBalance(updatedBalance);
            accountManager.setNumberOfTransactions(accountManager.getNumberOfTransactions() - 1);
        }
    }
}
