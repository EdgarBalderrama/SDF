import java.util.*;

public class Main {

    public static void main(String[] args){
        Customer customer = new Customer(8738124, "Ricardo", "Pedriel", "C. Santos Taborga", "4200556", "+59176884566");
        Customer customer1 = new Customer(9958463, "Cristian", "Chavez", "Av. La Paz", "4669852", "+59176996541");
        Customer customer2 = new Customer(24662598, "Alejadro", "Melean", "Av. Ortiz", "4695687", "+59176889325");

        Account currentAccount = new CurrentAccount(8738124, customer);
        Account housingAccount = new HousingAccount(9958463, customer1);
        Account investmentFund = new InvestmentFund(24662598, customer2);

        customer.setAccount(currentAccount);
        customer1.setAccount(housingAccount);
        customer2.setAccount(investmentFund);

        HashMap accounts = new HashMap();
        accounts.put(currentAccount, 0);
        accounts.put(housingAccount, 0);
        accounts.put(investmentFund, 0);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        customers.add(customer2);

        Bank bank = Bank.getMyBank();
        bank.setAccountPoints(accounts);
        bank.setCustomers(customers);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;
        String textOption;

        while(!exit){

            System.out.println("1. Bank options");
            System.out.println("2. Customer information");
            System.out.println("3. Account options");
            System.out.println("4. Exit");

            try {
                System.out.println("Choose one option with its number");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("1. Bank information");
                        System.out.println("2. New customer");
                        option = scanner.nextInt();
                        switch (option){
                            case 1:
                                System.out.println(bank.getBankInformation());
                                break;
                            case 2:
                                System.out.println("1. Customer's name: ");
                                String name = scanner.next();
                                System.out.println("2. Customer's last name: ");
                                String lastName = scanner.next();
                                System.out.println("3. Customer's address: ");
                                String address = scanner.next();
                                System.out.println("4. Customer's phone: ");
                                String phone = scanner.next();
                                System.out.println("5. Customer's cell phone: ");
                                String cellPhone = scanner.next();
                                System.out.println("5. Customer's CI: ");
                                int id = scanner.nextInt();

                                Customer newCustomer = bank.createCustomer(id, name, lastName, address, phone, cellPhone);
                                if (newCustomer != null)
                                    System.out.println("Customer " + lastName + " " + name + " created successfully.");
                                else {
                                    System.out.println("There is already a customer with that CI");
                                    break;
                                }
                                System.out.println("Choose the type of account for the new customer: ");
                                System.out.println("1. Current account ");
                                System.out.println("2. Housing account ");
                                System.out.println("3. Investment fund ");
                                option = scanner.nextInt();

                                bank.createAccount(newCustomer, option, id);
                                System.out.println("Account created successfully.");

                                break;

                            default:
                                System.out.println("Only numbers between 1 and 2");
                        }

                        break;
                    case 2:
                        System.out.println("Enter the user ID");
                        option = scanner.nextInt();
                        System.out.println(bank.getCustomerInformation(option));
                        break;
                    case 3:
                        System.out.println("Enter the account number");
                        int id = scanner.nextInt();
                        if (bank.validAccount(id))
                        {
                            Account account = bank.getAccount(id);
                            System.out.println("1. Enter money");
                            System.out.println("2. Withdraw money");
                            System.out.println("3. Check balance");
                            System.out.println("4. Change client");
                            System.out.println("5. Get monthly report");
                            option = scanner.nextInt();
                            switch (option){
                                case 1:
                                    System.out.println("Enter the amount: ");
                                    option = scanner.nextInt();
                                    System.out.println(account.enterMoney(option));
                                    break;
                                case 2:
                                    System.out.println("Enter the amount: ");
                                    option = scanner.nextInt();
                                    System.out.println(account.withdrawMoney(option));
                                    break;
                                case 3:
                                    System.out.println(account.checkBalance());
                                    break;
                                case 4:
                                    System.out.println("1. Create new customer");
                                    System.out.println("2. Select one customer");
                                    option = scanner.nextInt();
                                    switch (option){
                                        case 1:
                                            Customer userCustomer = new Customer();
                                            System.out.println("1. Customer's name: ");
                                            textOption = scanner.next();
                                            userCustomer.setName(textOption);
                                            System.out.println("2. Customer's last name: ");
                                            textOption = scanner.next();
                                            userCustomer.setLastName(textOption);
                                            System.out.println("3. Customer's address: ");
                                            textOption = scanner.next();
                                            userCustomer.setAddress(textOption);
                                            System.out.println("4. Customer's phone: ");
                                            textOption = scanner.next();
                                            userCustomer.setPhone(textOption);
                                            System.out.println("5. Customer's cell phone: ");
                                            textOption = scanner.next();
                                            userCustomer.setCellPhone(textOption);
                                            System.out.println("6. Customer's CI: ");
                                            option = scanner.nextInt();
                                            userCustomer.setIdentificationNumber(option);
                                            userCustomer.setAccount(account);

                                            System.out.println(bank.changeClient(userCustomer, id));
                                            break;

                                        case 2:
                                            System.out.println("Enter the client ID: ");
                                            option = scanner.nextInt();
                                            System.out.println(bank.replaceClient(bank.getCustomer(option), id, option));
                                            break;

                                        default:
                                            System.out.println("Only numbers between 1 and 2");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Enter the month: ");
                                    option = scanner.nextInt();
                                    System.out.println(account.getMonthlyReport(option));
                                    break;
                                default:
                                    System.out.println("Only numbers between 1 and 5");
                            }
                        }
                        else
                            System.out.println("Account not found");
                            break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Only numbers between 1 and 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("You have to enter only numbers");
                scanner.next();
            }
        }
    }


}
