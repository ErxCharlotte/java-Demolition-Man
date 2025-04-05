package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class Bomb{
    /**
     * The location of the bomb (X value)
     */
    protected int x;
    /**
     * The location of the bomb (Y value)
     */
    protected int y;
    /**
     * The top position of the two units from the bomb's location (Y value)
     */
    public int top1Y;
    /**
     * The top position of the one units from the bomb's location (Y value)
     */
    public int top2Y;
    /**
     * The bottom position of the two units from the bomb's location (Y value)
     */
    public int bottom1Y;
    /**
     * The bottom position of the one units from the bomb's location (Y value)
     */
    public int bottom2Y;
    /**
     * The left position of the two units from the bomb's location (X value)
     */
    public int left1X;
    /**
     * The left position of the one units from the bomb's location (X value)
     */
    public int left2X;
    /**
     * The right position of the two units from the bomb's location (X value)
     */
    public int right1X;
    /**
     * The right position of the one units from the bomb's location (X value)
     */
    public int right2X;

    /**
     * Used to calculate time
     */
    private double timer = 0.0;
    /**
     * Used to calculate the period of the explosion
     */
    public double period = 60.0;
    /**
     * Used to record the frame value when the bomb was placed
     */
    public int frameStart;
    /**
     * Images of bombs
     */
    public PImage sprite;
    /**
     * Images of bombs
     */
    public PImage sprite1;
    /**
     * Images of bombs
     */
    public PImage sprite2;
    /**
     * Images of bombs
     */
    public PImage sprite3;
    /**
     * Images of bombs
     */
    public PImage sprite4;
    /**
     * Images of bombs
     */
    public PImage sprite5;
    /**
     * Images of bombs
     */
    public PImage sprite6;
    /**
     * Images of bombs
     */
    public PImage sprite7;
    /**
     * Images of bombs
     */
    public PImage sprite8;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionCentreSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionBottomSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionLeftSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionRightSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionTopSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionHorizontalSprite;
    /**
     * Image when the bomb exploded
     */
    public PImage explosionVerticalSprite;
    /**
     * Used to determine whether the explosion occurred, the default is false
     */
    public boolean explosion = false;
    /**
     * Used to determine whether there is a solid wall at the top two units where the bomb is located, the default is false
     */
    public boolean top1Wall = false;
    /**
     * Used to determine whether there is a solid wall at the top one units where the bomb is located, the default is false
     */
    public boolean top2Wall = false;
    /**
     * Used to determine whether there is a solid wall at the bottom two units where the bomb is located, the default is false
     */
    public boolean bottom1Wall = false;
    /**
     * Used to determine whether there is a solid wall at the bottom one units where the bomb is located, the default is false
     */
    public boolean bottom2Wall = false;
    /**
     * Used to determine whether there is a solid wall at the left two units where the bomb is located, the default is false
     */
    public boolean left1Wall = false;
    /**
     * Used to determine whether there is a solid wall at the left one units where the bomb is located, the default is false
     */
    public boolean left2Wall = false;
    /**
     * Used to determine whether there is a solid wall at the right two units where the bomb is located, the default is false
     */
    public boolean right1Wall = false;
    /**
     * Used to determine whether there is a solid wall at the right one units where the bomb is located, the default is false
     */
    public boolean right2Wall = false;
    /**
     * Used to determine whether there is a Broken wall at the top of the bomb's location, the default is false
     */
    public boolean topBrokenWall = false;
    /**
     * Used to determine whether there is a Broken wall at the bottom of the bomb's location, the default is false
     */
    public boolean bottomBrokenWall = false;
    /**
     * Used to determine whether there is a Broken wall at the left of the bomb's location, the default is false
     */
    public boolean leftBrokenWall = false;
    /**
     * Used to determine whether there is a Broken wall at the right of the bomb's location, the default is false
     */
    public boolean rightBrokenWall = false;
    /**
     * Used to store the location of the Broken Wall that was blown up by the bomb
     */
    public ArrayList<Integer> removeBrokenWallList = new ArrayList<Integer>();
    /**
     * Used to store the location of the bomb explosion range (X value)
     */
    public ArrayList<Integer> locationX = new ArrayList<Integer>();
    /**
     * Used to store the location of the bomb explosion range (Y value)
     */
    public ArrayList<Integer> locationY = new ArrayList<Integer>();
    /**
     * Constructor for a Bomb
     */
    public Bomb(){
    }
    /**
     * Constructor for a Bomb, requires the location of the bomb (X and Y value)
     * @param x the location of the bomb (X value)  
     * @param y the location of the bomb (Y value)  
     */
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * This method is used to load the image required by the bomb in the app and the image required when it explodes
     * @param app The app used to load the program
     */
    public void setSprite(App app){
        this.sprite1 = app.loadConfig.bombSprite1;
        this.sprite2 = app.loadConfig.bombSprite2;
        this.sprite3 = app.loadConfig.bombSprite3;
        this.sprite4 = app.loadConfig.bombSprite4;
        this.sprite5 = app.loadConfig.bombSprite5;
        this.sprite6 = app.loadConfig.bombSprite6;
        this.sprite7 = app.loadConfig.bombSprite7;
        this.sprite8 = app.loadConfig.bombSprite8;
        this.explosionCentreSprite = app.loadConfig.explosionCentreSprite;
        this.explosionBottomSprite = app.loadConfig.explosionBottomSprite;
        this.explosionLeftSprite = app.loadConfig.explosionLeftSprite;
        this.explosionRightSprite = app.loadConfig.explosionRightSprite;
        this.explosionTopSprite = app.loadConfig.explosionTopSprite;
        this.explosionHorizontalSprite = app.loadConfig.explosionHorizontalSprite;
        this.explosionVerticalSprite = app.loadConfig.explosionVerticalSprite;
    }

    /**
     * This method is used to determine whether there is a solid wall or a Broken Wall in the explosion range of the bomb
     * If there is a solid wall, part of the image at the time of the explosion will not be displayed
     * If there is a broken wall, destroy it (Add it to the list of storage to be destroyed)
     * @param app The app used to load the program
     */
    public void haveWall(App app){
        int top1Y = this.getY() - 64;
        int top2Y = this.getY() - 32;
        int bottom1Y = this.getY() + 64;
        int bottom2Y = this.getY() + 32;
        int right1X = this.getX() + 64;
        int right2X = this.getX() + 32;
        int left1X = this.getX() - 64;
        int left2X = this.getX() - 32;

        ArrayList<Integer> solidWallList = app.loadConfig.solidWallList;
        for (int i = 0; i < solidWallList.size(); i++){
                int row = (int) Math.floor(solidWallList.get(i)/15);
                int wallY = 48 + row * 32;
                int column = (int) solidWallList.get(i) % 15;
                int wallX = column * 32;
                if (this.getX() == wallX){
                    if (top1Y == wallY){
                        this.top1Wall = true;
                    }else if (top2Y == wallY){
                        this.top2Wall = true;
                    }else if (bottom1Y == wallY){
                        this.bottom1Wall = true;
                    }else if (bottom2Y == wallY){
                        this.bottom2Wall = true;
                    }
                }else if (this.getY() == wallY){
                    if (left1X == wallX){
                        this.left1Wall = true;
                    }else if (left2X == wallX){
                        this.left2Wall = true;
                    }else if (right1X == wallX){
                        this.right1Wall = true;
                    }else if (right2X == wallX){
                        this.right2Wall = true;
                    }
                }
        }

        ArrayList<Integer> brokenWallList = app.loadConfig.brokenWallList;
        for (int u = 0; u < brokenWallList.size(); u++){
                int row = (int) Math.floor(brokenWallList.get(u)/15);
                int wallY = 48 + row * 32;
                int column = (int) brokenWallList.get(u) % 15;
                int wallX = column * 32;
                if (this.getX() == wallX){
                    if (top2Y == wallY){
                        this.topBrokenWall = true;
                    }else if (bottom2Y == wallY){
                        this.bottomBrokenWall = true;
                    }
                }else if (this.getY() == wallY){
                    if (left2X == wallX){
                        this.leftBrokenWall = true;
                    }else if (right2X == wallX){
                        this.rightBrokenWall = true;
                    }
                }
            }
    }
    /**
     * This method loads the position of the explosion range according to the position of the bomb (including X and Y values)
     * The x and y values of the explosion range will be stored in their respective lists
     * The list of stored x values will be combined with the list of stored y values
     * @param app The app used to load the program
     * @return location 
     */
    public ArrayList<ArrayList> loadLocation(App app){
        ArrayList<ArrayList> location = new ArrayList<ArrayList>();
        ArrayList<Integer> locationX = new ArrayList<Integer>();
        ArrayList<Integer> locationY = new ArrayList<Integer>();

        //Load the range of the explosion
        this.top1Y = this.getY() - 64;
        this.top2Y = this.getY() - 32;
        this.bottom1Y = this.getY() + 64;
        this.bottom2Y = this.getY() + 32;
        this.left1X = this.getX() - 64;
        this.left2X = this.getX() - 32;
        this.right1X = this.getX() + 64;
        this.right2X = this.getX() + 32;

        locationX.add(this.getX());
        locationY.add(this.getY());
        for (int p = 0; p < 4; p ++){
            locationX.add(this.getX());
        }
        locationY.add(this.top1Y);
        locationY.add(this.top2Y);
        locationY.add(this.bottom1Y);
        locationY.add(this.bottom2Y);

        for (int i = 0; i < 4; i ++){
            locationY.add(this.getY());
        }
        locationX.add(this.left1X);
        locationX.add(this.left2X);
        locationX.add(this.right1X);
        locationX.add(this.right2X);

        if (this.top2Wall == true){
            locationY.remove(Integer.valueOf(this.top1Y));
            locationY.remove(Integer.valueOf(this.top2Y));
            locationX.remove(Integer.valueOf(this.getX()));
            locationX.remove(Integer.valueOf(this.getX()));
        }
        if (this.bottom2Wall == true){
            locationY.remove(Integer.valueOf(this.bottom1Y));
            locationY.remove(Integer.valueOf(this.bottom2Y));
            locationX.remove(Integer.valueOf(this.getX()));
            locationX.remove(Integer.valueOf(this.getX()));
        }
        if (this.left2Wall == true){
            locationX.remove(Integer.valueOf(this.left1X));
            locationX.remove(Integer.valueOf(this.left2X));
            locationY.remove(Integer.valueOf(this.getY()));
            locationY.remove(Integer.valueOf(this.getY()));
        }
        if (this.right2Wall == true){
            locationX.remove(Integer.valueOf(this.right1X));
            locationX.remove(Integer.valueOf(this.right2X));
            locationY.remove(Integer.valueOf(this.getY()));
            locationY.remove(Integer.valueOf(this.getY()));
        }

        //Broken Wall
        if (this.topBrokenWall == true){
            locationY.remove(Integer.valueOf(this.top1Y));
            locationX.remove(Integer.valueOf(this.getX()));
        }
        if (this.bottomBrokenWall == true){
            locationY.remove(Integer.valueOf(this.bottom1Y));
            locationX.remove(Integer.valueOf(this.getX()));
        }
        if (this.leftBrokenWall == true){
            locationX.remove(Integer.valueOf(this.left1X));
            locationY.remove(Integer.valueOf(this.getY()));
        }
        if (this.rightBrokenWall == true){
            locationX.remove(Integer.valueOf(this.right1X));
            locationY.remove(Integer.valueOf(this.getY()));
        }
        location.add(locationX);
        location.add(locationY);
        return location;
    }
    /**
     * This method determines the scope of the explosion (data provided by a list storing X and Y values)
     * Within a certain period of time, the bomb will explode and will destroy the Broken Wall (if there is a broken wall)
     * When an explosion occurs, if bombGuy is within the explosion range, it will lose one life
     * @param app The app used to load the program
     */
    public void rangeExplosion(App app){
        ArrayList<ArrayList> location = this.loadLocation(app);
        int count = app.count - this.frameStart;
        
        //bombGuy
        if (count >= 150){
            //System.out.println(location.get(0));
            //System.out.println(location.get(1));
            for (int u = 0; u < location.get(0).size(); u ++){
                    if (app.bombGuy.getX() == (int) location.get(0).get(u) && app.bombGuy.getY() == (int) location.get(1).get(u)){
                        app.loadConfig.loseLives = true;
                    }
                }
        }

        //BrokenWall
        for (int i = 0; i < app.loadConfig.brokenWallList.size(); i ++){
            int row = (int) Math.floor(app.loadConfig.brokenWallList.get(i)/15);
            int yBroken = 48 + row * 32;
            int column = (int) app.loadConfig.brokenWallList.get(i) % 15;
            int xBroken = column * 32;
            
            for (int t = 0; t < location.get(0).size(); t ++){
                if (xBroken == (int) location.get(0).get(t) && yBroken == (int) location.get(1).get(t)){
                    this.removeBrokenWallList.add(app.loadConfig.brokenWallList.get(i));
                }
            }
        }
    }
    /**
     * This method is used to draw the image of the bomb (before the explosion) in the window of the app
     * There is a timer to calculate the period of bomb image loading
     * @param app The app used to load the program
     */
    public void drawBomb(App app){
        this.timer ++;
        this.sprite = this.sprite1;
        if (this.timer > 0.25 * app.FPS){
            int frameDirection = (int) Math.floor(app.count/15);
            if (frameDirection % 8 == 0) {
                this.x = this.x;
                this.sprite = this.sprite2;
            }else if (frameDirection % 8 == 1){
                this.x = this.x;
                this.sprite = this.sprite3;
            }else if (frameDirection % 8  == 2){
                this.x = this.x;
                this.sprite = this.sprite4;
            }else if (frameDirection % 8 == 3){
                this.x = this.x;
                this.sprite = this.sprite5;
            }else if (frameDirection % 8 == 4){
                this.x = this.x;
                this.sprite = this.sprite6;
            }else if (frameDirection % 8 == 5){
                this.x = this.x;
                this.sprite = this.sprite7;
            }else if (frameDirection % 8 == 6){
                this.x = this.x;
                this.sprite = this.sprite8;
            }else if (frameDirection % 8 == 7){
                this.x = this.x;
                this.sprite = this.sprite1;
            }
        }
    }
    /**
     * This method is used to draw the image when the bomb explodes in the window of the app
     * At the same time, the explosion range and the images caused by them (if blocked by a solid wall or destroyed by a broken wall) will be drawn at the same time.
     * @param app The app used to load the program
     * @param sprite Explosion image
     */
    public void draw(App app, PImage sprite){
        app.image(sprite, this.x, this.y + 16, 32, 32);

        int count = app.count - this.frameStart;

        if (count <= 150 && count > 120){
            app.image(this.explosionCentreSprite, this.x, this.y + 16, 32, 32);
            if (top2Wall == false){
                app.image(this.explosionVerticalSprite, this.x, this.y - 16, 32, 32);
            }
            if (top2Wall == false && top1Wall == false && topBrokenWall == false){
                app.image(this.explosionVerticalSprite, this.x, this.y - 16, 32, 32);
                app.image(this.explosionTopSprite, this.x, this.y - 48, 32, 32);
            }
            if (bottom2Wall == false){
                app.image(this.explosionVerticalSprite, this.x, this.y + 48, 32, 32);
            }
            if (bottom2Wall == false && bottom1Wall == false && bottomBrokenWall == false){
                app.image(this.explosionVerticalSprite, this.x, this.y + 48, 32, 32);
                app.image(this.explosionBottomSprite, this.x, this.y + 80, 32, 32);
            }
            if (left2Wall == false){
                app.image(this.explosionHorizontalSprite, this.x - 32, this.y + 16, 32, 32);
            }
            if (left2Wall == false && left1Wall == false && leftBrokenWall == false){
                app.image(this.explosionHorizontalSprite, this.x - 32, this.y + 16, 32, 32);
                app.image(this.explosionLeftSprite, this.x - 64, this.y + 16, 32, 32);
            }
            if (right2Wall == false){
                app.image(this.explosionHorizontalSprite, this.x + 32, this.y + 16, 32, 32);
            }
            if (right2Wall == false && right1Wall == false && rightBrokenWall == false){
                app.image(this.explosionHorizontalSprite, this.x + 32, this.y + 16, 32, 32);
                app.image(this.explosionRightSprite, this.x + 64, this.y + 16, 32, 32);
            }
        }else if (count > 150){
                app.loadConfig.bombList.remove(this);
                
                for (int i = 0; i < this.removeBrokenWallList.size(); i ++){
                    app.loadConfig.brokenWallList.remove(this.removeBrokenWallList.get(i));
                }
            }
    }
    /**
     * This method is used to return the x value of the current position of the bomb
     * @return x 
     */
    public int getX(){
        return this.x;
    }
    /**
     * This method is used to return the y value of the current position of the bomb
     * @return y 
     */
    public int getY(){
        return this.y;
    }
}