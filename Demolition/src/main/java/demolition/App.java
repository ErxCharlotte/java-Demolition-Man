package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;

public class App extends PApplet{
    /**
     * The Basic attributes (Window size - width)
     */
    public static final int WIDTH = 480;
    /**
     * The Basic attributes (Window size - height)
     */
    public static final int HEIGHT = 480;
    /**
     * The Basic attributes (frameRate)
     */
    public static final int FPS = 60;
    /**
     * The Basic attributes (The path of the json file to be used)
     */
    public String path = "./config.json";
    /**
     * The Basic Class (Used to load the app)
     */
    public LoadConfig loadConfig  = new LoadConfig();
    /**
     * The Basic Class (BombGuy)
     */
    public BombGuy bombGuy = new BombGuy();
    /**
     * Storage tool (Store the content of the json file)
     */
    public HashMap<String, String> configList;
    /**
     * Storage tool (Store the content of the map)
     */
    public ArrayList<String> mapList;
    /**
     * Storage tool (Store the content of the RED enemies)
     */
    public ArrayList<Integer> indexListRED;
    /**
     * Storage tool (Store the content of the YELLOW enemies)
     */
    public ArrayList<Integer> indexListYELLOW;
    /**
     * The Basic attributes (The game starts)
     */
    public boolean start = true;
    /**
     * The Basic attributes (The game resets)
     */
    public boolean reset = false;
    /**
     * The Basic attributes (Key is pressed)
     */
    public boolean isPressed = false;
    /**
     * The Basic attributes (whether bombguy can move)
     */
    public boolean canMove = true;
    /**
     * The Basic attributes (Timer)
     */
    public int count = 0;
    /**
     * Constructor for a app
     * Responsible for loading the configuration at the beginning of the game. 
     * By default, start becomes false
     */
    public App() {
        this.configList = this.loadConfig.loadConfig(this.path);
        this.loadConfig.loadLevel1(this);
        this.start = false;
    }
    /**
     * Set the size of the window
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /**
     * Load the images needed by the App
     */
    public void setup() {
        this.loadConfig.setupOnApp(this);
    }
    /**
     * Call this method every frame to draw in the window
     */
    public void draw() {
        this.loadConfig.drawOnApp(this);
    }
    /**
     * Move according to the button pressed
     */
    public void keyPressed() {
        this.loadConfig.keyPressed(this);
    }
    /**
     * This method is used to determine whether the button has been released after being pressed
     */
    public void keyReleased() {
        this.canMove = true;
    }
    /**
     * This method is used to determine whether the button has been released after being pressed
     * @param isReleased State of key release
     */
    public void isReleased(boolean isReleased){
        if (isReleased == true){
            this.canMove = true;
        }
    }
    /**
     * Set the path of the json file to be loaded
     * @param path json file path
     */
    public void setConfig(String path){
        this.loadConfig.setConfig(path, this);
    }
    /**
     * This method demonstrates App().
     * @param args Unused.
     */
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}

