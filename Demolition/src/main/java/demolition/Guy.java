package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public abstract class Guy{
    /**
     * Used to calculate time
     */
    private double timer = 0.0;
    /**
     * The value is the cycle time of guy's image change
     */
    public static final double SECONDS_BETWEEN_RENDERED = 0.2;
    /**
     * The location of this guy (X value)
     */
    protected int x;
    /**
     * The location of this guy (Y value)
     */
    protected int y;
    /**
     * Whether this guy can move (left), the default is false
     */
    public boolean moveLeft = false;
    /**
     * Whether this guy can move (down), the default is false
     */
    public boolean moveDown = false;
    /**
     * Whether this guy can move (up), the default is false
     */
    public boolean moveUp = false;
    /**
     * Whether this guy can move (right), the default is false
     */
    public boolean moveRight = false;
    /**
     * Whether this guy can move, the default is true
     */
    public boolean canMove = true;
    /**
     * This value judges whether this guy is killed or not, if it is killed, the value is true, and the default is false
     */
    public boolean beKilled = false;
    /**
     * Images of this guy
     */
    public PImage sprite;
    /**
     * Images of this guy (1)
     */
    public PImage sprite1;
    /**
     * Images of this guy (2)
     */
    public PImage sprite2;
    /**
     * Images of this guy (3)
     */
    public PImage sprite3;
    /**
     * Images of this guy (4)
     */
    public PImage sprite4;
    /**
     * Images of this guy (down1)
     */
    public PImage spriteDown1;
    /**
     * Images of this guy (down2)
     */
    public PImage spriteDown2;
    /**
     * Images of this guy (down3)
     */
    public PImage spriteDown3;
    /**
     * Images of this guy (down4)
     */
    public PImage spriteDown4;
    /**
     * Images of this guy (right1)
     */
    public PImage spriteRight1;
    /**
     * Images of this guy (right2)
     */
    public PImage spriteRight2;
    /**
     * Images of this guy (right3)
     */
    public PImage spriteRight3;
    /**
     * Images of this guy (right4)
     */
    public PImage spriteRight4;
    /**
     * Images of this guy (left1)
     */
    public PImage spriteLeft1;
    /**
     * Images of this guy (left2)
     */
    public PImage spriteLeft2;
    /**
     * Images of this guy (left3)
     */
    public PImage spriteLeft3;
    /**
     * Images of this guy (left4)
     */
    public PImage spriteLeft4;
    /**
     * Images of this guy (up1)
     */
    public PImage spriteUp1;
    /**
     * Images of this guy (up2)
     */
    public PImage spriteUp2;
    /**
     * Images of this guy (up3)
     */
    public PImage spriteUp3;
    /**
     * Images of this guy (up4)
     */
    public PImage spriteUp4;
    /**
     * Constructor for a Guy
     */
    public Guy(){
    }
    /**
     * Constructor for a guy, requires the location of the guy (X and Y value)
     * @param x the location of the guy (X value)  
     * @param y the location of the guy (Y value)  
     */
    public Guy(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * This method is used for the guy to change accordingly as each frame advances (Used for guy's move)
     * The changes will be displayed in the app's window
     * @param app The app used to load the program
     */
    public abstract void tick(App app);
    /**
     * This method is used for the guy to change accordingly as each frame advances (Used for guy's facing)
     * The changes will be displayed in the app's window
     * @param app The app used to load the program
     */
    public abstract void direction(App app);
    /**
     * This method is used to determine the image that the guy should display according to the current time
     * The image will be periodically changed according to a specific display cycle
     * @param app The app used to load the program
     */
    public void drawGuy(App app){
        this.timer ++;
        this.sprite = this.sprite1;
        if (this.timer > SECONDS_BETWEEN_RENDERED * app.FPS){
            int frameDirection = (int) Math.floor(app.count/12);
            if (frameDirection % 4 == 0) {
                this.x = this.x;
                this.sprite = this.sprite2;
            }else if (frameDirection % 4 == 1){
                this.x = this.x;
                this.sprite = this.sprite3;
            }else if (frameDirection % 4  == 2){
                this.x = this.x;
                this.sprite = this.sprite4;
            }else if (frameDirection % 4 == 3){
                this.x = this.x;
                this.sprite = this.sprite1;
            }
        }
    }
    /**
     * This method is used to draw the guy's image in the app window
     * @param app The app used to load the program
     * @param sprite image of this guy
     */
    public void draw(App app, PImage sprite){
        app.image(sprite, this.x, this.y, 32, 48);
    }
    /**
     * This method is used to return the x value of the current position of this guy
     * @return x 
     */
    public int getX(){
        return this.x;
    }
    /**
     * This method is used to return the y value of the current position of this guy
     * @return y 
     */
    public int getY(){
        return this.y;
    }
    /**
     * This method judges whether the guy can move based on the guy's current position and the expected next position
     * If there is an obstruction, canMove is false, and the default is true
     * @param app The app used to load the program
     * @return canMove 
     */
    public boolean canMove(App app){
        ArrayList<String> mapList = app.mapList;
        int nextX = this.getX();
        int nextY = this.getY();
        
        if (this.moveLeft){
            nextX = this.getX() - 32;
        }else if (this.moveRight){
            nextX = this.getX() + 32;
        }else if (this.moveDown){
            nextY = this.getY() + 32;
        }else if (this.moveUp){
            nextY = this.getY() - 32;
        }

        //Solid Wall
        for (int i = 0; i < mapList.size(); i++){
            if (mapList.get(i).equals("W")){
                int row = (int) Math.floor(i/15);
                int wallY = 48 + row * 32;
                int column = (int) i % 15;
                int wallX = column * 32;
                if (nextX == wallX && nextY == wallY){
                    this.canMove = false;
                }
            }
        }

        //Broken Wall
        for (int u = 0; u < app.loadConfig.brokenWallList.size(); u ++){
            int index = app.loadConfig.brokenWallList.get(u);
            int row = (int) Math.floor(index/15);
            int wallY = 48 + row * 32;
            int column = (int) index % 15;
            int wallX = column * 32;
            if (nextX == wallX && nextY == wallY){
                this.canMove = false;
            }
        }
        return canMove;
    }
}