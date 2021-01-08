public class Customer {
    private int identificationNumber;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String cellPhone;

    private Account account;

    public Customer(int identificationNumber, String name, String lastName, String address, String phone, String cellPhone) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.cellPhone = cellPhone;
    }

    public Customer() {
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
