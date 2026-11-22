/**
 * Represents a single parking slot in the car park. A slot has a unique
 * identifier, is designated for either staff or visitors, and may or may
 * not currently have a car parked in it.
 *
 * @author Yugma ManojBhai Mangukiya
 * @id 106227407
 * @version 1.0
 * @date 11/09/2026
 */
public class ParkingSlot
{
    private String slotId;
    private boolean staffSlot;
    private Car parkedCar;

    /**
     * Creates a new, unoccupied parking slot.
     *
     * @param slotId    the unique identifier for this slot (e.g. "A001")
     * @param staffSlot true if this slot is reserved for staff, false if for visitors
     */
    public ParkingSlot(String slotId, boolean staffSlot)
    {
        if(slotId == null || !slotId.matches("[A-Z][0-9]{3}"))
        {
            throw new IllegalArgumentException("Invalid slot ID");
        }

        this.slotId = slotId;
        this.staffSlot = staffSlot;
        parkedCar = null;
    }

    /**
     * Returns this slot's identifier.
     *
     * @return the slot ID
     */
    public String getSlotId()
    {
        return slotId;
    }

    /**
     * Returns whether this slot is reserved for staff.
     *
     * @return true if this is a staff slot, false if it is a visitor slot
     */
    public boolean isStaffSlot()
    {
        return staffSlot;
    }

    /**
     * Returns whether a car is currently parked in this slot.
     *
     * @return true if occupied, false otherwise
     */
    public boolean isOccupied()
    {
        return parkedCar != null;
    }

    /**
     * Returns the car currently parked in this slot.
     *
     * @return the parked car, or null if the slot is unoccupied
     */
    public Car getParkedCar()
    {
        return parkedCar;
    }

    /**
     * Parks a car in this slot, provided the slot is currently unoccupied
     * and a valid car is given.
     *
     * @param car the car to park
     * @return true if the car was parked successfully, false if the slot
     *         was already occupied or the car was null
     */
    public boolean parkCar(Car car)
    {
        if(car == null)
        {
            return false;
        }
        if(isOccupied())
        {
            return false;
        }
        parkedCar = car;
        return true;
    }

    /**
     * Removes the car currently parked in this slot, if any.
     */
    public void removeCar()
    {
        parkedCar = null;
    }

    /**
     * Returns a short text representation of this slot.
     *
     * @return the slot ID
     */
    @Override
    public String toString()
    {
        return slotId;
    }
}