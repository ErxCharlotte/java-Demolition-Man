package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
    Method method = new Method();
    
    @Test
    public void simpleTest() {
        App app = new App();
        assertEquals(480, App.HEIGHT);
    }

    //Test whether BombGuy can move when the button is kept pressed
    @Test
    public void basicTestMove() {
        App app = new App();
        method.loadBasic("./config.json", app);

        app.draw();
        //Move right once
        app.keyCode = 39;
        app.keyPressed();
        app.draw();
        app.isReleased(false);
        app.keyPressed();
        app.draw();
        assertEquals(64, app.bombGuy.getX());
        app.isReleased(true);
        app.keyPressed();
        app.draw();
        assertEquals(96, app.bombGuy.getX());
        //Down
        app.isReleased(true);
        app.keyCode = 40;
        app.keyPressed();
        app.draw();
        assertEquals(112, app.bombGuy.getY());
        //Up
        app.isReleased(true);
        app.keyCode = 38;
        app.keyPressed();
        app.draw();
        assertEquals(80, app.bombGuy.getY());
        //Left
        app.isReleased(true);
        app.keyCode = 37;
        app.keyPressed();
        app.draw();
        assertEquals(64, app.bombGuy.getX());
        //Up (Impossible to walk)
        app.isReleased(true);
        app.keyCode = 38;
        app.keyPressed();
        app.draw();
        assertEquals(80, app.bombGuy.getY());
    }

    //Detect whether Bomb can put correctly and explosion in 120 frames
    @Test
    public void basicTestBomb() {
        App app = new App();
        method.loadBasic("./config.json", app);

        app.keyCode = 32;
        app.keyPressed();
        app.isReleased(true);
        assertEquals(1, app.loadConfig.bombList.size());
        for (int frame = 0; frame < 151; frame ++){
            app.draw();
        }
        assertEquals(0, app.loadConfig.bombList.size());
    }

    //Test the result of the encounter between the enemy and BombGuy
    @Test
    public void basicTestMeetEnemy() {
        App app = new App();
        method.loadBasic("./config.json", app);

        this.method.move("right",app);
        app.draw();
        this.method.move("right",app);
        app.draw();
        this.method.move("down",app);
        app.draw();
        this.method.move("down",app);
        app.draw();
        this.method.move("down",app);
        app.draw();
        this.method.move("down",app);
        app.draw();
        for (int frame = 0; frame < 600; frame ++){
            app.draw();
        }
        assertEquals(2, app.loadConfig.lives);
    }

    //Test the result of the bomb's explosion will kill the Enemy
    @Test
    public void basicTestKillEnemy() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test1/configTest1.json", app);

        this.method.move("bomb",app);
        this.method.move("down",app);
        this.method.move("down",app);
        this.method.move("down",app);
        this.method.move("right",app);
        for (int frame = 0; frame < 300; frame ++){
            app.draw();
        }
        assertEquals(2, app.loadConfig.redEnemiesList.size());
    }

    //Test whether can Load the Level 2
    @Test
    public void basicTestLoadLevel2() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test2/configTest2.json", app);

        app.draw();
        for (int i = 0; i < 13; i ++){
            this.method.move("right",app);
        }
        for (int frame = 0; frame < 600; frame ++){
            app.draw();
        }
        assertEquals(2, app.loadConfig.level);
    }

    //Test the explosion range of the bomb when there is a solid wall around
    @Test
    public void basicTestExplosionSolidWall() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test3/configTest3.json", app);

        method.move("bomb", app);
        for (int frame = 0; frame < 61; frame ++){
            app.draw();
        }
        assertEquals(true, app.loadConfig.bombList.get(0).top2Wall);
        assertEquals(true, app.loadConfig.bombList.get(0).bottom2Wall);
        assertEquals(true, app.loadConfig.bombList.get(0).left2Wall);
        assertEquals(true, app.loadConfig.bombList.get(0).right2Wall);
    }

    //Test the explosion range of the bomb when there is a broken wall around
    @Test
    public void basicTestExplosionBrokenWall() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test7/configTest7.json", app);

        method.move("bomb", app);
        for (int frame = 0; frame < 61; frame ++){
            app.draw();
        }
        assertEquals(true, app.loadConfig.bombList.get(0).topBrokenWall);
        assertEquals(true, app.loadConfig.bombList.get(0).bottomBrokenWall);
        assertEquals(true, app.loadConfig.bombList.get(0).leftBrokenWall);
        assertEquals(true, app.loadConfig.bombList.get(0).rightBrokenWall);

        for (int frame = 0; frame < 100; frame ++){
            app.draw();
        }

    }

    //Test whether the bomb can destory the Broken Wall
    @Test
    public void basicTestExplosionALL() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test8/configTest8.json", app);

        method.move("bomb", app);
        for (int i=0; i < 7; i ++){
            method.move("right", app);
        }
        for (int frame = 0; frame < 161; frame ++){
            app.draw();
        }
        method.move("bomb", app);

        for (int i=0; i < 4; i ++){
            method.move("left", app);
        }
        for (int frame = 0; frame < 161; frame ++){
            app.draw();
        }
        assertEquals(1, app.loadConfig.brokenWallList.size());
    }

    //Test the conditions for win
    @Test
    public void basicTestGameWIN() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test4/configTest4.json", app);

        for (int i = 0; i < 15; i ++){
            this.method.move("right",app);
        }
        for (int frame = 0; frame < 700; frame ++){
            app.draw();
        }
        this.method.move("bomb",app);
        for (int frame = 0; frame < 200; frame ++){
            app.draw();
        }
        assertEquals(2, app.loadConfig.lives);
        for (int i = 0; i < 26; i ++){
            this.method.move("right",app);
        }
        for (int frame = 0; frame < 700; frame ++){
            app.draw();
        }
        assertEquals(true, app.loadConfig.gameWin);
    }

    //Test the conditions for lose (timeout)
    @Test
    public void basicTestGameLOSE1() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test5/configTest5.json", app);
        for (int frame = 0; frame < 700; frame ++){
            app.draw();
        }
        assertEquals(true, app.loadConfig.gameOver);
    }

    //Test the conditions for lose (0 lives)
    @Test
    public void basicTestGameLOSE2() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test5/configTest5.json", app);
        this.method.move("bomb",app);
        for (int frame = 0; frame < 200; frame ++){
            app.draw();
        }
        assertEquals(true, app.loadConfig.gameOver);
    }

    //Test whether the enemy's actions are correct
    @Test
    public void basicTestActionEnemies() {
        App app = new App();
        method.loadBasic("./src/test/resources/Test6/configTest6.json", app);

        for (int frame = 0; frame < 1200; frame ++){
            app.draw();
        }
    }
}

class Method{
    public Method(){
    }

    public void loadBasic(String path, App app){
        app.noLoop();
        app.setConfig(path);
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
    }

    public void move(String type, App app){
        app.isReleased(true);
        switch (type) {
            case "right":
                app.keyCode = 39;
                break;
            case "left":
                app.keyCode = 37;
                break;
            case "up":
                app.keyCode = 38;
                break;    
            case "down":
                app.keyCode = 40;
                break;
            case "bomb":
                app.keyCode = 32;
                break;
        }
        app.keyPressed();
        app.draw();
    }

}