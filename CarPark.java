import java.util.ArrayList;

/**
 * Manages the collection of parking slots at a car park site.
 * Responsible for adding, finding and deleting slots, and for
 * finding and removing cars by registration number.
 */
public class CarPark
{
    private ArrayList<ParkingSlot> slots;

    /**
     * Creates a new, empty car park.
     */
    public CarPark()
    {
        slots = new ArrayList<>();
    }

    /**
     * Adds a new slot to the car park, provided the slot is not null and
     * no existing slot already has the same ID.
     *
     * @param slot the slot to add
     * @return true if the slot was added, false if it was null or its ID
     *         was already in use
     */
    public boolean addSlot(ParkingSlot slot)
    {
        if(slot == null)
        {
            return false;
        }
        
        if(findSlot(slot.getSlotId()) != null)
        {
            return false;
        }
        
        slots.add(slot);
        return true;
    }

    /**
     * Finds the slot with the given ID (case-insensitive).
     *
     * @param slotId the slot ID to search for
     * @return the matching slot, or null if no slot has that ID
     */
    public ParkingSlot findSlot(String slotId)
    {
        if(slotId == null)
        {
            return null;
        }
        for(ParkingSlot slot : slots)
        {
            if(slot.getSlotId().equalsIgnoreCase(slotId))
            {
                return slot;
            }
        }
        return null;
    }

    /**
     * Deletes the slot with the given ID, provided it exists and is not
     * currently occupied by a car.
     *
     * @param slotId the ID of the slot to delete
     * @return true if the slot was deleted, false if it did not exist or
     *         was occupied
     */
    public boolean deleteSlot(String slotId)
    {
        ParkingSlot slot = findSlot(slotId);
        if(slot == null)
        {
            return false;
        }
        if(slot.isOccupied())
        {
            return false;
        }
        slots.remove(slot);
        return true;
    }

    /**
     * Prints every slot in the car park to the console.
     * (Kept for convenience; the Application class provides the
     * full formatted listing shown to the user.)
     */
    public void listSlots()
    {
        for(ParkingSlot slot : slots)
        {
            System.out.println(slot);
        }
    }

    /**
     * Deletes every slot in the car park that is not currently occupied.
     * Occupied slots are left untouched.
     */
    public void deleteAllUnoccupiedSlots()
    {
        slots.removeIf(slot -> !slot.isOccupied());
    }

    /**
     * Parks a car in the slot with the given ID.
     *
     * @param slotId the ID of the slot to park in
     * @param car    the car to park
     * @return true if the car was parked successfully, false if the slot
     *         does not exist or could not accept the car
     */
    public boolean parkCar(String slotId, Car car)
    {
        ParkingSlot slot = findSlot(slotId);
        if(slot == null)
        {
            return false;
        }
        return slot.parkCar(car);
    }

    /**
     * Finds the slot containing the car with the given registration number
     * (case-insensitive).
     *
     * @param regNo the registration number to search for
     * @return the slot containing that car, or null if not found
     */
    public ParkingSlot findCar(String regNo)
    {
        if(regNo == null)
        {
            return null;
        }
        for(ParkingSlot slot : slots)
        {
            if(slot.isOccupied()
                && slot.getParkedCar().getRegistrationNumber().equalsIgnoreCase(regNo))
            {
                return slot;
            }
        }
        return null;
    }

    /**
     * Removes the car with the given registration number from whichever
     * slot it is parked in.
     *
     * @param regNo the registration number of the car to remove
     * @return true if a matching car was found and removed, false otherwise
     */
    public boolean removeCar(String regNo)
    {
        ParkingSlot slot = findCar(regNo);
        if(slot == null)
        {
            return false;
        }
        slot.removeCar();
        return true;
    }

    /**
     * Returns the list of all slots in the car park.
     *
     * @return the list of slots
     */
    public ArrayList<ParkingSlot> getSlots()
    {
        return slots;
    }
}
