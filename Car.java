import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * Represents a car parked (or to be parked) in the car park.
 * A car is identified by its registration number and records its owner,
 * make, model, whether the owner is a staff member, and the time it was
 * parked. It can also calculate how long it has been parked and the
 * resulting parking fee.
 *
 * @author Yugma ManojBhai Mangukiya
 * @id 106227407
 * @version 1.0
 * @date 11/09/2026
 */
public class Car
{
    // Parking is charged at this rate per hour (or part thereof).
    private static final double RATE_PER_HOUR = 10.0;
    
    // Format used when displaying the parked time to the user.
    private static final DateTimeFormatter TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String registrationNumber;
    private String owner;
    private String make;
    private String model;
    private boolean staff;
    private LocalDateTime parkedTime;

    /**
     * Creates a new Car.
     *
     * @param registrationNumber the car registration number of car (e.g. "T12345")
     * @param owner              the name of the owner of car
     * @param make               the make of car
     * @param model              the model of car
     * @param staff              true if the owner is a staff member, false if a visitor
     */
    public Car(String registrationNumber, String owner,
           String make, String model, boolean staff)
    {
        if(registrationNumber == null
            || !registrationNumber.matches("[A-Z][0-9]{5}"))
        {
            throw new IllegalArgumentException("Invalid registration number");
        }

        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.staff = staff;
        this.parkedTime = null;
    }

    /**
     * Returns the registration number of car.
     *
     * @return the registration number
     */
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    /**
     * Returns the name of the owner of car.
     *
     * @return the name of owner
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Returns the make of car.
     *
     * @return the make
     */
    public String getMake()
    {
        return make;
    }

    /**
     * Returns the model of car.
     *
     * @return the model
     */
    public String getModel()
    {
        return model;
    }

    /**
     * Returns whether the owner of this car is a staff member.
     *
     * @return true if the owner is a staff member, false if a visitor
     */
    public boolean isStaff()
    {
        return staff;
    }

    /**
     * Records the date and time at which this car was parked.
     *
     * @param parkedTime the date and time the car was parked
     */
    public void setParkedTime(LocalDateTime parkedTime)
    {
        this.parkedTime = parkedTime;
    }

    /**
     * Returns the raw date and time at which this car was parked.
     *
     * @return the parked time, or null if the car has not been parked
     */
    public LocalDateTime getParkedTime()
    {
        return parkedTime;
    }

    /**
     * Returns the parked time formatted for display, e.g. "2026-08-10 16:48:05".
     *
     * @return the formatted parked time, or "N/A" if the car has not been parked
     */
    public String getFormattedParkedTime()
    {
        if(parkedTime == null)
        {
            return "N/A";
        }
        return parkedTime.format(TIME_FORMATTER);
    }

    /**
     * Calculates how long this car has been parked, from the recorded
     * parked time until now.
     *
     * @return the duration formatted as "X hours Y minutes and Z seconds",
     *         or "N/A" if the car has not been parked
     */
    public String getParkingDuration()
    {
        if(parkedTime == null)
        {
            return "N/A";
        }

        long totalSeconds = Duration.between(parkedTime, LocalDateTime.now()).getSeconds();
        
        if(totalSeconds < 0)
        {
            totalSeconds = 0;
        }

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return hours + " hours " + minutes + " minutes and " + seconds + " seconds";
    }

    /**
     * Calculates the parking fee owed so far, charged at $10 per hour,
     * with any part-hour rounded up to a full hour (minimum charge is
     * 1 hour once the car has been parked).
     *
     * @return the parking fee in dollars, or 0.0 if the car has not been parked
     */
    public double getParkingFee()
    {
        if(parkedTime == null)
        {
            return 0.0;
        }

        long totalSeconds = Duration.between(parkedTime, LocalDateTime.now()).getSeconds();
        if(totalSeconds < 0)
        {
            totalSeconds = 0;
        }

        long chargeableHours = (long) Math.ceil(totalSeconds / 3600.0);
        if(chargeableHours < 1)
        {
            chargeableHours = 1;
        }

        return chargeableHours * RATE_PER_HOUR;
    }

    /**
     * Returns a short text summary of the car.
     *
     * @return a string in the form "regNo | make | owner"
     */
    @Override
    public String toString()
    {
        return registrationNumber + " | " + make + " | " + owner;
    }
}