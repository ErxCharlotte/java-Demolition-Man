package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class YellowEnemies extends Enemies{
    /**
     * Constructor for a YellowEnemy
     */
    public YellowEnemies(){
    }
    /**
     * Constructor for a YellowEnemy
     * @param x the location of the YellowEnemy (X value)  
     * @param y the location of the YellowEnemy (Y value)  
     */
    public YellowEnemies(int x, int y){
        super(x, y);
    }
    /**
     * This method is used to adjust the moving direction of the yellow enemy when encountering a blocker
     * Yellow enemies will turn clockwise when encountering obstacles
     */
    public void turnDirection(){
        if (this.enemyMoveDown){
            this.enemyMoveLeft = true;
            this.enemyMoveDown = false;
        }else if (this.enemyMoveLeft){
            this.enemyMoveUp = true;
            this.enemyMoveLeft = false;
        }else if (this.enemyMoveUp){
            this.enemyMoveRight = true;
            this.enemyMoveUp = false;
        }else if (this.enemyMoveRight){
            this.enemyMoveDown = true;
            this.enemyMoveRight = false;
        }
    }
    /**
     * The method determines whether can move in a straight line
     * @param app The app used to load the program
     */
    public void enemiesCanMove(App app){
        ArrayList<String> mapList = app.mapList;
        int nextX = this.getX();
        int nextY = this.getY();
        
        if (this.enemyMoveLeft){
            nextX = this.getX() - 32;
        }else if (this.enemyMoveRight){
            nextX = this.getX() + 32;
        }else if (this.enemyMoveDown){
            nextY = this.getY() + 32;
        }else if (this.enemyMoveUp){
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
                    if (app.count % 60 == 0){
                        this.turnDirection();
                        this.enemiesCanMove(app);
                    }
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
                if (app.count % 60 == 0){
                    this.turnDirection();
                    this.enemiesCanMove(app);
                }
                
            }
        }
    }
    /**
     * This method sets the yellow enemy's image displayed in the app according to this yellow enemy's current facing state
     * The default facing is downward
     * @param app The app used to load the program
     */
    public void direction(App app){
        //Default
        if (app.count == 1){
            this.sprite1 = app.loadConfig.down1YELLOW;
            this.sprite2 = app.loadConfig.down2YELLOW;
            this.sprite3 = app.loadConfig.down3YELLOW;
            this.sprite4 = app.loadConfig.down4YELLOW;
        }

        //Determine the direction of the bombGuy
        if (this.enemyMoveDown == true){
            this.sprite1 = app.loadConfig.down1YELLOW;
            this.sprite2 = app.loadConfig.down2YELLOW;
            this.sprite3 = app.loadConfig.down3YELLOW;
            this.sprite4 = app.loadConfig.down4YELLOW;
            //this.enemyMoveDown = false;
        }else if (this.enemyMoveUp == true){
            this.sprite1 = app.loadConfig.up1YELLOW;
            this.sprite2 = app.loadConfig.up2YELLOW;
            this.sprite3 = app.loadConfig.up3YELLOW;
            this.sprite4 = app.loadConfig.up4YELLOW;
            //this.enemyMoveUp = false;
        }else if (this.enemyMoveLeft == true){
            this.sprite1 = app.loadConfig.left1YELLOW;
            this.sprite2 = app.loadConfig.left2YELLOW;
            this.sprite3 = app.loadConfig.left3YELLOW;
            this.sprite4 = app.loadConfig.left4YELLOW;
            //this.enemyMoveLeft = false;
        }else if (this.enemyMoveRight == true){
            this.sprite1 = app.loadConfig.right1YELLOW;
            this.sprite2 = app.loadConfig.right2YELLOW;
            this.sprite3 = app.loadConfig.right3YELLOW;
            this.sprite4 = app.loadConfig.right4YELLOW;
            //this.enemyMoveRight = false;
        }
    }
    /**
     * This method performs the corresponding operation according to the death state of the yellow enemy
     * If its death status is true, remove it from the list of yellow enemies
     * @param app The app used to load the program
     */
    public void removeEnemy(App app){
        if (this.beKilled){
            app.loadConfig.yellowEnemiesList.remove(this);
        }
    }   
}