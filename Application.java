import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * The console (text based) user interface for the Parking Spot System.
 *  all user input and output, and delegates all business logic
 * (adding/finding/deleting slots and cars) to the CarPark class.
 *
 * @author Yugma ManojBhai Mangukiya
 * @id 106227407
 * @version 1.0
 * @date 11/09/2026
 */
public class Application
{
    private Scanner scanner;
    private CarPark carPark;

    /**
     * Creates the application, ready to be started with start().
     */
    public Application()
    {
        scanner = new Scanner(System.in);
        carPark = new CarPark();
    }

    /**
     * Program entry point.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args)
    {
        Application app = new Application();
        app.start();
    }

    /**
     * Sets up the car park and then repeatedly displays the menu and
     * processes the user's chosen option until they choose to exit.
     */
    public void start()
    {
        createCarPark();

        int choice;

        do
        {
            displayMenu();
            choice = readInt();

            switch(choice)
            {
                case 1:
                    addSlot();
                    break;

                case 2:
                    deleteSlot();
                    break;

                case 3:
                    listSlots();
                    break;

                case 4:
                    deleteAllUnoccupiedSlots();
                    break;

                case 5:
                    parkCar();
                    break;

                case 6:
                    findCar();
                    break;

                case 7:
                    removeCar();
                    break;

                case 8:
                    System.out.println("Program end!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while(choice != 8);
    }

    /**
     * Asks the user how many staff slots and visitor slots to create,
     * then creates the car park with those slots. Staff slots are given
     * IDs A001, A002, ...; visitor slots are given IDs V001, V002, ...
     */
    private void createCarPark()
    {
        System.out.println("Start to establish a car parking with slots for staff and visitors");

        System.out.print("Please enter the number of slots for staff: ");
        int staff = readNonNegativeInt();

        System.out.print("Please enter the number of slots for visitors: ");
        int visitor = readNonNegativeInt();

        for(int i = 1; i <= staff; i++)
        {
            String id = String.format("A%03d", i);
            carPark.addSlot(new ParkingSlot(id, true));
        }

        for(int i = 1; i <= visitor; i++)
        {
            String id = String.format("V%03d", i);
            carPark.addSlot(new ParkingSlot(id, false));
        }

        System.out.println("The " + staff + " slots for staff are created successfully");
        System.out.println("The " + visitor + " slots for visitors are created successfully");
    }

    /**
     * Prints the main menu options to the console.
     */
    private void displayMenu()
    {
        System.out.println("  ");
        System.out.println("1. Add a car slot");
        System.out.println("2. Delete a car slot");
        System.out.println("3. List all car slots");
        System.out.println("4. Delete all unoccupied slots");
        System.out.println("5. Park a car");
        System.out.println("6. Find a car");
        System.out.println("7. Remove a car");
        System.out.println("8. Exit");
        System.out.println("  ");
        System.out.print("Please select an option (1-8): ");
    }

    /**
     * menu option 1: prompts for a new slot's ID and type,
     * validates the ID format and uniqueness, and adds it to the car park.
     */
    private void addSlot()
    {
        System.out.print("Please enter the slot ID (e.g. A001): ");
        String id = scanner.nextLine().trim();

        if(!id.matches("[A-Z][0-9]{3}"))
        {
            System.out.println("Invalid slot ID. Format must be one uppercase letter followed by 3 digits.");
            System.out.println("e.g. A001");
            return;
        }

        if(carPark.findSlot(id) != null)
        {
            System.out.println("The slot already exists.");
            return;
        }

        boolean staff = readYesNo("Is the slot for a staff member (yes/no)? ");

        if(carPark.addSlot(new ParkingSlot(id, staff)))
        {
            System.out.println("The slot is added successfully");
        }
        else
        {
            System.out.println("Could not add slot.");
        }
    }

    /**
     * menu option 2: prompts for a slot ID and deletes it,
     * provided it exists and is not currently occupied.
     */
    private void deleteSlot()
    {
        System.out.print("Please enter the slot ID (e.g. A001): ");
        String id = scanner.nextLine().trim();

        ParkingSlot slot = carPark.findSlot(id);

        if(slot == null)
        {
            System.out.println("Cannot delete slot. Slot not found.");
        }
        else if(slot.isOccupied())
        {
            System.out.println("Cannot delete slot. The slot is currently occupied.");
        }
        else if(carPark.deleteSlot(id))
        {
            System.out.println("The slot is deleted");
        }
        else
        {
            System.out.println("Cannot delete slot.");
        }
    }

    /**
     * menu option 3: lists every slot, its type, whether it is
     * occupied, and (if occupied) the parked car's details, how long it
     * has been parked, and the fee owed so far.
     */
    private void listSlots()
    {
        if(carPark.getSlots().isEmpty())
        {
            System.out.println("There are no slots in the car park.");
            return;
        }

        for(ParkingSlot slot : carPark.getSlots())
        {
            System.out.print("SlotID is: " + slot.getSlotId());

            if(slot.isStaffSlot())
            {
                System.out.print(", is for staff");
            }
            else
            {
                System.out.print(", is for visitor");
            }

            if(slot.isOccupied())
            {
                Car car = slot.getParkedCar();

                System.out.println(", and is occupied by "
                    + car.getRegistrationNumber()
                    + " (" + car.getMake() + ") owned by "
                    + car.getOwner());
                System.out.println("    Parked since: " + car.getFormattedParkedTime()
                    + " | Duration: " + car.getParkingDuration()
                    + " | Fee: $" + String.format("%.2f", car.getParkingFee()));
            }
            else
            {
                System.out.println(", and is not occupied");
            }
        }
    }

    /**
     * menu option 4: deletes every slot that is not currently
     * occupied by a car.
     */
    private void deleteAllUnoccupiedSlots()
    {
        carPark.deleteAllUnoccupiedSlots();
        System.out.println("All unoccupied slots removed.");
    }

    /**
     * menu option 5: prompts for a slot ID and car details, then
     * parks the car in that slot after validating the slot exists, is free,
     * the registration number is valid, the car is not already parked
     * elsewhere, and the car's staff/visitor status matches the slot type.
     */
    private void parkCar()
    {
        System.out.print("Slot ID: ");
        String slotId = scanner.nextLine().trim();

        ParkingSlot slot = carPark.findSlot(slotId);

        if(slot == null)
        {
            System.out.println("Slot not found.");
            return;
        }

        if(slot.isOccupied())
        {
            System.out.println("Slot occupied.");
            return;
        }

        System.out.print("Registration Number: ");
        String reg = scanner.nextLine().trim();

        if(!reg.matches("[A-Z][0-9]{5}"))
        {
            System.out.println("Invalid registration number.");
            System.out.println("Format must be one uppercase letter followed by 5 digits.");
            System.out.println("Example: T12345");
            return;
        }

        if(carPark.findCar(reg) != null)
        {
            System.out.println("Car already parked.");
            return;
        }

        System.out.print("Owner: ");
        String owner = scanner.nextLine().trim();

        System.out.print("Make: ");
        String make = scanner.nextLine().trim();

        System.out.print("Model: ");
        String model = scanner.nextLine().trim();

        boolean staff = readYesNo("Is the owner a staff member (yes/no)? ");

        if(staff != slot.isStaffSlot())
        {
            System.out.println("Wrong slot type. A "
                + (staff ? "staff" : "visitor") + " car cannot park in a "
                + (slot.isStaffSlot() ? "staff" : "visitor") + " slot.");
            return;
        }

        Car car = new Car(reg, owner, make, model, staff);
        car.setParkedTime(LocalDateTime.now());

        if(slot.parkCar(car))
        {
            System.out.println("Car parked.");
            System.out.println("Parking time: " + car.getFormattedParkedTime());
        }
        else
        {
            System.out.println("Could not park car.");
        }
    }

    /**
     * menu option 6: prompts for a registration number and, if
     * found, displays the slot it is in, the car's details, how long it
     * has been parked, and the fee owed so far.
     */
    private void findCar()
    {
        System.out.print("Registration Number: ");
        String reg = scanner.nextLine().trim();

        ParkingSlot slot = carPark.findCar(reg);

        if(slot == null)
        {
            System.out.println("Car not found.");
            return;
        }

        Car car = slot.getParkedCar();

        System.out.println("Slot: " + slot.getSlotId());
        System.out.println("Registration Number: " + car.getRegistrationNumber());
        System.out.println("Owner: " + car.getOwner());
        System.out.println("Make: " + car.getMake());
        System.out.println("Model: " + car.getModel());
        System.out.println("Parked since: " + car.getFormattedParkedTime());
        System.out.println("Duration: " + car.getParkingDuration());
        System.out.println("Fee: $" + String.format("%.2f", car.getParkingFee()));
    }

    /**
     * menu option 7: prompts for a registration number and removes
     * that car from its slot, if found.
     */
    private void removeCar()
    {
        System.out.print("Registration Number: ");
        String reg = scanner.nextLine().trim();

        ParkingSlot slot = carPark.findCar(reg);

        if(slot == null)
        {
            System.out.println("Car not found.");
            return;
        }

        Car car = slot.getParkedCar();

        System.out.println("Car found in slot: " + slot.getSlotId());
        System.out.println("Parking duration: " + car.getParkingDuration());
        System.out.println("Parking fee: $" 
                + String.format("%.2f", car.getParkingFee()));

        slot.removeCar();
    
        System.out.println("Car removed successfully.");
    }

    /**
     * Reads a line of input and attempts to parse it as an integer.
     * Never throws; invalid input is reported back as -1 so the caller
     * can safely treat it as an invalid menu choice.
     *
     * @return the parsed integer, or -1 if the input was not a valid integer
     */
    private int readInt()
    {
        try
        {
            return Integer.parseInt(scanner.nextLine().trim());
        }
        catch(Exception e)
        {
            return -1;
        }
    }

    /**
     * Repeatedly prompts the user until they enter a valid non-negative
     * integer. Used for the number of staff/visitor slots at start-up,
     * so a bad entry there cannot crash or silently misconfigure the
     * car park.
     *
     * @return a validated non-negative integer
     */
    private int readNonNegativeInt()
    {
        while(true)
        {
            int value = readInt();
            if(value >= 0)
            {
                return value;
            }
            System.out.print("Please enter a valid non-negative number: ");
        }
    }

    /**
     * Repeatedly prompts the user with the given message until they enter
     * yes or no (case-insensitive), and returns the corresponding
     * boolean. This avoids the common pitfall of using
     * Boolean.parseBoolean(), which only recognises the literal word
     * true and would silently treat yes as false.
     *
     * @param prompt the message to display when asking for input
     * @return true if the user entered yes, false if they entered no
     */
    private boolean readYesNo(String prompt)
    {
        while(true)
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("yes"))
            {
                return true;
            }
            if(input.equals("no"))
            {
                return false;
            }

            System.out.println("Please enter 'yes' or 'no'.");
        }
    }
}