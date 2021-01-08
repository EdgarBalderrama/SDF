import java.util.List;

public class InvestmentFund extends Account{
    public InvestmentFund(int idAccount, Customer client) {
        super(idAccount, 1.1, 0, 0.33, 0, "InvestmentFund", client);
    }

    @Override
    public String withdrawMoney(double money) {
        if (money <= 600.0 && money <= getAccountAmount()){
            return super.withdrawMoney(money);
        }
        return "You cannot make this transaction";
    }
}
