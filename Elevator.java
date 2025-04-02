import java.util.*;
public class Elevator {
    public enum ElevatorDirection {
        UP, DOWN, IDLE
    }
    public int id;
    private int currentFloor;
    private ElevatorDirection direction;
    private TreeSet<Integer> stops;
    public boolean isAtStop;
    //constructor
    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = ElevatorDirection.IDLE;
        this.stops = new TreeSet<>();
        this.isAtStop =false;
    }

    public void addStop (int floor){
        stops.add(floor);
    }
    public void removeStop (int floor){
        stops.remove(floor);
    }

    public void move() {
        if (stops.isEmpty()) {
            direction = ElevatorDirection.IDLE;
            return;
        }
        Integer target =null;
        if(direction == ElevatorDirection.UP){
            target = stops.higher(currentFloor);
            if(target ==null){
                direction  = ElevatorDirection.DOWN;
                target = stops.lower(currentFloor);
            }
        }else if (direction == ElevatorDirection.DOWN) {
            target = stops.lower(currentFloor);
            if(target ==null){
                direction  = ElevatorDirection.UP;
                target = stops.higher(currentFloor);
            }  
        }else{//idle elevator
            //choosing the closest stop
            Integer lower = stops.lower(currentFloor);
            Integer higher = stops.higher(currentFloor);
            if(lower == null && higher ==null){
                return;
            }else if(lower == null ) {
                target = higher;
                direction = ElevatorDirection.UP;
            }else if(higher == null) {
                target = lower;
                direction  = ElevatorDirection.DOWN;
            }else{
                if(currentFloor - lower <= higher - currentFloor){
                    target = lower;
                    direction = ElevatorDirection.DOWN;
                }else{
                    target = higher;
                    direction = ElevatorDirection.UP;
                }
            }
        }
//logic of movement of the elevator
        if (target !=null) {
            if(target> currentFloor) currentFloor++;
            else if (target< currentFloor) currentFloor--; 
            if (currentFloor == target) {
                stops.remove(target);
                isAtStop = true;
                System.out.println("\u001b[32m"+"Elevator " + id + " reached floor " + currentFloor + "\u001b[0m");
            }
        }
    }
    //getters
    public int getCurrentFloor() {
        return currentFloor;
    }
    public ElevatorDirection getDirection(){
        return direction;
    }
    public int getElevatorId(){
        return id;
    }
    // public static void main(String[] args) {
    //     Elevator elevator = new Elevator(1, 10); // ID 1, 10 floors
    //     elevator.addStop(3);
    //     elevator.addStop(5);
    //     for (int i = 0; i < 6; i++) {
    //         System.out.println("Floor: " + elevator.getCurrentFloor() + ", Direction: " + elevator.getDirection());
    //         elevator.move();
    //     }
    // }
}
