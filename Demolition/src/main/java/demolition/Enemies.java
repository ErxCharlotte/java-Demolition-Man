package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public abstract class Enemies extends Guy{
    /**
     * The enemy's current face (down), the default is true
     */
    public boolean enemyMoveDown = true;
    /**
     * The enemy's current face (up), the default is false
     */
    public boolean enemyMoveUp = false;
    /**
    * The enemy's current face (left), the default is false
    */
    public boolean enemyMoveLeft = false;
    /**
     * The enemy's current face (right), the default is false
     */
    public boolean enemyMoveRight = false;
    /**
     * Whether the enemy can move (down), the default is true
     */
    public boolean canMoveDown = true;
    /**
     * Whether the enemy can move (up), the default is false
     */
    public boolean canMoveUp = false;
    /**
    * Whether the enemy can move (left), the default is false
    */
    public boolean canMoveLeft = false;
    /**
     * Whether the enemy can move (right), the default is false
     */
    public boolean canMoveRight = false;
    /**
     * Whether the enemy can move (whole), the default is true
     */
    public boolean canMove = true;
    /**
     * The x value of the enemy's next position (under the assumption that there is no obstruction)
     */
    public int nextX;
    /**
     * The y value of the enemy's next position (under the assumption that there is no obstruction)
     */
    public int nextY;
    /**
     * The survival status of the enemy, if the value is true, the enemy is killed. The default is false
     */
    public boolean beKilled = false;
    /**
     * Constructor for a enemy
     */
    public Enemies(){
    }
    /**
     * Constructor for a enemy, requires the location of the bombGuy (X and Y value)
     * @param x the location of the enemy (X value)  
     * @param y the location of the enemy (Y value)  
     */
    public Enemies(int x, int y){
        super(x, y);
    }
    /**
     * The method determines whether can move in a straight line
     * @param app The app used to load the program
     */
    public abstract void enemiesCanMove(App app);
    /**
     * This method sets the enemy's image displayed in the app according to the enemy's current facing state
     * The default facing is downward
     * @param app The app used to load the program
     */
    public abstract void direction(App app);
    /**
     * This method is used to remove enemies
     * When an enemy is killed, remove the enemy from the enemy list
     * @param app The app used to load the program
     */
    public abstract void removeEnemy(App app);
    /**
     * This method is used for enemies to move over time
     * Different types of enemies have different movement patterns
     * Only when it is determined that the enemy can move (that is, there are no obstacles)
     * @param app The app used to load the program
     */
    public void tick(App app){
        if (app.count % 60 == 0){
            if (this.canMove){
                if (this.enemyMoveDown == true){
                    this.y += 32;
                }else if (this.enemyMoveUp == true){
                    this.y -= 32;
                }else if (this.enemyMoveRight == true){
                    this.x += 32;
                }else if (this.enemyMoveLeft == true){
                    this.x -= 32;
                }
            }
        }
    }
    /**
     * This method is used to determine whether the enemy has collided with BombGuy (that is, at the same position as BombGuy)
     * If so, the enemy will kill bombGuy (bombGuy loses one life)
     * @param app The app used to load the program
     */
    public void killBombGuy(App app){
        BombGuy bombGuy = app.bombGuy;
        if (this.getX() == bombGuy.getX() && this.getY() == bombGuy.getY()){
            app.loadConfig.loseLives = true;
        }
    }
    /**
     * This method is used to determine whether the enemy is within the explosive range of the bomb
     * If it is, the enemy is killed (removed from the enemy list)
     * @param app The app used to load the program
     */
    public void beKilled(App app){
        ArrayList<Bomb> bombList = app.loadConfig.bombList;
        for (int i = 0; i < bombList.size(); i ++){
            Bomb bomb = bombList.get(i);
            
            int count = app.count - bombList.get(i).frameStart;
            if (count > 150){
                ArrayList<ArrayList> location = bomb.loadLocation(app);
                //System.out.println(location.get(0));
                //System.out.println(location.get(1));
                for (int u = 0; u < location.get(0).size(); u ++){
                        if (this.getX() == (int) location.get(0).get(u) && this.getY() == (int) location.get(1).get(u)){
                                beKilled = true;
                        }
                    }
            }
        }
    }
}

