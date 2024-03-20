package shopping;

import java.util.*;

class Product 
{
    private String name;
    private double price;
    private String description;

    public Product(String name, double price, String description) 
    {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() 
    {
        return name;
    }

    public double getPrice() 
    {
        return price;
    }

    public String getDescription() 
    {
        return description;
    }
}

class ProductCatalog 
{
    private Map<String, Product> products;

    public ProductCatalog() 
    {
        this.products = new HashMap<>();
        addProduct("Laptop", 999.99, "High-performance gaming laptop with RTX4090.");
        addProduct("Smartphone", 599.99, "Apple Iphone 15 256GB.");
        addHouseholdItem("Toaster", 25.99, "A toaster for toasting bread slices.");
        addHouseholdItem("Coffee Maker", 49.99, "An electric coffee maker for brewing coffee.");
        addHouseholdItem("Vacuum Cleaner", 99.99, "DYSON vacuum cleaner for cleaning floors.");
    }

    public void addProduct(String name, double price, String description) 
    {
        Product product = new Product(name, price, description);
        products.put(name, product);
    }

    public void addHouseholdItem(String name, double price, String description) 
    {
        addProduct(name, price, description);
    }

    public Product getProduct(String name) 
    {
        return products.get(name);
    }

    public List<Product> getAllProducts() 
    {
        return new ArrayList<>(products.values());
    }
}

class ShoppingCart 
{
    private Map<Product, Integer> items;

    private ShoppingCart() 
    {
        this.items = new HashMap<>();
    }

    public static ShoppingCart getInstance() 
    {
        return ShoppingCartHolder.INSTANCE;
    }

    private static class ShoppingCartHolder 
    {
        private static final ShoppingCart INSTANCE = new ShoppingCart();
    }

    public void addItem(Product product, int quantity) 
    {
        items.put(product, quantity);
    }

    public void removeItem(Product product) 
    {
        items.remove(product);
    }

    public double getTotalPrice() 
    {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) 
        {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public Map<Product, Integer> getItems() 
    {
        return items;
    }
}

class ProductFactory 
{
    public static Product createProduct(String name, double price, String description) 
    {
        return new Product(name, price, description);
    }
}

interface PaymentProcessor 
{
    boolean processPayment(double amount);
}

class MockPaymentProcessor implements PaymentProcessor 
{
    @Override
    public boolean processPayment(double amount) 
    {
        System.out.println("Payment Gateway: Processing payment of $" + amount);
        return true;
    }
}

class User 
{
    private String username;
    private String password;
    private ShoppingCart cart;

    public User(String username, String password) 
    {
        this.username = username;
        this.password = password;
        this.cart = ShoppingCart.getInstance();
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public ShoppingCart getCart() 
    {
        return cart;
    }
}

class AuthenticationService 
{
    private static Map<String, User> users = new HashMap<>();

    public static void registerUser(String username, String password) 
    {
        if (!users.containsKey(username)) 
        {
            User user = new User(username, password);
            users.put(username, user);
            System.out.println("User registered successfully!");
        } 
        else 
        {
            System.out.println("Username already exists. Please choose another one.");
        }
    }

    public static User loginUser(String username, String password) 
    {
        if (users.containsKey(username)) 
        {
            User user = users.get(username);
            if (user.getPassword().equals(password)) 
            {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
        return null;
    }
}

class Order 
{
    private Map<Product, Integer> items;

    public Order(Map<Product, Integer> items) 
    {
        this.items = items;
    }

    public Map<Product, Integer> getItems() 
    {
        return items;
    }

    public double getTotalPrice() 
    {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) 
        {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}

class OrderManager 
{
    private static OrderManager instance;
    private List<Order> orders;
    private PaymentProcessor paymentProcessor;

    private OrderManager() 
    {
        orders = new ArrayList<>();
        paymentProcessor = new MockPaymentProcessor();
    }

    public static OrderManager getInstance() 
    {
        if (instance == null) 
        {
            instance = new OrderManager();
        }
        return instance;
    }

    public void placeOrder(ShoppingCart cart) 
    {
        Map<Product, Integer> items = cart.getItems();
        Order order = new Order(items);
        orders.add(order);
        boolean paymentStatus = paymentProcessor.processPayment(order.getTotalPrice());
        if (paymentStatus) 
        {
            System.out.println("Order placed successfully!");
            cart.getItems().clear(); 
        } 
        else 
        {
            System.out.println("Payment processing failed. Order not placed.");
        }
    }

    public List<Order> getAllOrders() 
    {
        return orders;
    }
}

public class ShoppingSystem 
{
    private static Scanner scanner = new Scanner(System.in);
    private static ProductCatalog catalog = new ProductCatalog();

    public static void main(String[] args) 
    {
        System.out.println("Welcome to Aditya's Supermarket!");

        while (true) 
        {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) 
            {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting..");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() 
    {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        AuthenticationService.registerUser(username, password);
    }

    private static void loginUser() 
    {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User loggedInUser = AuthenticationService.loginUser(username, password);
        if (loggedInUser != null) 
        {
            System.out.println("Login successful!");
            shoppingLoop(loggedInUser);
        }
    }

    private static void viewProductCatalog() 
    {
        List<Product> products = catalog.getAllProducts();
        System.out.println("\nProduct Catalog:");
        for (Product product : products) 
        {
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Description: " + product.getDescription());
            System.out.println();
        }
    }

    private static void shoppingLoop(User user) 
    {
        ShoppingCart cart = user.getCart();
        while (true) 
        {
            System.out.println("\n1. Add Product to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. View Product Catalog");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) 
            {
                case 1:
                    addProductToCart(user);
                    break;
                case 2:
                    viewCart(cart);
                    break;
                case 3:
                    checkout(cart);
                    break;
                case 4:
                    viewProductCatalog();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProductToCart(User user) 
    {
        System.out.print("Enter the name of the product to add to cart: ");
        String productName = scanner.nextLine();
        Product product = catalog.getProduct(productName);
        if (product != null) 
        {
            ShoppingCart cart = user.getCart();
            cart.addItem(product, 1);
            System.out.println("Product added to cart.");
        } 
        else 
        {
            System.out.println("Product not found in catalog.");
        }
    }

    private static void viewCart(ShoppingCart cart) 
    {
        Map<Product, Integer> items = cart.getItems();
        if (items.isEmpty()) 
        {
            System.out.println("Your cart is empty.");
        } 
        else 
        {
            System.out.println("\nShopping Cart:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) 
            {
                System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue() + " - $" + entry.getKey().getPrice() * entry.getValue());
            }
            System.out.println("Total Price: $" + cart.getTotalPrice());
        }
    }

    private static void checkout(ShoppingCart cart) 
    {
        if (cart.getItems().isEmpty()) 
        {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.placeOrder(cart);
    }
}


