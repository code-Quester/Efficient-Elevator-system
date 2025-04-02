import java.util.*;

public class ElevatorController {
    private List<Elevator> elevators;
    private Queue<Request> outsideRequests;
    private int totalFloors;
    public ElevatorController (int numElevators, int totalFloors) {
        elevators = new ArrayList<>();
        this.totalFloors =totalFloors;
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i));
        }
        outsideRequests = new LinkedList<>();
    }

    public void addOutsideRequest(int floor, Request.RequestDirection direction){
        Request r = new Request(Request.RequestType.OUTSIDE, floor, direction);
        outsideRequests.add(r);
    }

    public void addInsideRequest(int elevatorId, int floor){
        Elevator elevator = elevators.get(elevatorId);
        elevator.addStop(floor);
    }

    private void assignRequests(){
        while (!outsideRequests.isEmpty()) {
            Request request = outsideRequests.poll();
            Elevator bestElevator = findBestElevator(request);
            if(bestElevator!=null) {
                bestElevator.addStop(request.getFloor());
            }else{
                outsideRequests.add(request);
            }
        }
    }
    public Elevator findBestElevator(Request request){
        Elevator bestElevator =null;
        int bestScore = Integer.MAX_VALUE;
        int requestFloor = request.getFloor();
        Request.RequestDirection requestDirection = request.getDirection();

        for(Elevator elevator: elevators){
            int currentFloor = elevator.getCurrentFloor();
            Elevator.ElevatorDirection eleDir = elevator.getDirection();
            int distance = Math.abs(requestFloor - currentFloor);
            int score = distance * 10;
            if (eleDir == Elevator.ElevatorDirection.IDLE) {
                score -= 5;
            }else if ((eleDir == Elevator.ElevatorDirection.UP && requestDirection == Request.RequestDirection.UP && currentFloor <= requestFloor ) || (eleDir == Elevator.ElevatorDirection.DOWN && requestDirection == Request.RequestDirection.DOWN && currentFloor >= requestFloor)) {
                score +=0;
            }else{
                score +=50;
            }
            if (score< bestScore) {
                bestScore = score;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
    public void run() {
        for (int time = 0; time < 100; time++) {
            // Randomly add an outside request (e.g., 10% chance per step)
            if (Math.random() < 0.1) {
                int randomFloor = (int) (Math.random() * totalFloors) + 1;
                Request.RequestDirection randomDir = Math.random() < 0.5 ? Request.RequestDirection.UP : Request.RequestDirection.DOWN;
                addOutsideRequest(randomFloor, randomDir);
                System.out.println("\u001b[31m" + "New outside request: Floor " + randomFloor + " " + randomDir + "\u001b[0m");
            }
    
            assignRequests();
    
            for (Elevator elevator : elevators) {
                elevator.move();
                if (elevator.isAtStop) {
                    int elevatorId = elevator.getElevatorId();
                    int randomDestination = generateRandomDestination(elevator.getCurrentFloor(), totalFloors);
                    addInsideRequest(elevatorId, randomDestination);
                    System.out.println("Elevator " + elevatorId + " picked up passenger at " + elevator.getCurrentFloor() + ", heading to " + randomDestination);
                }
            }
    
            System.out.println("Time " + time );
            try {
                Thread.sleep(500); // 500ms delay per step
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private int generateRandomDestination(int currentFloor, int totalFloors) {
        int destination;
        do {
            destination = (int) (Math.random() * totalFloors) + 1; // Floors 1 to totalFloors
        } while (destination == currentFloor); // Ensure it’s a different floor
        return destination;
    }
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(2,10); // 2 elevators, 10 floors
        controller.addOutsideRequest(3, Request.RequestDirection.UP); // Initial request
        controller.run();
    }
}
