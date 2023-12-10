package com.mycompany.a3.GameObjects;

//Code Name One Libraries
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

// Java Libraries
import java.util.Random;
import java.lang.Math;

// My libraries
import com.mycompany.a3.GameWorld;

/**
 * GameObject. All the objects on the map
 */
public abstract class GameObject {

    private Random rand = new Random();

    private Integer size;

    private Point location;

    private int myColor;

    private String name;

    public GameObject() {
        // ObjectSize is between 10 and 50
        this.size = rand.nextInt(41) + 10;
        this.location = new Point(rand.nextFloat() * 1000.0f, rand.nextFloat() * 1000.0f);
        GameObject.putInBounds(this);
        this.myColor = ColorUtil.BLUE;
    }

    public GameObject(String name) {
        this();
        this.setName(name);
    }

    // Getters and Setters

    public Integer getSize() {
        return this.size;
    }

    protected void setSize(Integer newSize) {
        // size can't be less than 1 or more than equal to 1000
        if (newSize < 1) {
            newSize = 1;
        } else if (newSize >= 1000) {
            newSize = 1000;
        }
        this.size = newSize;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Point getLocation() {
        return location;
    }

    protected boolean setLocation(Point newLocation) {
        location = newLocation;
        return true;
    }

    public int getColor() {
        return myColor;
    }

    protected boolean setColor(int newColor) {
        myColor = newColor;
        return true;
    }

    protected Random getRand() {
        return rand;
    }

    // class methods //

    public abstract void tick();

    // Class Statics //

    /**
     * Guarantee to put object in bounds.
     * Should not be used for bump checking
     * 
     * @param GameObject
     */
    public static void putInBounds(GameObject go) {
        // TODO: clean this up, use mod to restrict the values.
        // If game object is not in range of the game world, put it inside.
        if (go.location.getX() > GameWorld.getWorldMax().getX()) {
            Integer tmpX = new Integer(Math.round(go.location.getX()) % Math.round(GameWorld.getWorldMax().getX()));
            go.location.setX((float) tmpX.doubleValue());
        } else if (go.location.getX() < GameWorld.getWorldMax().getX()) {
            Integer tmpX = new Integer(Math.round(go.location.getX()) % Math.round(GameWorld.getWorldMax().getX()));
            go.location.setX((float) tmpX.doubleValue());
        }
        if (go.location.getY() > GameWorld.getWorldMax().getY()) {
            Integer tmpX = new Integer(Math.round(go.location.getY()) % Math.round(GameWorld.getWorldMax().getY()));
            go.location.setY((float) tmpX.doubleValue());
        } else if (go.location.getY() < GameWorld.getWorldMax().getY()) {
            Integer tmpX = new Integer(Math.round(go.location.getY()) % Math.round(GameWorld.getWorldMax().getY()));
            go.location.setY((float) tmpX.doubleValue());
        }
    }

    /**
     * @param go
     * @return
     * @True if GameObject in bounds
     * @False if GameObject out of bounds
     */
    public static boolean inBoundsCheck(GameObject go) {
        return GameObject.inBoundsCheck(go.getLocation());
    }

    /**
     * @return true if the point is in the realm of the world
     * @param pnt
     */
    public static boolean inBoundsCheck(Point pnt) {
        // X out of bounds
        if (pnt.getX() > GameWorld.getWorldMax().getX()) {
            return false;
        } else if (pnt.getX() < GameWorld.getWorldMin().getX()) {
            return false;
        }

        // Y out of bounds
        if (pnt.getY() > GameWorld.getWorldMax().getY()) {
            return false;
        } else if (pnt.getY() < GameWorld.getWorldMin().getY()) {
            return false;
        }

        // In bounds
        return true;
    }

    /**
     * returns s00i
     * string + 3 left padded 0's if less than 4
     * Used for key if using hashmap
     * @param s foo, bar
     * @param i 1, 1111
     * @return foo001, bar1111
     */
    public static String name(String s,Integer i){
        String tmp = "";
        tmp += i.toString();
        while(tmp.length() < 3){
            tmp = "0" + tmp;
        }
        return s+tmp;
    }

    /**
     * Returns the key value for FoodStation.
     * FoodStation has a different format due to map
     * @param s
     * @param i
     * @return
     */
    public static String key(String s, Integer i){
        return name(s, i);
    }

    @Override
    public String toString() {
        double xRound = Math.round(this.location.getX() * 10.0f) / 10.0;
        double yRound = Math.round(this.location.getY() * 10.0f) / 10.0;
        return "" + name + " loc= (" + xRound + " , " + yRound +
                ") color: " + "[" + ColorUtil.red(myColor) + ","
                + ColorUtil.green(myColor) + ","
                + ColorUtil.blue(myColor) + "]";
    }



}
