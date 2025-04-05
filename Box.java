import java.util.*;
import java.io.*;
import java.math.*;

public class Team<T, Y> {
   
    private T playerName;
    private E playerAge;
    public LinkedList<Player> playerDetails = new LinkedList<Player>();
 
    public boolean addPlayer(T playerName, E playerAge) {
        player = new Player(playerName, playerAge);

        if (player != null && playerDetails.contains(player) == false){
            playerDetails.add(player);
            return true;
        }else{
            return false;
        }
    }

    public void playerList(){
        for (Player player : playerDetails){
            System.out.print(player.playerName);
            System.out.print(" --> ");
            System.out.println(player.playerAge);
        }
        
    }

class Player{
    T playerName;
    E playerAge;
    
    public Player(){
    }

    public Player(T playerName, E playerAge){
        this.playerName = playerName;
        this.playerAge = playerAge;
    }
}