import java.util.HashMap;
import java.util.List;

public class Bank {
    private HashMap<Account, Integer> accountPoints;
    private List<Customer> customers;

    private static Bank myBank;

    public  static Bank getMyBank() {
        if (myBank == null) {
            myBank = new Bank();
        }
        return myBank;
    }

    public Bank(HashMap<Account, Integer> accountPoints, List<Customer> customers) {
        this.accountPoints = accountPoints;
        this.customers = customers;
    }

    public Bank() {
    }

    public String getBankInformation(){
        String returnString = "";
        for (Account account : accountPoints.keySet()) {
            returnString += "Account number: " + account.getIdAccount() + " Is type: " + account.getType() +" and has: " + accountPoints.get(account) + " account points. Belongs to: " + account.getClient().getLastName() + " " + account.getClient().getName() + "\n";
        }
        returnString += "\n";
        for (Customer customer: customers){
            returnString += "Customer " + customer.getLastName() + " " + customer.getName() + " has ID: " + customer.getIdentificationNumber() + "\n";
        }
        return returnString;
    }

    public String getCustomerInformation(int customerID){
        for (Customer customer: customers) {
            if (customer.getIdentificationNumber() == customerID){
                return "Customer information: " + "\n Name: " + customer.getName() + "\n Last name: " + customer.getLastName() +
                        "\n Address: " + customer.getAddress() + "\n Phone: " + customer.getPhone() +
                        "\n CellPhone: " + customer.getCellPhone() + "\n Account: " + customer.getAccount().getIdAccount() + "\n";
            }
        }
        return "Customer not found";
    }

    public boolean validAccount(int accountNumber){
        for (Account account : accountPoints.keySet()) {
            if (account.getIdAccount() == accountNumber)
                return true;
        }
        return false;
    }

    public Account getAccount(int accountNumber){
        for (Account account : accountPoints.keySet()) {
            if (account.getIdAccount() == accountNumber)
                return account;
        }
        return null;
    }

    public void addAccountPoints(String type, double money){
        int newPoints = 0;
        int currentPoints = 0;
        Account account1 = null;
        while (money - 10 >= 0){
            money -= 10;
            newPoints++;
        }
        for (Account account : accountPoints.keySet()) {
            if (account.getType().equals(type)){
                currentPoints = accountPoints.get(account);
                account1 = account;
            }
        }
        if (account1 != null){
            accountPoints.remove(account1);
            accountPoints.put(account1, newPoints + currentPoints);
        }

    }

    public Customer getCustomer(int clientID){
        for (Customer customer : customers) {
            if (customer.getIdentificationNumber() == clientID)
                return customer;
        }
        return null;
    }

    public Customer createCustomer(int id, String name, String lastName, String address, String phone, String cellPhone){
        for (Customer customer: customers) {
            if (customer.getIdentificationNumber() == id)
                return null;
        }
        Customer customer = new Customer(id, name, lastName, address, phone, cellPhone);
        customers.add(customer);
        return customer;
    }

    public String changeClient(Customer newCustomer, int idAccount){
        for (Account account : accountPoints.keySet()) {
            if (account.getIdAccount() == idAccount){
                account.setClient(newCustomer);
                customers.add(newCustomer);
                return "New client is: " + newCustomer.getLastName() + " " + newCustomer.getName();
            }
        }
        return "Not allowed to create this client";
    }

    public String replaceClient(Customer newCustomer, int idAccount, int idCustomer){
        boolean userExists = false;
        int currentAccountPoints = 0;
        Account currentAccount = null;
        for (Customer customer: customers) {
            if (customer.getIdentificationNumber() == idCustomer)
                userExists = true;
        }
        if (userExists){
            Customer customerToRemove = null;
            for (Customer customer1:customers) {
                if (customer1.getIdentificationNumber() == newCustomer.getIdentificationNumber()) {
                    customerToRemove = customer1;
                }
            }
            if (customerToRemove != null){
                customers.remove(customerToRemove);
                customers.add(newCustomer);
            }
            for (Account account : accountPoints.keySet()) {
                if (account.getIdAccount() == idAccount){
                    currentAccountPoints = accountPoints.get(account);
                    currentAccount = account;
                }
            }
            accountPoints.remove(currentAccount);
            currentAccount.setClient(newCustomer);
            accountPoints.put(currentAccount, currentAccountPoints);
            return "New customer is: " + newCustomer.getName() + " " + newCustomer.getLastName();
        }
        else
            return "The user doesn't exists";
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public static void setMyBank(Bank myBank) {
        Bank.myBank = myBank;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public HashMap<Account, Integer> getAccountPoints() {
        return accountPoints;
    }

    public void setAccountPoints(HashMap<Account, Integer> accountPoints) {
        this.accountPoints = accountPoints;
    }

    public Account createAccount(Customer customer, int option, int id) {
        Account account = null;
        if (option == 1)
            account = new CurrentAccount(id, customer);
        else if (option == 2)
            account = new HousingAccount(id, customer);
        else
            account = new InvestmentFund(id, customer);

        customer.setAccount(account);
        accountPoints.put(account, 0);
        return account;
    }
}
