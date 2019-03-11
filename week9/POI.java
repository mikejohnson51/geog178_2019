import java.io.Serializable;

public class POI extends Point implements Serializable {

    private String name;

    // parameterized constructor
    public POI (double x, double y, String name) {
        super(x,y);
        this.name = name;
    }

    // Getters and setters
    String getName() { return name; }
    void setName(String n) {name = n; }

    public static void put(String text, Point point) {
    }

    public static void put(String text) {
    }

}