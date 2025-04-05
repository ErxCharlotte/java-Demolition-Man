package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class RedEnemies extends Enemies{
    /**
     * Constructor for a RedEnemy
     */
    public RedEnemies(){
    }
    /**
     * Constructor for a RedEnemy
     * @param x the location of the RedEnemy (X value)  
     * @param y the location of the RedEnemy (Y value)  
     */
    public RedEnemies(int x, int y){
        super(x, y);
    }
    /**
     * This method is used to adjust the moving direction of the red enemy when encountering a blocker
     * Red enemies will turn randomly when encountering obstacles
     */
    public void turnDirection(){
        Random rd = new Random();
        int number = rd.nextInt(3);
        if (this.enemyMoveDown){
            switch (number) {
                case 0:
                    this.enemyMoveLeft = true;
                    break;
                case 1:
                    this.enemyMoveUp = true;
                    break;
                case 2:
                    this.enemyMoveRight = true;
                    break;
            }
            this.enemyMoveDown = false;
        }else if (this.enemyMoveLeft){
            switch (number) {
                case 0:
                    this.enemyMoveDown = true;
                    break;
                case 1:
                    this.enemyMoveUp = true;
                    break;
                case 2:
                    this.enemyMoveRight = true;
                    break;
            }
            this.enemyMoveLeft = false;
        }else if (this.enemyMoveUp){
            switch (number) {
                case 0:
                    this.enemyMoveLeft = true;
                    break;
                case 1:
                    this.enemyMoveDown = true;
                    break;
                case 2:
                    this.enemyMoveRight = true;
                    break;
            }
            this.enemyMoveUp = false;
        }else if (this.enemyMoveRight){
            switch (number) {
                case 0:
                    this.enemyMoveLeft = true;
                    break;
                case 1:
                    this.enemyMoveUp = true;
                    break;
                case 2:
                    this.enemyMoveDown = true;
                    break;
            }
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
     * This method sets the red enemy's image displayed in the app according to this red enemy's current facing state
     * The default facing is downward
     * @param app The app used to load the program
     */
    public void direction(App app){
        //Default
        if (app.count == 1){
            this.sprite1 = app.loadConfig.down1RED;
            this.sprite2 = app.loadConfig.down2RED;
            this.sprite3 = app.loadConfig.down3RED;
            this.sprite4 = app.loadConfig.down4RED;
        }

        //Determine the direction of the bombGuy
        if (this.enemyMoveDown == true){
            this.sprite1 = app.loadConfig.down1RED;
            this.sprite2 = app.loadConfig.down2RED;
            this.sprite3 = app.loadConfig.down3RED;
            this.sprite4 = app.loadConfig.down4RED;
        }else if (this.enemyMoveUp == true){
            this.sprite1 = app.loadConfig.up1RED;
            this.sprite2 = app.loadConfig.up2RED;
            this.sprite3 = app.loadConfig.up3RED;
            this.sprite4 = app.loadConfig.up4RED;
        }else if (this.enemyMoveLeft == true){
            this.sprite1 = app.loadConfig.left1RED;
            this.sprite2 = app.loadConfig.left2RED;
            this.sprite3 = app.loadConfig.left3RED;
            this.sprite4 = app.loadConfig.left4RED;
        }else if (this.enemyMoveRight == true){
            this.sprite1 = app.loadConfig.right1RED;
            this.sprite2 = app.loadConfig.right2RED;
            this.sprite3 = app.loadConfig.right3RED;
            this.sprite4 = app.loadConfig.right4RED;
        }
    }
    /**
     * This method performs the corresponding operation according to the death state of the red enemy
     * If its death status is true, remove it from the list of red enemies
     * @param app The app used to load the program
     */
    public void removeEnemy(App app){
        if (this.beKilled){
            app.loadConfig.redEnemiesList.remove(this);
        }
    }
}