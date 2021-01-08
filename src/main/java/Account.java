import java.util.HashMap;
import java.util.List;

public abstract class Account {
    private int idAccount;
    private double commission;
    private double balance;
    private double interest;
    private String type;

    private double accountInterest;
    private double accountAmount;
    private Customer client;

    public Account(int idAccount, double commission, double balance, double interest,  double accountAmount, String type, Customer client) {
        this.idAccount = idAccount;
        this.commission = commission;
        this.balance = balance;
        this.interest = interest;
        this.accountAmount = accountAmount;
        this.type = type;
        this.client = client;
    }

    public String enterMoney(double money) {
        accountAmount += money;
        Bank bank = Bank.getMyBank();
        bank.addAccountPoints(type, money);
        return "You entered: " + money + "$. " + "Current account amount is: " + accountAmount;
    }

    public String withdrawMoney(double money){
        accountAmount -= money;
        return "You withdrew: " + money + "$. " + "Current account amount is: " + accountAmount;
    }

    public String checkBalance(){
        accountInterest = accountAmount * interest;
        balance = balance + accountInterest - commission;
        return "Balance is: " + balance + "$";
    }

    public String changeClient(Customer customer, int id){
        this.client = customer;
        Bank bank = Bank.getMyBank();
        Customer customerToRemove = null;
        List<Customer> customers = bank.getCustomers();
        for (Customer customer1:customers) {
            if (customer1.getIdentificationNumber() == id) {
                //customers.remove(customer1);
                customerToRemove = customer1;
                //customers.add(customer);
            }
        }
        if (customerToRemove != null){
            customers.remove(customerToRemove);
            customers.add(customer);
        }
        bank.setCustomers(customers);
        return "New customer is: " + customer.getName() + " " + customer.getLastName();
    }

    public String getMonthlyReport(int month){
        accountInterest = accountAmount * interest;
        for (int i = 0; i < month; i++) {
            balance = balance + accountInterest - commission;
        }
        return "In " + month + " months the balance will be: " + balance + "$";
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAccountInterest() {
        return accountInterest;
    }

    public void setAccountInterest(double accountInterest) {
        this.accountInterest = accountInterest;
    }

    public double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }
}
