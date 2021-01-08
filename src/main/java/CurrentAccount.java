
public class CurrentAccount extends Account{

    public CurrentAccount(int idAccount, Customer client) {
        super(idAccount, 1.1, 0, 0.11,0, "CurrentAccount", client);
    }

    @Override
    public String withdrawMoney(double money) {
        if (money <= getAccountAmount()){
            return super.withdrawMoney(money);
        }
        else
            return "Not enough money in your account";
    }
}
