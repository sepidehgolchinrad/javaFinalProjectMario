package game.sample.ball;

import game.sample.ball.objects.Player;
import game.sample.ball.objects.Wall;
import game.sample.ball.objects.enemy.Enemy;

import java.util.ArrayList;

public class Gravity implements Runnable {
    Player player;
    ArrayList<Wall> walls;
    ArrayList<Enemy> enemies;


    @Override
    public void run() {
        while(true) {
            if(GameState.pause) {
                System.out.println("");
                continue;
            }
            try {
                Thread.sleep(5);
                playerMove();
                for(Enemy enemy: enemies){
                    enemyMove(enemy);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private void enemyMove(Enemy enemy){
        boolean flag = false;
        for(Wall wall : walls){
            if(wall.checkAbove(enemy))
                flag = true;
        }
        if(!flag) {
            enemy.setLocationY(enemy.getLocationY() + 5);
        }
    }
    private void playerMove(){
        if(player.jump)
            return;
        boolean flag = false;
        for (Wall wall : walls) {
            if (wall.checkAbove(player))
                flag = true;
        }
        if (!flag) {
            player.setLocationY(player.getLocationY() + 5);
        }
        else {
            player.setLastPosX(player.getLocationX());
            player.setLastPosY(player.getLocationY());
        }

    }
}
