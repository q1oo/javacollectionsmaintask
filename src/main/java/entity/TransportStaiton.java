package entity;

import entity.transports.*;
import service.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static service.FileNames.*;

public class TransportStaiton {

    public TransportStaiton() {
    }

    public ArrayList<TransportDriver> getListOfDriversFromDatabase() {
        List<TransportDriver> drivers = new ArrayList<>();
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from drivers;");
            while (set.next()) {
                drivers.add(new TransportDriver(set.getLong("driver_id"), set.getString(2),
                        set.getString(3), set.getDate(4), set.getShort(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return (ArrayList) drivers;
    }

    public TransportDriver getDriverByIdFromDatabase(long driverId) {
        TransportDriver driver = null;
        if (driverId <= getMaxDriverIdFromDatabase()) {
            try {
                ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from drivers where driver_id = " + driverId + ";");
                set.next();
                driver = new TransportDriver(set.getLong("driver_id"), set.getString(2),
                        set.getString(3), set.getDate(4), set.getShort(5));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseCommunication.closeConnection();
            }
        } else {
            System.out.println("WARNING! There is no driver with such ID in current database!");
            driver = new TransportDriver();
        }
        return driver;
    }

    public ArrayList<Car> getListOfCarsFromDatabase() {
        List<Car> cars = new ArrayList<>();
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from cars;");
            while (set.next()) {
                cars.add(new Car(set.getLong("transport_id"), getDriverByIdFromDatabase(set.getLong(8)),
                        TransportType.valueOf(set.getString(9)), set.getString(2), set.getInt(3), set.getInt(4),
                        set.getDouble(5), set.getInt(6), TransportCategory.valueOf(set.getString(10)), set.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return (ArrayList) cars;
    }

    public Car getCarByIdFromDatabase(long transportId) {
        Car car = null;
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from cars where transport_id = " + transportId + ";");
            set.next();
            car = new Car(set.getLong("transport_id"), getDriverByIdFromDatabase(set.getLong(8)),
                    TransportType.valueOf(set.getString(9)), set.getString(2), set.getInt(3), set.getInt(4),
                    set.getDouble(5), set.getInt(6), TransportCategory.valueOf(set.getString(10)), set.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return car;
    }

    public ArrayList<Bus> getListOfBusesFromDatabase() {
        List<Bus> buses = new ArrayList<>();
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from buses;");
            while (set.next()) {
                buses.add(new Bus(set.getLong("transport_id"), getDriverByIdFromDatabase(set.getLong(8)),
                        TransportType.valueOf(set.getString(9)), set.getString(2), set.getInt(3), set.getInt(4),
                        set.getDouble(5), set.getInt(6), TransportCategory.valueOf(set.getString(10)), set.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return (ArrayList) buses;
    }

    public Bus getBusByIdFromDatabase(long transportId) {
        Bus bus = null;
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from cars where transport_id = " + transportId + ";");
            set.next();
            bus = new Bus(set.getLong("transport_id"), getDriverByIdFromDatabase(set.getLong(8)),
                    TransportType.valueOf(set.getString(9)), set.getString(2), set.getInt(3), set.getInt(4),
                    set.getDouble(5), set.getInt(6), TransportCategory.valueOf(set.getString(10)), set.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return bus;
    }

    public ArrayList<Truck> getListOfTrucksFromDatabase() {
        List<Truck> trucks = new ArrayList<>();
        try {
            ResultSet set = DatabaseCommunication.getDataFromDatabase("select * from trucks;");
            while (set.next()) {
                trucks.add(new Truck(set.getLong("transport_id"), getDriverByIdFromDatabase(set.getLong(7)),
                        TransportType.valueOf(set.getString(8)), set.getString(2), set.getInt(3), set.getInt(4),
                        set.getDouble(5), set.getInt(6), set.getInt(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseCommunication.closeConnection();
        }
        return (ArrayList) trucks;
    }

    public void insertNewObjectIntoDataBase(Object o) {
        List<TransportDriver> drivers = getListOfDriversFromDatabase();
        List<Car> cars = getListOfCarsFromDatabase();
        List<Bus> buses = getListOfBusesFromDatabase();
        List<Truck> trucks = getListOfTrucksFromDatabase();
        boolean isContains = false;
        if (o.getClass().equals(TransportDriver.class)) {
            TransportDriver newDriver = (TransportDriver) o;
            for (TransportDriver driver : drivers) {
                if (driver.getDriverId() == newDriver.getDriverId()) {
                    isContains = true;
                }
            }
            if (!isContains) {
                DatabaseCommunication.insertData(
                        "insert into drivers (driver_id, first_name, last_name, birthDate, driving_expirience) " +
                                "VALUES (" +
                                newDriver.getDriverId() + ", '" + newDriver.getFirstName() + "', '" + newDriver.getLastName() + "', " + newDriver.getBirthDate() + ", " +
                                newDriver.getDrivingExperience() + ");");
            } else {
                System.out.println("WARNING! This driver is already exist in database!");
            }
        } else if (o.getClass().equals(Car.class)) {
            Car newCar = (Car) o;
            if (newCar.getTransportDriver().getDriverId() <= getMaxDriverIdFromDatabase() && newCar.getTransportDriver().getDriverId() != 0) {
                for (Car car : cars) {
                    if (car.getTransportId() == newCar.getTransportId() || car.getTransportDriver().getDriverId() == newCar.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Bus bus : buses) {
                    if (bus.getTransportId() == newCar.getTransportId() || bus.getTransportDriver().getDriverId() == newCar.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Truck truck : trucks) {
                    if (truck.getTransportId() == newCar.getTransportId() || truck.getTransportDriver().getDriverId() == newCar.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    DatabaseCommunication.insertData(
                            "insert into cars (transport_id, model, max_speed, fuel_capacity, fuel_consumption, " +
                                    "transport_current_price, passenger_capacity, driver_id, transport_type, transport_category) VALUES (" +
                                    newCar.getTransportId() + ", '" + newCar.getModel() + "', " + newCar.getMaxSpeed() + ", " + newCar.getFuelCapacity() + ", " +
                                    newCar.getFuelConsumptionPerOneHundredKm() + ", " + newCar.getTransportCurrentPrice() + ", " + newCar.getPassengerCapacity() + ", " +
                                    newCar.getTransportDriver().getDriverId() + ", '" + newCar.getTransportType() + "', '" + newCar.getTransportCategory() + "');");
                } else {
                    System.out.println("WARNING! The transport with same ID or transport with this driver is already exist in database!");
                }
            } else {
                System.out.println("WARNING! There is no driver with such ID, add new driver in database before do this operation!");
            }
        } else if (o.getClass().equals(Bus.class)) {
            Bus newBus = (Bus) o;
            if (newBus.getTransportDriver().getDriverId() <= getMaxDriverIdFromDatabase() && newBus.getTransportDriver().getDriverId() != 0) {
                for (Car car : cars) {
                    if (car.getTransportId() == newBus.getTransportId() || car.getTransportDriver().getDriverId() == newBus.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Bus bus : buses) {
                    if (bus.getTransportId() == newBus.getTransportId() || bus.getTransportDriver().getDriverId() == newBus.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Truck truck : trucks) {
                    if (truck.getTransportId() == newBus.getTransportId() || truck.getTransportDriver().getDriverId() == newBus.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    DatabaseCommunication.insertData(
                            "insert into buses (transport_id, model, max_speed, fuel_capacity, fuel_consumption, " +
                                    "transport_current_price, passenger_capacity, driver_id, transport_type, transport_category) VALUES (" +
                                    newBus.getTransportId() + ", '" + newBus.getModel() + "', " + newBus.getMaxSpeed() + ", " + newBus.getFuelCapacity() + ", " +
                                    newBus.getFuelConsumptionPerOneHundredKm() + ", " + newBus.getTransportCurrentPrice() + ", " + newBus.getPassengerCapacity() + ", " +
                                    newBus.getTransportDriver().getDriverId() + ", '" + newBus.getTransportType() + "', '" + newBus.getTransportCategory() + "');");
                } else {
                    System.out.println("WARNING! The transport with same ID or transport with this driver is already exist in database!");
                }
            } else {
                System.out.println("WARNING! There is no driver with such ID, add new driver in database before do this operation!");
            }
        } else if (o.getClass().equals(Truck.class)) {
            Truck newTruck = (Truck) o;
            if (newTruck.getTransportDriver().getDriverId() <= getMaxDriverIdFromDatabase() && newTruck.getTransportDriver().getDriverId() != 0) {
                for (Car car : cars) {
                    if (car.getTransportId() == newTruck.getTransportId() || car.getTransportDriver().getDriverId() == newTruck.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Bus bus : buses) {
                    if (bus.getTransportId() == newTruck.getTransportId() || bus.getTransportDriver().getDriverId() == newTruck.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                for (Truck truck : trucks) {
                    if (truck.getTransportId() == newTruck.getTransportId() || truck.getTransportDriver().getDriverId() == newTruck.getTransportDriver().getDriverId()) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    DatabaseCommunication.insertData(
                            "insert into trucks (transport_id, model, max_speed, fuel_capacity, fuel_consumption, " +
                                    "transport_current_price, driver_id, transport_type, load_capacity) VALUES (" +
                                    newTruck.getTransportId() + ", '" + newTruck.getModel() + "', " + newTruck.getMaxSpeed() + ", " + newTruck.getFuelCapacity() + ", " +
                                    newTruck.getFuelConsumptionPerOneHundredKm() + ", " + newTruck.getTransportCurrentPrice() + ", " + newTruck.getTransportDriver().getDriverId() +
                                    ", '" + newTruck.getTransportType() + "', " + newTruck.getLoadCapacity() + ");");
                } else {
                    System.out.println("WARNING! The transport with same ID or transport with this driver is already exist in database!");
                }
            } else {
                System.out.println("WARNING! There is no driver with such ID, add new driver in database before do this operation!");
            }
        }
    }

    public long getMaxDriverIdFromDatabase() {
        List<TransportDriver> drivers = getListOfDriversFromDatabase();
        long maxId = 0L;
        for (TransportDriver driver : drivers) {
            if (driver.getDriverId() > maxId) {
                maxId = driver.getDriverId();
            }
        }
        return maxId;
    }

    public int getSumOfTransportPriceFromDatabase() {
        int sum = 0;
        List<Car> cars = getListOfCarsFromDatabase();
        for (Car car : cars) {
            sum += car.getTransportCurrentPrice();
        }
        List<Bus> buses = getListOfBusesFromDatabase();
        for (Bus bus : buses) {
            sum += bus.getTransportCurrentPrice();
        }
        List<Truck> trucks = getListOfTrucksFromDatabase();
        for (Truck truck : trucks) {
            sum += truck.getTransportCurrentPrice();
        }
        return sum;
    }

    public ArrayList<AbstractTransport> getListOfAllTransportsFromDatabase() {
        List<AbstractTransport> allTransports = new ArrayList<>();
        allTransports.addAll(getListOfCarsFromDatabase());
        allTransports.addAll(getListOfBusesFromDatabase());
        allTransports.addAll(getListOfTrucksFromDatabase());
        return (ArrayList) allTransports;
    }

    public ArrayList<AbstractTransport> sortListOfTransportsByFuelConsumptionFromDatabase(ArrayList transports) {
        transports.sort(new TransportsFuelConsumptionComporator());
        return transports;
    }

    public ArrayList<AbstractTransport> getListOfTransportsByMaxSpeedRangeFromDatabase(int leftOfRange, int rigthOfRange) {
        List<AbstractTransport> transports = getListOfAllTransportsFromDatabase();
        int temp = 0;
        for (int i = 0; i < transports.size(); i++) {
             if (transports.get(i).getMaxSpeed() < leftOfRange || transports.get(i).getMaxSpeed() > rigthOfRange) {
                 transports.remove(i);
                 i--;
             }
        }
        return (ArrayList) transports;
    }

    public ArrayList<TransportDriver> getListOfDriversFromFile() {
        List <TransportDriver> drivers = new ArrayList<>();
        String stringDrivers = FileManager.readFromFile(DRIVERS);
        String[] arrOfData = stringDrivers.split(", ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < arrOfData.length; i+=5) {
            TransportDriver driver = new TransportDriver(Long.parseLong(arrOfData[i]), arrOfData[i + 1], arrOfData[i + 2], Date.valueOf(arrOfData[i + 3]), Short.parseShort(arrOfData[i + 4]));
            drivers.add(driver);
        }
        return (ArrayList) drivers;
    }

    public ArrayList<AbstractTransport> getListOfTransportsFromFile(FileNames fileName) {
        List <AbstractTransport> transports = new ArrayList<>();
        String stringTransports = FileManager.readFromFile(fileName);
        String[] arrOfData = stringTransports.split(", ");
        switch (fileName) {
            case CARS:
                for (int i = 0; i < arrOfData.length; i+=10) {
                    Car car = new Car(Long.parseLong(arrOfData[i]),
                                        getDriverByIdFromFile(Long.parseLong(arrOfData[i + 1])),
                                        TransportType.valueOf(arrOfData[i + 2]),
                                        arrOfData[i + 3],
                                        Integer.parseInt(arrOfData[i + 4]),
                                        Integer.parseInt(arrOfData[i + 5]),
                                        Double.parseDouble(arrOfData[i + 6]),
                                        Integer.parseInt(arrOfData[i + 7]),
                                        TransportCategory.valueOf(arrOfData[i + 8]),
                                        Integer.parseInt(arrOfData[i + 9]));
                    transports.add(car);
                }
                break;
            case BUSES:
                for (int i = 0; i < arrOfData.length; i+=10) {
                    Bus bus = new Bus(Long.parseLong(arrOfData[i]),
                            getDriverByIdFromFile(Long.parseLong(arrOfData[i + 1])),
                            TransportType.valueOf(arrOfData[i + 2]),
                            arrOfData[i + 3],
                            Integer.parseInt(arrOfData[i + 4]),
                            Integer.parseInt(arrOfData[i + 5]),
                            Double.parseDouble(arrOfData[i + 6]),
                            Integer.parseInt(arrOfData[i + 7]),
                            TransportCategory.valueOf(arrOfData[i + 8]),
                            Integer.parseInt(arrOfData[i + 9]));
                    transports.add(bus);
                }
                break;
            case TRUCKS:
                for (int i = 0; i < arrOfData.length; i+=9) {
                    Truck truck = new Truck(Long.parseLong(arrOfData[i]),
                            getDriverByIdFromFile(Long.parseLong(arrOfData[i + 1])),
                            TransportType.valueOf(arrOfData[i + 2]),
                            arrOfData[i + 3],
                            Integer.parseInt(arrOfData[i + 4]),
                            Integer.parseInt(arrOfData[i + 5]),
                            Double.parseDouble(arrOfData[i + 6]),
                            Integer.parseInt(arrOfData[i + 7]),
                            Integer.parseInt(arrOfData[i + 8]));
                    transports.add(truck);
                }
                break;
            default:
                transports.addAll(getListOfTransportsFromFile(CARS));
                transports.addAll(getListOfTransportsFromFile(BUSES));
                transports.addAll(getListOfTransportsFromFile(TRUCKS));
        }
        return (ArrayList) transports;
    }

    public TransportDriver getDriverByIdFromFile(long driverId) {
        TransportDriver driver = null;
        if (driverId <= getMaxDriverIdFromFile()) {
            List<TransportDriver> drivers = getListOfDriversFromFile();
            for (TransportDriver transportDriver : drivers) {
                if (transportDriver.getDriverId() == driverId) {
                    driver = transportDriver;
                }
            }
        }
        return driver;
    }

    public long getMaxDriverIdFromFile() {
        List<TransportDriver> drivers = getListOfDriversFromFile();
        long maxId = 0L;
        for (TransportDriver driver : drivers) {
            if (driver.getDriverId() > maxId) {
                maxId = driver.getDriverId();
            }
        }
        return maxId;
    }
}
