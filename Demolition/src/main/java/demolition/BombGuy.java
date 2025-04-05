package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class BombGuy extends Guy{
    /**
     * Record whether the bomber placed a bomb, the default is false
     */
    public boolean putBomb = false;
    /**
     * Constructor for a BombGuy
     */
    public BombGuy(){
    }
    /**
     * Constructor for a BombGuy, requires the location of the bombGuy (X and Y value)
     * @param x the location of the bombGuy (X value)  
     * @param y the location of the bombGuy (Y value)  
     */
    public BombGuy(int x, int y){
        super(x, y);
    }
    /**
     * This method is used to determine the current image of bombGuy
     * At the same time restrict the bomber's re-movement
     * @param app The app used to load the program
     */
    public void direction(App app){
        //Default
        if (app.count == 1){
            this.sprite1 = this.spriteDown1;
            this.sprite2 = this.spriteDown2;
            this.sprite3 = this.spriteDown3;
            this.sprite4 = this.spriteDown4;
        }

        //Determine the direction of the bombGuy
        if (app.reset == true){
            this.sprite1 = this.spriteDown1;
            this.sprite2 = this.spriteDown2;
            this.sprite3 = this.spriteDown3;
            this.sprite4 = this.spriteDown4;
            this.moveDown = false;
        }else if (this.moveDown == true){
            this.sprite1 = this.spriteDown1;
            this.sprite2 = this.spriteDown2;
            this.sprite3 = this.spriteDown3;
            this.sprite4 = this.spriteDown4;
            this.moveDown = false;
        }else if (this.moveUp == true){
            this.sprite1 = this.spriteUp1;
            this.sprite2 = this.spriteUp2;
            this.sprite3 = this.spriteUp3;
            this.sprite4 = this.spriteUp4;
            this.moveUp = false;
        }else if (this.moveLeft == true){
            this.sprite1 = this.spriteLeft1;
            this.sprite2 = this.spriteLeft2;
            this.sprite3 = this.spriteLeft3;
            this.sprite4 = this.spriteLeft4;
            this.moveLeft = false;
        }else if (this.moveRight == true){
            this.sprite1 = this.spriteRight1;
            this.sprite2 = this.spriteRight2;
            this.sprite3 = this.spriteRight3;
            this.sprite4 = this.spriteRight4;
            this.moveRight = false;
        }
    }
    /**
     * This method is used to determine the movement of bombGuy in each frame, and move according to the button pressed
     * At the same time, judge whether bombGuy can move
     * @param app The app used to load the program
     */
    public void tick(App app){
        if (this.canMove){
            if (this.moveLeft){
                this.x -= 32;
            }else if (this.moveRight){
                this.x += 32;
            }else if (this.moveDown){
                this.y += 32;
            }else if (this.moveUp){
                this.y -= 32;
            }
        }else{
            this.canMove = true;
        }
    }
    /**
     * This method is used to detect whether bombGuy has reached the target
     * If it reaches, load to the next level or declare the game victory according to the situation
     * @param app The app used to load the program
     */
    public void reachGoal(App app){
        for (int i = 0; i < app.loadConfig.goalTileList.size(); i ++){
            int row = (int) Math.floor(app.loadConfig.goalTileList.get(i)/15);
            int yGoal = 48 + row * 32;
            int column = (int) app.loadConfig.goalTileList.get(i) % 15;
            int xGoal = column * 32;
            if (this.getX() == xGoal && this.getY() == yGoal){
                if (app.loadConfig.level == 1){
                    app.loadConfig.level = 2;
                    app.loadConfig.nextLevel = true;
                }else{
                    app.loadConfig.gameWin = true;
                }
            }
        }
    }
}