import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class login {
    private static Map<String, String> users = new HashMap<>();
    private static String currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();
        
        while (true) {
            if (currentUser == null) {
                System.out.println("\n=== Welcome to FellInLove ===");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        login(scanner);
                        break;
                    case "2":
                        register(scanner);
                        break;
                    case "3":
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("\n=== Main Menu ===");
                System.out.println("Welcome, " + currentUser + "!");
                System.out.println("1. View Profile");
                System.out.println("2. Browse Items");
                System.out.println("3. View Payments");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        viewProfile();
                        break;
                    case "2":
                        browseItems();
                        break;
                    case "3":
                        viewPayments();
                        break;
                    case "4":
                        logout();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(hashPassword(password))) {
            currentUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
            return;
        }

        users.put(username, hashPassword(password));
        System.out.println("Registration successful! You can now login.");
    }

    private static void logout() {
        currentUser = null;
        System.out.println("You have been logged out.");
    }

    private static void viewProfile() {
        System.out.println("\n--- Your Profile ---");
        System.out.println("Username: " + currentUser);
        System.out.println("Email: " + currentUser + "@fellinlove.com");
    }

    private static void browseItems() {
        System.out.println("\n--- Items Available ---");
        System.out.println("1. Diamond Ring - $999");
        System.out.println("2. Gold Necklace - $499");
        System.out.println("3. Silver Bracelet - $199");
    }

    private static void viewPayments() {
        System.out.println("\n--- Payment History ---");
        System.out.println("No transactions yet.");
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void initializeUsers() {
        users.put("demo", hashPassword("123456"));
        users.put("john", hashPassword("password"));
    }
}
