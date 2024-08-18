package CarRentalSystem;

import java.util.*;

class Car {
    private String carId;
    private String carBrand;
    private String carModel;
    private double carPrice;
    private boolean isAvailable;

    public Car(String carId, String carBrand, String carModel, double carPrice) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public double calculateRent(int days) {
        return carPrice*days;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private long customerAadhar;
    private long licenseNumber;

    public Customer(String customerId, String customerName, String customerAddress, String customerPhone, long customerAadhar, long licenseNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerAadhar = customerAadhar;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public long getCustomerAadhar() {
        return customerAadhar;
    }

    public long getLicenseNumber() {
        return licenseNumber;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRental {

    private java.util.ArrayList<java.lang.Object> cars;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public CarRental() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if(car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        }
        else {
            System.out.println("Car is not available for rent");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentaltoRemove = null;
        for(Rental rental : rentals) {
            if(rental.getCar().getCarId().equals(car.getCarId())) {
                rentaltoRemove = rental;
                break;
            }
        }

        if(rentaltoRemove != null) {
            rentals.remove(rentaltoRemove);
            System.out.println("Car returned successfully");
        }
        else {
            System.out.println("Car was not rented");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-------Car Rental System-------");
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Rent Car");
            System.out.println("4. Return Car");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Enter car id: ");
                    String carId = sc.nextLine();
                    System.out.println("Enter car brand: ");
                    String carBrand = sc.nextLine();
                    System.out.println("Enter car model: ");
                    String carModel = sc.nextLine();
                    System.out.println("Enter car price: ");
                    double carPrice = sc.nextDouble();
                    sc.nextLine();
                    addCar(new Car(carId, carBrand, carModel, carPrice));
                    break;
                case 2:
                    System.out.println("Enter customer id: ");
                    String customerId = sc.nextLine();
                    System.out.println("Enter customer name: ");
                    String customerName = sc.nextLine();
                    System.out.println("Enter customer address: ");
                    String customerAddress = sc.nextLine();
                    System.out.println("Enter customer phone: ");
                    String customerPhone = sc.nextLine();
                    System.out.println("Enter customer aadhar: ");
                    long customerAadhar = sc.nextLong();
                    System.out.println("Enter license number: ");
                    String licenseNumber = sc.nextLine();
                    sc.nextLine();
                    addCustomer(new Customer(customerId, customerName, customerAddress, customerPhone, customerAadhar, licenseNumber));
                    break;
                case 3:
                    System.out.println("Enter car id: ");
                    String rentCarId = sc.nextLine();
                    System.out.println("Enter customer id: ");
                    String rentCustomerId = sc.nextLine();
                    System.out.println("Enter number of days: ");
                    int days = sc.nextInt();
                    sc.nextLine();
                    Car rentCar = null;
                    Customer rentCustomer = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(rentCarId)) {
                            rentCar = car;
                            break;
                        }
                    }
                    for (Customer customer : customers) {
                        if (customer.getCustomerId().equals(rentCustomerId)) {
                            rentCustomer = customer;
                            break;
                        }
                    }
                    if (rentCar != null && rentCustomer != null) {
                        rentCar(rentCar, rentCustomer, days);
                    } else {
                        System.out.println("Car or Customer not found");
                    }
                    break;
                case 4:
                    System.out.println("Enter car id: ");
                    String returnCarId = sc.nextLine();
                    Car returnCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(returnCarId)) {
                            returnCar = car;
                            break;
                        }
                    }
                    if (returnCar != null) {
                        returnCar(returnCar);
                    } else {
                        System.out.println("Car not found");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(true);
    }
}

public class Main {
    public static void main(String[] args) {
        CarRental carRentalSystem = new CarRental();
        Car car1 = new Car("C001", "Toyota", "Fortuner", 5000);
        Car car2 = new Car("C002", "Hyundai", "Creta", 4000);
        Car car3 = new Car("C003", "MG", "Hector", 4000);
        Car car4 = new Car("C004", "Kia", "Seltos", 3500);
        Car car5 = new Car("C005", "Suzuki", "Swift", 3000);
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);
        carRentalSystem.addCar(car4);
        carRentalSystem.addCar(car5);
        carRentalSystem.menu();
    }
}