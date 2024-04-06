import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IncorrectNumberOfComponentsError, IncorrectPhoneNumberFormatError, IncorrectEmailFormatError {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length!=4){
                throw new IncorrectNumberOfComponentsError("Incorrect number of components in the provided data.");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if(!isValidPhoneNum(components[INDEX_PHONE])){
                throw new IncorrectPhoneNumberFormatError("Invalid phone number format.");
        }
        if(!isValidEmail(components[INDEX_EMAIL])){
                throw new IncorrectEmailFormatError("Invalid email format.");
        }


        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));

    }
    public boolean isValidPhoneNum(String phoneNum){
        return phoneNum.matches("^\\+?7?\\d{10}$");
    }
    public boolean isValidEmail(String email){
        return email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
            try {
                storage.remove(name);
            }
            catch (Exception e){
                System.out.println(e);
            }

    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}