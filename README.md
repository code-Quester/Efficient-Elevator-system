# Elevator System Simulation

## Overview

This project implements a multi-elevator system simulation in Java, designed to model the behavior of elevators in a building. The system handles both outside requests (users calling elevators from floors) and inside requests (passengers selecting destination floors). It features a controller to manage multiple elevators, assign requests efficiently, and simulate real-time movement, pickup, and drop-off operations.

The simulation aims to optimize response times, passenger travel efficiency, and elevator utilization while mimicking realistic elevator behavior, such as moving one floor at a time and servicing stops in a directionally optimized manner.

## Features

- Multiple Elevators: Supports any number of elevators, each with independent movement and stop management.
- Request Types:
  - Outside Requests: Users on a floor request an elevator with a direction (UP or DOWN).
  - Inside Requests: Passengers inside an elevator select their destination floor.
- Efficient Assignment: Assigns outside requests to the best elevator based on proximity, direction compatibility, and idle status.
- Real-Time Updates: Elevators respond to new requests dynamically, stopping at floors as they pass if requested.
- Direction Optimization: Elevators follow a SCAN-like algorithm, servicing all stops in one direction before reversing.
- Simulation: Runs a time-stepped simulation with random outside requests and passenger pickups, logging elevator movements.

## Prerequisites

- Java Development Kit (JDK): Version 8 or higher.
- IDE (Optional): IntelliJ IDEA, Eclipse, or any Java-compatible editor.
- Command Line: For compiling and running the code manually.

## Setup and Running

1. Clone or Download the Project:
   - Copy all .java files into a single directory (e.g., ElevatorSystem).

2. File Structure:
   Ensure the following files are present:
   - Elevator.java: Defines the Elevator class and its movement logic.
   - Request.java: Defines the Request class with nested enums RequestType and RequestDirection.
   - ElevatorController.java: Manages elevators, assigns requests, and runs the simulation.

3. Compile the Code:
   Open a terminal in the project directory and run:
   ```bash
   javac *.java
   ```

4. Run the Simulation:
   Execute the ElevatorController class, which includes a main method:
   ```bash
   java ElevatorController
   ```
   - This starts a simulation with 2 elevators in a 10-floor building for 100 time steps.
   - Output shows elevator movements, request pickups, and passenger destinations.

5. Customize (Optional):
   - Modify the main method in ElevatorController.java to change the number of elevators (numElevators), total floors (totalFloors), or simulation duration (time).

## Code Structure

- Elevator.java:
  - Manages individual elevator state (current floor, direction, stops) and movement.
  - Key Methods: move(), addStop(), removeStop(), isAtStop().
  - Uses a TreeSet for stops to ensure ordered and unique floor requests.

- Request.java:
  - Represents user requests with type (INSIDE/OUTSIDE), floor, and direction.
  - Nested Enums: RequestType (INSIDE, OUTSIDE), RequestDirection (UP, DOWN).

- ElevatorController.java:
  - Orchestrates the system with a list of elevators and a queue of outside requests.
  - Key Methods: addOutsideRequest(), addInsideRequest(), findBestElevator(), run().

## Output Example

```
Time 0:
New outside request: Floor 1 UP
Elevator 1 reached floor 1
Elevator 1 picked up passenger at 1, heading to 5
Time 1:
Elevator 0 reached floor 3
Elevator 0 picked up passenger at 3, heading to 2
```

- Each time step shows elevator states, new requests, and passenger interactions.

## Efficiency Analysis

- Response Time: Outside requests are serviced within 0-3 time steps, often immediately.
- Travel Time: Passengers reach destinations in 1-10 steps, depending on distance and stops.
- Load Balancing: Both elevators are utilized evenly, with no prolonged idle periods.
- Direction Optimization: SCAN-like movement minimizes backtracking.

## Potential Improvements

- Stop Load Consideration: Factor in the number of existing stops when assigning requests to avoid overloading an elevator.
- Passenger Grouping: Batch passengers with similar destinations to reduce stops.
- Idle Positioning: Move idle elevators to central floors (e.g., floor 5) for faster responses.
- Capacity Limits: Add a maximum passenger capacity per elevator.
- Visualization: Implement a graphical interface to display elevator movements.

## Contributing

Feel free to fork this project, submit pull requests, or suggest enhancements via issues. Contributions to improve efficiency, add features, or fix bugs are welcome!

---
