import java.util.List;

public class HousingAccount extends Account{

    public HousingAccount(int idAccount, Customer client) {
        super(idAccount, 1.1, 0, 0.22, 0, "HousingAccount", client);
    }

    @Override
    public String withdrawMoney(double money) {
        return "You cannot withdraw money from a Housing Account";
    }

    @Override
    public String getMonthlyReport(int month) {
        double accountInterest = getAccountAmount() * getInterest();
        double balance = 0;
        for (int i = 0; i < month; i++) {
            balance = balance + accountInterest;
        }
        return "In " + month + "months the balance will be: " + balance + "$";
    }
}
