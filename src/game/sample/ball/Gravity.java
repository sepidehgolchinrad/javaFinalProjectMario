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
            enemy.setLocationY(enemy.getLocationY() + enemy.getHeight() / 10);
        }
    }
    private void playerMove(){
        if(player.jump)
            return;
        boolean flag = false;
        for (Wall wall : walls) {
            if (wall.checkAbove(player)) {
                System.out.println("player: " + player.getLocationX() / player.getWidth() + " " + player.getLocationY() / player.getHeight() + " : " + (player.getLocationY() + player.getHeight()));
                System.out.println(wall.getLocationX() / wall.getWidth() + " " + wall.getLocationY() / wall.getHeight());
                flag = true;
            }
        }
        if (!flag) {
            player.setLocationY(player.getLocationY() + player.getHeight() / 10);
        }
        else {
            player.setLastPosX(player.getLocationX());
            player.setLastPosY(player.getLocationY());
        }

    }
}
