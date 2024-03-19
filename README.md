This code consists of various essential parts that make up a simple shopping system:

        Product Class: This refers to a product's name, price, and description as well as its overall structure. It offers ways to access these features as well.

        ProductCatalog Class: This class uses a map data structure to maintain the product catalogue. Products and household goods can be added to the catalogue, and there are ways to list every product or retrieve particular products.

        ShoppingCart Class: This pattern, when used as a singleton, makes sure that the shopping cart exists in a single instance. It provides ways to add, remove, compute the total cost, and retrieve every item in the cart. It also keeps track of the things added to the cart and their quantities.

        User Class: Describes a user with linked shopping cart, password, and username. It offers ways to get at these characteristics.

        AuthenticationService Class is in charge of user login and registration. Users can register with a username and password, and the system verifies their information when they log in.

        Order Class: Depicts an order with a list of items and their respective quantities. It provides ways to recover the order's contents and figure out the overall cost.

        OrderManager Class: This class is implemented as a Singleton and handles user orders, much like the ShoppingCart. It offers ways to place orders and retrieve all of them, and it simulates payment processing using a dummy payment processor.

Let's analyze how the SOLID principles are applied in the provided code:

Single Responsibility Principle (SRP):

        A product having attributes is represented by a Product class, which also offers ways to access them.
        
        The ProductCatalog class offers methods for interacting with a product catalogue and maintains one.
        
        The ShoppingCart class manages the features of the shopping cart, including the ability to add and remove products and determine the final cost.
        
        A user with a login, password, and connected shopping cart is represented by the User class.
        
        User authentication, including login and registration features, is handled by the AuthenticationService class.
        
        The Order class offers methods to access order details and represents an order with a list of items.
        
        Orders are managed by the OrderManager class, which also retrieves all orders and places orders.
        
Open/Closed Principle (OCP):

    Classes are made to be easily extended without requiring changes to already written code.
    Instead of changing the current classes, new ones can be added or existing ones can be extended to allow for the addition of new goods or functionalities to the system.

Liskov Substitution Principle (LSP):

    The Liskov Substitution Principle guarantees that subclass objects can be substituted for superclass objects without causing any changes to the behaviour of the system.
    In the provided code, the classes adhere to LSP:
                                                        
                                          Subclasses (e.g., ShoppingCart, Order) can be used interchangeably with their parent classes (e.g., Product, User) without affecting the system's functionality.
Interface Segregation Principle (ISP):

    The Interface Segregation Principle advocates for creating fine-grained, client-specific interfaces rather than large, monolithic ones.
    While there are no explicit interfaces in the provided code, each class encapsulates specific functionality tailored to its purpose, adhering to ISP implicitly.
    For example, the AuthenticationService class provides separate methods for user registration and login, avoiding the need for clients to implement unnecessary functionality.
    
Dependency Inversion Principle (DIP):

    According to the Dependency Inversion Principle, abstractions should be the source of dependency for both high-level and low-level modules instead of the other way around.
    Dependencies are dealt with correctly in the code that has been provided:
    
                                        Concrete implementations are not necessary for high-level modules like OrderManager; instead, abstractions like ShoppingCart and PaymentProcessor are what they rely on.
                                        As a result, switching out implementations without affecting higher-level modules is made easier and more flexible.

The provided code incorporates several design patterns to enhance its structure, maintainability, and flexibility:

Singleton Pattern:

Implemented in the ShoppingCart and OrderManager classes.
     
      Ensures that there is only one instance of these classes throughout the application.
      Guarantees centralized access to shopping carts and order management functionality.
      
Factory Pattern:

    Utilized implicitly in the ProductFactory class 
    It could be expanded upon to create different types of products based on input parameters or configurations.
    
Builder Pattern:

    Although not explicitly implemented, the code structure follows a builder-like approach in constructing instances of products, orders, and users.
    The classes have constructors or factory methods to initialize objects with required attributes.



HOW TO USE THE PROGRAM 

Run the file ShoppingSystem.java in Eclipse IDE 

You will be at the main menu with three options to:
  1. Register
  2. Login
  3. Exit

Enter your preffered option

When you register you will be asked to provide a username and password.
After providing the following, user will be registered.

Then login by using your credentials.
An error will be generated when you enter the wrong credentials.

After logging in you will get the following options
  1. Add Product to Cart
  2. View Cart
  3. Checkout
  4. View Product Catalog
  5. Logout

Here you can view the catalog and add the products to your cart.
After adding to cart you can view or checkout your cart.

After order processing you can logout of the account to exit





