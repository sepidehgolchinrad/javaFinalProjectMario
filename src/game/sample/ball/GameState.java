/*** In The Name of Allah ***/
package game.sample.ball;

import game.sample.ball.objects.*;
import game.sample.ball.objects.enemy.Enemy;
import game.sample.ball.objects.enemy.Hedgehog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

    public static boolean pause;
	public int locX, locY, diam;
	public boolean gameOver;
	public ArrayList<Wall> walls;
	public ArrayList<Coin> coins;
	public ArrayList<Bullet> bullets;
	public ArrayList<Enemy> enemies;
	boolean jumpFlag;

	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
	private boolean mousePress;
	private int mouseX, mouseY;	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	private ArrayList<GameObject> gameObjects;
	private Player player;
	private Gravity gravity;
	private long lastJump;
	private int mapColumn;
	private ArrayList<String>map;
	private int boxH, boxW;

	public Player getPlayer() {
		return player;
	}

	public GameState() throws FileNotFoundException {
		gameObjects = new ArrayList<GameObject>();
		walls = new ArrayList<Wall>();
		coins = new ArrayList<Coin>();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		map = new ArrayList<String>();
		mapColumn = 10;
		boxW = GameFrame.GAME_WIDTH / 20;
		boxH = GameFrame.GAME_HEIGHT / 10;
		initObjects();

//		walls.add(new StoneWall(0,GameFrame.GAME_HEIGHT-190,GameFrame.GAME_WIDTH-500,190));
//		walls.add(new StoneWall(GameFrame.GAME_WIDTH-400,GameFrame.GAME_HEIGHT-190,GameFrame.GAME_WIDTH,190));
//		walls.add((new StoneWall(400,500,100,50)));
//		walls.add((new BrickWall(450,440,100,30)));



//		Hedgehog hedgehog = new Hedgehog(400,480, +1);
//		enemies.add(hedgehog);
//		ThreadPool.execute(hedgehog);
//		coins.add(new Coin(200, 500));

//		coins.add(new Coin(150, 500));
//		coins.add(new Coin(250, 500));

//		player = new Player(300,500,30,30);
		gravity = new Gravity();
		gravity.walls = walls;
		gravity.player = player;
		gravity.enemies = enemies;
		ThreadPool.execute(gravity);
		gameOver = false;
		//
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		//
		mousePress = false;
		mouseX = 0;
		mouseY = 0;
		//
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}

	/**
	 * The method which initalize objects in map
	 */
	public void initObjects() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src/icons/map.txt"));
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			map.add(line);
		}
		for(int i = 0 ; i < 20 ; i ++) {
			for(int j = 0 ; j < 10 ; j ++ ) {
				switch (map.get(j).charAt(i)) {
					case 'm':
						player = new Player(i * boxW, j * boxH, boxW, boxH);
						break;
					case 's':
						walls.add(new StoneWall(i * boxW, j * boxH, boxW, boxH));
						break;
					case 'b':
						walls.add((new BrickWall(i * boxW, j * boxH, boxW, boxH)));
						break;
					case 'c':
						coins.add(new Coin(i * boxW, j * boxH, boxW/2, boxH/2));
						break;
					case 'H':
						Hedgehog hedgehog = new Hedgehog(i * boxW,j * boxH, boxW, boxH, +1);
						enemies.add(hedgehog);
						ThreadPool.execute(hedgehog);
						break;
				}
			}
		}
	}
	/**
	 * The method which updates the game state.
	 */
	public void update() throws InterruptedException {
		if (mousePress) {
			locY = mouseY - diam / 2;
			locX = mouseX - diam / 2;
		}
		if (keyLEFT) {
			player.direction = -1;
			player.setLocationX(player.getLocationX() - boxW / 5);
			boolean flag = true;
			for(Wall wall : walls){
				if(!wall.checkMove(player))
					flag = false;
			}
			if(!flag)
				player.setLocationX(player.getLocationX() + boxW / 5);
		}
		if (keyRIGHT) {
			player.direction = 1;
			player.setLocationX(player.getLocationX() + boxW / 5);
			boolean flag = true;
			for(Wall wall : walls){
				if(!wall.checkMove(player))
					flag = false;
			}
			if(!flag)
				player.setLocationX(player.getLocationX() - boxW / 5);
		}
		checkLiveWalls();
		checkCoins();
		checkBullets();
		removeDeadBullets();
		//TODO
        player.setLocationX(Math.max(player.getLocationX(), 0));
        player.setLocationY(Math.max(player.getLocationY(), 0));
        if(player.getLocationY() >= GameFrame.GAME_HEIGHT){
			System.out.println("fall");
        	player.fall();
		}
		locX = Math.max(locX, 0);
		locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
		locY = Math.max(locY, 0);
		locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);
	}
	private void checkLiveWalls() {
		for (int i = walls.size() - 1; i >= 0; i--) {
		    Wall wall = walls.get(i);
			if (wall.getClass() == BrickWall.class) {
				if (!(((BrickWall) wall).isAlive())) {
					walls.remove(i);
				}
			}
		}
	}
	private void checkCoins() {
		for (int i = coins.size() - 1; i>=0 ;i--)
		{
			Coin coin = coins.get(i);
			if(!coin.checkMove(player)){
				player.coins++;
				coins.remove(coin);
			}
		}
	}
	private void addBullet() {
	    if(player.bullets == 0)
	    	return;
	    player.bullets--;
	    Bullet bullet = new Bullet(player.getLocationX(), player.getLocationY() , player.direction);
	    bullets.add(bullet);
	    ThreadPool.execute(bullet);

	}
	private void removeDeadBullets() {
		for(int i=bullets.size()-1 ;i>=0 ;i--){
			Bullet bullet = bullets.get(i);
			if(!bullet.getAlive())
				bullets.remove(bullet);
		}
	}

	
	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}
	private boolean canJump(){
		for(Wall wall:walls){
			if(wall.checkBelow(player))
			{
				if(wall.getClass() == BrickWall.class)
				{

				}

				return false;
			}
		}
		return true;
	}
	private void checkBullets(){
		for(int i=bullets.size()-1;i>=0;i--){
			Bullet bullet = bullets.get(i);
			for(Wall wall:walls){
				if(!wall.checkMove(bullet)){
				    bullets.remove(bullet);
				}
			}
		}
	}



	/**
	 * The keyboard handler.
	 */
	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					if(pause)
						break;
					keyUP = true;
					player.jump = true;
					if(System.currentTimeMillis() - lastJump <= 300)
						 break;
					if (player.jump) {
						lastJump = System.currentTimeMillis();
						for (int i = 0; i < 15; i++) {

							if(!canJump())
								break;
							player.setLocationY(player.getLocationY() - boxH / 5);
							try {
								Thread.sleep(5);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
						keyUP = false;
						player.jump = false;
						try {
							Thread.sleep(5 * 30);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					break;
				case KeyEvent.VK_DOWN:
					if(pause)
						break;
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
					if(pause)
						break;
					keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
					if(pause)
						break;
					keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					pause = !pause;
					System.out.println(pause);
					//gameOver = true;
					break;
				case KeyEvent.VK_SPACE:
				    if(pause)
				    	break;
					addBullet();
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					//keyUP = false;
					//player.jump = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
			}
		}

	}


	/**
	 * The mouse handler.
	 */
	class MouseHandler extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			mousePress = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mousePress = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}
}

