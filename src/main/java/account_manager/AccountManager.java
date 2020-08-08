package account_manager;

import account_manager.commands.PaymentCommand;
import account_manager.commands.ReversalCommand;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static account_manager.constants.Constants.ID_COLUMN;
import static account_manager.constants.Constants.TYPE_COLUMN;

public class AccountManager {

    private static final String TYPE_PAYMENT = "PAYMENT";
    private static final String TYPE_REVERSAL = "REVERSAL";

    private String accountId;
    private String from;
    private String to;
    private List<List<String>> transactions;

    private BigDecimal balance = new BigDecimal(0.00);
    private Integer numberOfTransactions = 0;

    public AccountManager(String accountId, String from, String to, List<List<String>> transactions) {
        this.accountId = accountId;
        this.from = from;
        this.to = to;
        this.transactions = transactions;

        // remove csv header
        transactions.remove(0);
    }

    public void count() {
        transactions.forEach(transaction -> {
            switch (transaction.get(TYPE_COLUMN).trim()) {
                case TYPE_PAYMENT:
                    PaymentCommand paymentCommand = new PaymentCommand(this, transaction);
                    paymentCommand.execute();
                    break;
                case TYPE_REVERSAL:
                    ReversalCommand reversalCommand = new ReversalCommand(this, transaction);
                    reversalCommand.execute();
                    break;
                default:
                    System.out.println("Warning: unknown payment type, type=" + transaction.get(TYPE_COLUMN));
            }
        });

        System.out.println("Relative balance for the period is: " + balance);
        System.out.println("Number of transactions included is: " + numberOfTransactions);
    }

    public List<String> getRelatedTransaction(String relatedTransactionId) {
        Optional<List<String>> relatedTransaction = transactions.stream().filter(transaction ->
            relatedTransactionId.trim().equals(transaction.get(ID_COLUMN))
        ).findFirst();

        return relatedTransaction.isPresent() ? relatedTransaction.get() : null;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Integer numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

}
