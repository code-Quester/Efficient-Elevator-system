public class Request {
    public enum RequestDirection { //for outside
        UP, DOWN;
    }
    public enum RequestType {
        INSIDE,  
        OUTSIDE 
    }
    private RequestType type;
    private int floor;
    private RequestDirection direction;
// constructor for the outside request
    public Request(RequestType type, int  floor, RequestDirection direction){
        this.type = type;
        this.floor = floor;
        this.direction = direction;
    }
// constructor for the inside request
    public Request(RequestType type, int  floor){
        this.type = type;
        this.floor = floor;
        this.direction =null;
    }

    //getters
    public RequestType getType(){
        return type;
    }
    public int getFloor(){
        return floor;
    }
    public RequestDirection getDirection(){
        return direction;
    }
}
