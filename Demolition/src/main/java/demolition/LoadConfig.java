package demolition;

import processing.core.*;
import processing.data.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class LoadConfig extends PApplet{
    /**
     * Images of the empty tile
     */
    public PImage emptySprite;
    /**
     * Images of the solid Wall
     */
    public PImage solidSprite;
    /**
     * Images of the broken Wall
     */
    public PImage brokenSprite;
    /**
     * Images of the goal tile
     */
    public PImage goalSprite;
    /**
     * Images of the UI (clock)
     */
    public PImage clockSprite;
    /**
     * Images of the UI (logo)
     */
    public PImage playerLogoSprite;
    /**
     * Images of the red enemies (down1)
     */
    public PImage down1RED;
    /**
     * Images of the red enemies (down2)
     */
    public PImage down2RED;
    /**
     * Images of the red enemies (down3)
     */
    public PImage down3RED;
    /**
     * Images of the red enemies (down4)
     */
    public PImage down4RED;
    /**
     * Images of the red enemies (up1)
     */
    public PImage up1RED;
    /**
     * Images of the red enemies (up2)
     */
    public PImage up2RED;
    /**
     * Images of the red enemies (up3)
     */
    public PImage up3RED;
    /**
     * Images of the red enemies (up4)
     */
    public PImage up4RED;
    /**
     * Images of the red enemies (left1)
     */
    public PImage left1RED;
    /**
     * Images of the red enemies (left2)
     */
    public PImage left2RED;
    /**
     * Images of the red enemies (left3)
     */
    public PImage left3RED;
    /**
     * Images of the red enemies (left4)
     */
    public PImage left4RED;
    /**
     * Images of the red enemies (right1)
     */
    public PImage right1RED;
    /**
     * Images of the red enemies (right2)
     */
    public PImage right2RED;
    /**
     * Images of the red enemies (right3)
     */
    public PImage right3RED;
    /**
     * Images of the red enemies (right4)
     */
    public PImage right4RED;
    /**
     * Images of the yellow enemies (down1)
     */
    public PImage down1YELLOW;
    /**
     * Images of the yellow enemies (down2)
     */
    public PImage down2YELLOW;
    /**
     * Images of the yellow enemies (down3)
     */
    public PImage down3YELLOW;
    /**
     * Images of the yellow enemies (down4)
     */
    public PImage down4YELLOW;
    /**
     * Images of the yellow enemies (up1)
     */
    public PImage up1YELLOW;
    /**
     * Images of the yellow enemies (up2)
     */
    public PImage up2YELLOW;
    /**
     * Images of the yellow enemies (up3)
     */
    public PImage up3YELLOW;
    /**
     * Images of the yellow enemies (up4)
     */
    public PImage up4YELLOW;
    /**
     * Images of the yellow enemies (left1)
     */
    public PImage left1YELLOW;
    /**
     * Images of the yellow enemies (left2)
     */
    public PImage left2YELLOW;
    /**
     * Images of the yellow enemies (left3)
     */
    public PImage left3YELLOW;
    /**
     * Images of the yellow enemies (left4)
     */
    public PImage left4YELLOW;
    /**
     * Images of the yellow enemies (right1)
     */
    public PImage right1YELLOW;
    /**
     * Images of the yellow enemies (right2)
     */
    public PImage right2YELLOW;
    /**
     * Images of the yellow enemies (right3)
     */
    public PImage right3YELLOW;
    /**
     * Images of the yellow enemies (right4)
     */
    public PImage right4YELLOW;

    /**
     * Images of the bomb
     */
    public PImage bombsprite;
    /**
     * Images of the bomb (1)
     */
    public PImage bombSprite1;
    /**
     * Images of the bomb (2)
     */
    public PImage bombSprite2;
    /**
    * Images of the bomb (3)
    */
    public PImage bombSprite3;
    /**
     * Images of the bomb (4)
     */
    public PImage bombSprite4;
    /**
     * Images of the bomb (5)
     */
    public PImage bombSprite5;
    /**
     * Images of the bomb (6)
     */
    public PImage bombSprite6;
    /**
     * Images of the bomb (7)
     */
    public PImage bombSprite7;
    /**
     * Images of the bomb (8)
     */
    public PImage bombSprite8;
    /**
     * Images of the explosion (centre)
     */
    public PImage explosionCentreSprite;
    /**
     * Images of the explosion (bottom)
     */
    public PImage explosionBottomSprite;
    /**
     * Images of the explosion (left)
     */
    public PImage explosionLeftSprite;
    /**
     * Images of the explosion (right)
     */
    public PImage explosionRightSprite;
    /**
     * Images of the explosion (top)
     */
    public PImage explosionTopSprite;
    /**
     * Images of the explosion (horizontal)
     */
    public PImage explosionHorizontalSprite;
    /**
     * Images of the explosion (vertical)
     */
    public PImage explosionVerticalSprite;
    /**
     * A list for storing the red enemies
     */
    public ArrayList<RedEnemies> redEnemiesList = new ArrayList<RedEnemies>(); 
    /**
     * A list for storing the yellow enemies
     */
    public ArrayList<YellowEnemies> yellowEnemiesList = new ArrayList<YellowEnemies>(); 
    /**
     * A list for storing the broken walls
     */
    public ArrayList<Integer> brokenWallList = new ArrayList<Integer>(); 
    /**
     * A list for storing the solid walls
     */
    public ArrayList<Integer> solidWallList = new ArrayList<Integer>(); 
    /**
     * A list for storing the goal tiles
     */
    public ArrayList<Integer> goalTileList = new ArrayList<Integer>(); 
    /**
     * A list for storing the bombs
     */
    public ArrayList<Bomb> bombList = new ArrayList<Bomb>(); 
    /**
     * Used to calculate time
     */
    public int time = 0;
    /**
     * The level of the current game
     */
    public int level = 1;
    /**
     * The current number of lives of bombGuy
     */
    public int lives;
    /**
     * Used to judge whether the game is over, the default is false. 
     * If lives or time is equal to 0, it will become true
     */
    public boolean gameOver = false;
    /**
     * Used to judge whether the game is won, the default is false. 
     * When bombGuy reaches the goal point of the final level, it will become true
     */
    public boolean gameWin = false;
    /**
     * When bombGuy is killed, the value will become true, the default is false
     */
    public boolean loseLives = false;
    /**
     * Used to determine whether to load the next level, the default is false. 
     * If true, load the next level
     */
    public boolean nextLevel = false;
    /**
     * Constructor for the LoadConfig class
     */
    public LoadConfig(){
    }
    /**
     * This method is used to Load the information of the config file
     * It will store the data read in the config file in the corresponding list
     * @param path The path of the config file
     * @return configList
     */
    public HashMap<String, String> loadConfig(String path){
        //Creat a HashMap to store the information of config file
        HashMap<String, String> configList = new HashMap<String, String>();

        File jsonFile = new File(path);
        JSONObject config = loadJSONObject(jsonFile);
        Integer lives = config.getInt("lives");
        JSONArray levelsArray = config.getJSONArray("levels");
        JSONObject level1 = levelsArray.getJSONObject(0);
        String level1Path = level1.getString("path");
        Integer level1Time = level1.getInt("time");
        JSONObject level2 = levelsArray.getJSONObject(1);
        String level2Path = level2.getString("path");
        Integer level2Time = level2.getInt("time");

        //Add the information
        configList.put("lives", String.valueOf(lives));
        configList.put("path1", level1Path);
        configList.put("path2", level2Path);
        configList.put("time1", String.valueOf(level1Time));
        configList.put("time2", String.valueOf(level2Time));

        //About the time and the levels
        //Determine which level it is
        if(this.level == 1){
            time = Integer.parseInt(configList.get("time1"));
        }else if(this.level == 2){
            time = Integer.parseInt(configList.get("time2"));
        }
        this.lives = Integer.parseInt(configList.get("lives"));

        return configList;
    }
    /**
     * This method is used to Load the map according to the config file path
     * It will store the information of the map into a list 
     * @param path The path of the config file
     * @param configList The hashmap which contains the information of the config file
     * @return mapList or null
     */
    public ArrayList<String> loadMap(String path, HashMap<String, String> configList){
        //Read the information in the path
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));

            //Store the type of map
            String mapLine;
            ArrayList<String> mapList = new ArrayList<String>();
            while((mapLine = br.readLine()) != null){
                String[] mapLineArray = mapLine.split("");
                for(String type: mapLineArray){
                    mapList.add(type);
                }
            }
            return mapList;
        }catch(Exception e){
            return null;
        }
    }
    /**
     * This method is used to Find the location of the bombGuy
     * @param mapList A list of map-related data
     * @param bombGuy The bombGuy
     */
    public void locationBombGuy(ArrayList<String> mapList, BombGuy bombGuy){
        int bombGuyIndex = mapList.indexOf("P");
        //System.out.println(bombGuyIndex);
        int row = (int) Math.floor(bombGuyIndex/15);
        bombGuy.y = 48 + row * 32;
        int column = (int) bombGuyIndex % 15;
        bombGuy.x = column * 32;
    }
    /**
     * This method is used to Find the location of the RED Enemies
     * @param mapList A list of map-related data
     * @return indexListRED
     */
    public ArrayList<Integer> enemiesIndexRED(ArrayList<String> mapList){
        ArrayList<Integer> indexListRED = new ArrayList<Integer>();
        for (int i = 0; i < mapList.size(); i ++){
            if (mapList.get(i).equals("R")){
                indexListRED.add(i);
            }
        }
        return indexListRED;
    }
    /**
     * This method is used to Find the location of the YELLOW Enemies
     * @param mapList A list of map-related data
     * @return indexListYELLOW
     */
    public ArrayList<Integer> enemiesIndexYELLOW(ArrayList<String> mapList){
        ArrayList<Integer> indexListYELLOW = new ArrayList<Integer>();
        for (int i = 0; i < mapList.size(); i ++){
            if (mapList.get(i).equals("Y")){
                indexListYELLOW.add(i);
            }
        }
        return indexListYELLOW;
    }
    /**
     * This method is used to Draw the map according to the mapList
     * @param mapList A list of map-related data
     * @param app The app used to load the program
     */
    public void drawMap(ArrayList<String> mapList, App app){
        int x;
        int y;
        int row;
        int column;
        PImage sprite;
        for (int i = 0; i < mapList.size(); i ++){
            row = (int) Math.floor(i/15);
            y = 64 + row * 32;
            column = (int) i % 15;
            x = column * 32;

            //Sort the type of the Map
            switch (mapList.get(i)) {
                case "W":
                    sprite = this.solidSprite;
                    break;
                case "G":
                    sprite = this.goalSprite;
                    break;
                default:
                    sprite = this.emptySprite;
                    break;
            }
            //Draw out the Map
            app.image(sprite, x, y, 32, 32);
        }
        for (int u = 0; u < brokenWallList.size(); u ++){
            row = (int) Math.floor(brokenWallList.get(u)/15);
            y = 64 + row * 32;
            column = (int) brokenWallList.get(u) % 15;
            x = column * 32;
            sprite = this.brokenSprite;
            //Draw out the Map
            app.image(sprite, x, y, 32, 32);
        }
    }
    /**
     * This method is used to Draw the Win or the Game Over Screen
     * @param stateBoolean Used to judge the current state of the game (victory or defeat)
     * @param app The app used to load the program
     */
    public void drawScreen(boolean stateBoolean, App app){
        String state = String.valueOf(stateBoolean);

        //Store the different char array according to the state
        char[] winArray = {'Y', 'O', 'U', ' ', 'W', 'I', 'N'};
        char[] loseArray = {'G', 'A', 'M', 'E',' ', 'O', 'V', 'E', 'R'};

        PFont font = app.createFont("src/main/resources/PressStart2P-Regular.ttf", 180, true);
        app.textFont(font, 32);

        //Determine which Screen should output
        switch (state) {
            case "true":
                app.text(winArray, 0, 7, 120, 240);
                break;
            case "false":
            app.text(loseArray, 0, 9, 96, 240);
            break;
        }
        app.fill(0,0,0);
    }
    /**
     * This method is used to Draw the two main UI elements
     * @param app The app used to load the program
     * @param level The current level of the game
     */
    public void drawUI(App app, int level){
        if (loseLives){
            if (app.count % 60 == 0){
                lives -= 1;
                loseLives = false;
            }
        }
        if (lives == 0){
            gameOver = true;
        }else if (app.count % 60 == 0){
            time -= 1;
        }
        String timeString = String.valueOf(time);

        //Draw the Sprite
        app.image(this.clockSprite, 256, 16, 32, 32);
        app.image(this.playerLogoSprite, 128, 16, 32, 32);

        //Draw the text
        PFont font = app.createFont("src/main/resources/PressStart2P-Regular.ttf", 180, true);
        app.textFont(font, 22);
        app.text(lives, 170, 46);
        app.text(timeString, 298, 46);
        app.fill(0,0,0);
    }
    /**
     * This method creates a red enemy instance based on the data in the red enemy list
     * @param app The app used to load the program
     */
    public void createEnemiesRED(App app){
        ArrayList<Integer> indexListRED = app.indexListRED;
        for (int i = 0; i < indexListRED.size(); i++){
            int row = (int) Math.floor((indexListRED.get(i))/15);
            int y = 48 + row * 32;
            int column = (int) (indexListRED.get(i)) % 15;
            int x = column * 32;
            RedEnemies redEnemy = new RedEnemies(x, y);
            this.redEnemiesList.add(redEnemy);
        }
    }
    /**
     * This method creates a red enemy instance based on the data in the yellow enemy list
     * @param app The app used to load the program
     */
    public void createEnemiesYELLOW(App app){
        ArrayList<Integer> indexListYELLOW = app.indexListYELLOW;
        for (int i = 0; i < indexListYELLOW.size(); i++){
            int row = (int) Math.floor((indexListYELLOW.get(i))/15);
            int y = 48 + row * 32;
            int column = (int) (indexListYELLOW.get(i)) % 15;
            int x = column * 32;
            YellowEnemies yellowEnemy = new YellowEnemies(x, y);
            this.yellowEnemiesList.add(yellowEnemy);
        }
    }
    /**
     * This method loads the location of the broken wall according to the map information
     * And add its index (position) to the broken wall list
     * @param app The app used to load the program
     */
    public void loadBrokenWall(App app){
        for (int i = 0; i < app.mapList.size(); i ++){
            if (app.mapList.get(i).equals("B")){
                brokenWallList.add(i);
            }
        }
    }
    /**
     * This method loads the location of the solid wall according to the map information
     * And add its index (position) to the solid wall list
     * @param app The app used to load the program
     */
    public void loadSolidWall(App app){
        for (int i = 0; i < app.mapList.size(); i ++){
            if (app.mapList.get(i).equals("W")){
                solidWallList.add(i);
            }
        }
    }
    /**
     * This method loads the location of the goal tile according to the map information
     * And add its index (position) to the goal tile list
     * @param app The app used to load the program
     */
    public void loadGoalTile(App app){
        for (int i = 0; i < app.mapList.size(); i ++){
            if (app.mapList.get(i).equals("G")){
                goalTileList.add(i);
            }
        }
    }
    /**
     * This method loads all the relevant images of the bomb in the setup method of the app
     * @param app The app used to load the program
     */
    public void setSpriteBomb(App app){
        this.bombSprite1 = app.loadImage("src/main/resources/bomb/bomb1.png");
        this.bombSprite2 = app.loadImage("src/main/resources/bomb/bomb2.png");
        this.bombSprite3 = app.loadImage("src/main/resources/bomb/bomb3.png");
        this.bombSprite4 = app.loadImage("src/main/resources/bomb/bomb4.png");
        this.bombSprite5 = app.loadImage("src/main/resources/bomb/bomb5.png");
        this.bombSprite6 = app.loadImage("src/main/resources/bomb/bomb6.png");
        this.bombSprite7 = app.loadImage("src/main/resources/bomb/bomb7.png");
        this.bombSprite8 = app.loadImage("src/main/resources/bomb/bomb8.png");
        this.bombSprite8 = app.loadImage("src/main/resources/bomb/bomb8.png");

        this.explosionCentreSprite = app.loadImage("src/main/resources/explosion/centre.png");
        this.explosionBottomSprite = app.loadImage("src/main/resources/explosion/end_bottom.png");
        this.explosionLeftSprite = app.loadImage("src/main/resources/explosion/end_left.png");
        this.explosionRightSprite = app.loadImage("src/main/resources/explosion/end_right.png");
        this.explosionTopSprite = app.loadImage("src/main/resources/explosion/end_top.png");
        this.explosionHorizontalSprite = app.loadImage("src/main/resources/explosion/horizontal.png");
        this.explosionVerticalSprite = app.loadImage("src/main/resources/explosion/vertical.png");
    }
    /**
     * This method loads all the relevant images of the enemies in the setup method of the app
     * @param app The app used to load the program
     */
    public void setSpriteEnemies(App app){
        //RED
        this.down1RED = app.loadImage("src/main/resources/red_enemy/red_down1.png");
        this.down2RED = app.loadImage("src/main/resources/red_enemy/red_down2.png");
        this.down3RED = app.loadImage("src/main/resources/red_enemy/red_down3.png");
        this.down4RED = app.loadImage("src/main/resources/red_enemy/red_down4.png");

        this.up1RED = app.loadImage("src/main/resources/red_enemy/red_up1.png");
        this.up2RED = app.loadImage("src/main/resources/red_enemy/red_up2.png");
        this.up3RED = app.loadImage("src/main/resources/red_enemy/red_up3.png");
        this.up4RED = app.loadImage("src/main/resources/red_enemy/red_up4.png");
    
        this.left1RED = app.loadImage("src/main/resources/red_enemy/red_left1.png");
        this.left2RED = app.loadImage("src/main/resources/red_enemy/red_left2.png");
        this.left3RED = app.loadImage("src/main/resources/red_enemy/red_left3.png");
        this.left4RED = app.loadImage("src/main/resources/red_enemy/red_left4.png");
    
        this.right1RED = app.loadImage("src/main/resources/red_enemy/red_right1.png");
        this.right2RED = app.loadImage("src/main/resources/red_enemy/red_right2.png");
        this.right3RED = app.loadImage("src/main/resources/red_enemy/red_right3.png");
        this.right4RED = app.loadImage("src/main/resources/red_enemy/red_right4.png");


        //YELLOW
        this.down1YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.down2YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
        this.down3YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
        this.down4YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");

        this.up1YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
        this.up2YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
        this.up3YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
        this.up4YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");
    
        this.left1YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
        this.left2YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
        this.left3YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
        this.left4YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");
    
        this.right1YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
        this.right2YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
        this.right3YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
        this.right4YELLOW = app.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");
    }
    /**
     * This method loads all the relevant images of the bombGuy and the map in the setup method of the app
     * @param app The app used to load the program
     */
    public void setSpriteBasic(App app){
        app.loadConfig.emptySprite = app.loadImage("src/main/resources/empty/empty.png");
        app.loadConfig.solidSprite = app.loadImage("src/main/resources/wall/solid.png");
        app.loadConfig.brokenSprite = app.loadImage("src/main/resources/broken/broken.png");
        app.loadConfig.goalSprite = app.loadImage("src/main/resources/goal/goal.png");
        app.loadConfig.clockSprite = app.loadImage("src/main/resources/icons/clock.png");
        app.loadConfig.playerLogoSprite = app.loadImage("src/main/resources/icons/player.png");
        app.bombGuy.spriteDown1 = app.loadImage("src/main/resources/player/player1.png");
        app.bombGuy.spriteDown2 = app.loadImage("src/main/resources/player/player2.png");
        app.bombGuy.spriteDown3 = app.loadImage("src/main/resources/player/player3.png");
        app.bombGuy.spriteDown4 = app.loadImage("src/main/resources/player/player4.png");
        app.bombGuy.spriteUp1 = app.loadImage("src/main/resources/player/player_up1.png");
        app.bombGuy.spriteUp2 = app.loadImage("src/main/resources/player/player_up2.png");
        app.bombGuy.spriteUp3 = app.loadImage("src/main/resources/player/player_up3.png");
        app.bombGuy.spriteUp4 = app.loadImage("src/main/resources/player/player_up4.png");
        app.bombGuy.spriteRight1 = app.loadImage("src/main/resources/player/player_right1.png");
        app.bombGuy.spriteRight2 = app.loadImage("src/main/resources/player/player_right2.png");
        app.bombGuy.spriteRight3 = app.loadImage("src/main/resources/player/player_right3.png");
        app.bombGuy.spriteRight4 = app.loadImage("src/main/resources/player/player_right4.png");
        app.bombGuy.spriteLeft1 = app.loadImage("src/main/resources/player/player_left1.png");
        app.bombGuy.spriteLeft2 = app.loadImage("src/main/resources/player/player_left2.png");
        app.bombGuy.spriteLeft3 = app.loadImage("src/main/resources/player/player_left3.png");
        app.bombGuy.spriteLeft4 = app.loadImage("src/main/resources/player/player_left4.png");
    }
    /**
     * This method generates bombs based on the operations performed by bombGuy (place bombs)
     * The generated bomb will be stored in the bomb list
     * @param app The app used to load the program
     */
    public void createBombList(App app){
        if (app.bombGuy.putBomb == true){
            Bomb bomb = new Bomb(app.bombGuy.getX(), app.bombGuy.getY());
            this.bombList.add(bomb);
            int frameStart = app.count;
            bomb.frameStart = frameStart;
            app.bombGuy.putBomb = false;
        }
    }
    /**
     * This method is used to clear everything that was previously loaded
     * Used to reset/load level or to achieve victory/failure conditions
     * @param app The app used to load the program
     */
    public void clearInformation(App app){
        this.redEnemiesList.clear();
        this.yellowEnemiesList.clear();
        this.brokenWallList.clear();
        this.solidWallList.clear();
        this.goalTileList.clear();
        this.bombList.clear();
    }
    /**
     * This method is used to determine whether the game has reached the condition of failure
     * When the life of bombGuy is equal to 0 or the time is equal to 0, the game fails
     * @param app The app used to load the program
     */
    public void gameOver(App app){
        if (this.lives == 0 || this.time <= 0){
            app.loadConfig.clearInformation(app);
            this.gameOver = true;
            this.clearInformation(app);
            app.background(239, 129, 0);
            this.drawScreen(false, app);
        }else if (this.loseLives == true){
            app.reset = true;
        }
        if (app.loadConfig.gameOver == false){
            app.loadConfig.drawUI(app, 1);
        }
    }
    /**
     * This method is used to load level1 (including the initial time of the game) or reset level1
     * @param app The app used to load the program
     */
    public void loadLevel1(App app){
        if (app.loadConfig.level == 1 && app.start == true){
            app.bombGuy.direction(app);
            //load config file
            app.mapList = app.loadConfig.loadMap(app.configList.get("path1"), app.configList);

            //BombGuy
            app.loadConfig.locationBombGuy(app.mapList, app.bombGuy);

            //Enemies
            app.indexListRED = app.loadConfig.enemiesIndexRED(app.mapList);
            app.indexListYELLOW = app.loadConfig.enemiesIndexYELLOW(app.mapList);
            app.loadConfig.createEnemiesRED(app);
            app.loadConfig.createEnemiesYELLOW(app);
            app.loadConfig.loadBrokenWall(app);
            app.loadConfig.loadSolidWall(app);
            app.loadConfig.loadGoalTile(app);
        }else if (app.reset == true && app.loadConfig.level == 1){
            app.bombGuy.direction(app);
            //Clear the information of the level 1
            app.loadConfig.clearInformation(app);

            //load config file
            app.mapList = app.loadConfig.loadMap(app.configList.get("path1"), app.configList);

            //BombGuy
            app.loadConfig.locationBombGuy(app.mapList, app.bombGuy);

            //Enemies
            app.indexListRED = app.loadConfig.enemiesIndexRED(app.mapList);
            app.indexListYELLOW = app.loadConfig.enemiesIndexYELLOW(app.mapList);
            app.loadConfig.createEnemiesRED(app);
            app.loadConfig.createEnemiesYELLOW(app);
            app.loadConfig.loadBrokenWall(app);
            app.loadConfig.loadSolidWall(app);
            app.loadConfig.loadGoalTile(app);
            app.reset = false;
        }
    }
    /**
     * This method is used to load level2 (when bombGuy reaches the second level) or reset level2
     * @param app The app used to load the program
     */
    public void loadLevel2(App app){
        if (app.loadConfig.nextLevel == true){
            app.loadConfig.time = Integer.parseInt(app.configList.get("time2"));
            app.bombGuy.direction(app);
            //Clear the information of the level 1
            app.loadConfig.clearInformation(app);

            //load config file
            app.mapList = app.loadConfig.loadMap(app.configList.get("path2"), app.configList);

            //BombGuy
            app.loadConfig.locationBombGuy(app.mapList, app.bombGuy);

            //Enemies
            app.indexListRED = app.loadConfig.enemiesIndexRED(app.mapList);
            app.indexListYELLOW = app.loadConfig.enemiesIndexYELLOW(app.mapList);
            app.loadConfig.createEnemiesRED(app);
            app.loadConfig.createEnemiesYELLOW(app);
            app.loadConfig.loadBrokenWall(app);
            app.loadConfig.loadSolidWall(app);
            app.loadConfig.loadGoalTile(app);
            app.loadConfig.nextLevel = false;
        }else if (app.reset == true && app.loadConfig.level == 2){
            app.bombGuy.direction(app);
            //Clear the information of the level 1
            app.loadConfig.clearInformation(app);

            //load config file
            app.mapList = app.loadConfig.loadMap(app.configList.get("path1"), app.configList);

            //BombGuy
            app.loadConfig.locationBombGuy(app.mapList, app.bombGuy);

            //Enemies
            app.indexListRED = app.loadConfig.enemiesIndexRED(app.mapList);
            app.indexListYELLOW = app.loadConfig.enemiesIndexYELLOW(app.mapList);
            app.loadConfig.createEnemiesRED(app);
            app.loadConfig.createEnemiesYELLOW(app);
            app.loadConfig.loadBrokenWall(app);
            app.loadConfig.loadSolidWall(app);
            app.loadConfig.loadGoalTile(app);
            app.reset = false;
        }
    }
    /**
     * This method is used to determine whether the game has reached the victory conditions
     * If it reaches, draw the victory image in the app window
     * @param app The app used to load the program
     */
    public void loadGameWin(App app){
        if (app.loadConfig.gameWin == true){
            app.loadConfig.clearInformation(app);
            app.background(239, 129, 0);
            app.loadConfig.drawScreen(true, app);
        }
    }
    /**
     * This method draws all the basic information obtained from the current level (including bombGuy, enemies, bombs, maps, etc.)
     * @param app The app used to load the program
     */
    public void drawAll(App app){
        //--------boomguy
        if (app.loadConfig.gameWin != true && app.loadConfig.gameOver != true){
            app.bombGuy.canMove(app);
            app.bombGuy.tick(app);
            app.bombGuy.direction(app);
            app.bombGuy.drawGuy(app);
            app.bombGuy.draw(app, app.bombGuy.sprite);
            app.bombGuy.reachGoal(app);
        }

        //--------EnemiesRED
        for (int i = 0; i < app.loadConfig.redEnemiesList.size(); i++){
            RedEnemies redEnemy = app.loadConfig.redEnemiesList.get(i);
            redEnemy.direction(app);
            redEnemy.drawGuy(app);
            redEnemy.draw(app, redEnemy.sprite);
            redEnemy.enemiesCanMove(app);
            redEnemy.tick(app);
            redEnemy.killBombGuy(app);
            redEnemy.beKilled(app);
            redEnemy.removeEnemy(app);
        }

        //--------EnemiesYELLOW
        for (int u = 0; u < app.loadConfig.yellowEnemiesList.size(); u++){
            YellowEnemies yellowEnemy = app.loadConfig.yellowEnemiesList.get(u);
            yellowEnemy.direction(app);
            yellowEnemy.drawGuy(app);
            yellowEnemy.draw(app, yellowEnemy.sprite);
            yellowEnemy.enemiesCanMove(app);
            yellowEnemy.tick(app);
            yellowEnemy.killBombGuy(app);
            yellowEnemy.beKilled(app);
            yellowEnemy.removeEnemy(app);
        }

        ////--------Bombs
        for (int p = 0; p < app.loadConfig.bombList.size(); p++){
            Bomb bomb = app.loadConfig.bombList.get(p);
            bomb.setSprite(app);
            bomb.haveWall(app);
            bomb.drawBomb(app);
            bomb.rangeExplosion(app);
            bomb.draw(app, bomb.sprite);
        }
    }
    /**
     * This method applies the press button value to the action aspect of bombGuy
     * @param app The app used to load the program
     */
    public void keyPressed(App app) {
        // Left: 37
        // Up: 38
        // Right: 39
        // Down: 40
        if (app.keyCode == 37 && app.canMove == true){
            app.bombGuy.moveLeft = true;
            app.canMove = false;
        }else if (app.keyCode == 39 && app.canMove == true){
            app.bombGuy.moveRight = true;
            app.canMove = false;
        }else if (app.keyCode == 40 && app.canMove == true){
            app.bombGuy.moveDown = true;
            app.canMove = false;
        }else if (app.keyCode == 38 && app.canMove == true){
            app.bombGuy.moveUp = true;
            app.canMove = false;
        }else if (app.keyCode == 32){
            app.bombGuy.putBomb = true;
            app.loadConfig.createBombList(app);
        }
    }
    /**
     * This method is used to draw in the draw method of the app
     * including the loading level and drawing basic information of the image
     * @param app The app used to load the program
     */
    public void drawOnApp(App app) {
        app.count ++;
        app.background(239, 129, 0);
        app.loadConfig.drawMap(app.mapList, app);
        app.loadConfig.gameOver(app);
        app.loadConfig.loadLevel1(app);
        app.loadConfig.loadLevel2(app);
        app.loadConfig.loadGameWin(app);
        app.loadConfig.drawAll(app);
    }
    /**
     * This method is used to set the config file that needs to be loaded
     * @param path The path of the config file
     * @param app The app used to load the program
     */
    public void setConfig(String path, App app){
        app.reset = true;
        app.path = path;
        app.configList = app.loadConfig.loadConfig(app.path);
        app.loadConfig.loadLevel1(app);
        app.reset = false;
    }
    /**
     * This method is used to set the frame rate of the app and load all images
     * @param app The app used to load the program
     */
    public void setupOnApp(App app) {
        app.frameRate(app.FPS);
        //Load images during setup
        app.loadConfig.setSpriteBasic(app);
        app.loadConfig.setSpriteEnemies(app);
        app.loadConfig.setSpriteBomb(app);
    }
}