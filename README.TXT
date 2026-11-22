------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Parking Spot System

AUTHORS: Yugma Manojbhai Mangukiya

PURPOSE OF PROJECT: This project is a console-based Parking Spot Management System developed for COS70006 Advanced Software Development Project 1.

The system allows a company to manage staff and visitor parking slots. Users can create parking slots, delete slots, park cars, find parked cars, remove cars, and display all parking slot information.
 
The system also implements advanced features including recording the parking time of a car, calculating parking duration, and calculating parking fees at a rate of $10 per hour (or part thereof).

VERSION or DATE:
Version 1.0
11 September 2026

HOW TO START THIS PROJECT:
1. Open the project in BlueJ.
2. Compile all classes.
3. Run the Application class.
4. Create the initial car park by entering the number of staff slots and visitor slots.
5. Select menu options to manage parking slots and cars. 


USER INSTRUCTIONS:
 
Menu Options:
 
1. Add a car slot
- Enter a unique slot ID in the format:
Capital letter followed by 3 digits
Example: F001, T005
 
2. Delete a car slot
- Enter the slot ID.
- Occupied slots cannot be deleted.
 
3. List all car slots
- Displays:
* Slot ID
* Slot type (Staff/Visitor)
* Occupied status
* Car registration number
* Owner
* Make
* Parking time
* Parking duration
* Parking fee
 
4. Delete all unoccupied slots
- Removes every vacant parking slot.
- Occupied slots remain in the system.
 
5. Park a car
- Enter slot ID and car details.
- Registration number format: Capital letter followed by 5 digits (Example: T12345)
- Staff cars can only park in staff slots.
- Visitor cars can only park in visitor slots.
- Parking time is recorded automatically.
 
6. Find a car
- Search using registration number.
- Displays slot information, parking duration,
and parking fee.
 
7. Remove a car
- Enter registration number to remove the car
from its parking slot.
 
8. Exit
- Displays "Program end!" and closes the program.
 
VALIDATION RULES:
- Slot IDs must be one uppercase letter followed by three digits.
- Registration numbers must be one uppercase letter followed by five digits.
- Slot IDs must be unique.
- A car cannot occupy more than one slot.
- A car can only be parked in an unoccupied slot.
- Invalid user input will not crash the program.
 
ADVANCED FEATURES:
- Records and displays parking time when a car is parked.
- Displays parking duration in hours, minutes, and seconds.
- Calculates parking fees at $10 per hour.
- Any part of an hour is charged as a full hour.
