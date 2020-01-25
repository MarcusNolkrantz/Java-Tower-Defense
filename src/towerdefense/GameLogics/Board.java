package towerdefense.gamelogics;

import towerdefense.spawns.BasicSpawnStats;
import towerdefense.spawns.SpawnStats;
import towerdefense.gameobjects.Enemy;
import towerdefense.gameobjects.Tower;
import towerdefense.spawns.IronSpawnStats;
import towerdefense.gamelogics.maps.Map;
import towerdefense.spawns.TeleportingSpawnStats;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for the game's board. This class contains functionality for the game to be able to playBackgroundMusic.
 */
public class Board
{
    private static final int TILE_SIZE = 60;
    private static final int START_MONEY = 200;
    private static final int START_HEALTH = 20;

    //Basic enemy's initial spawnstats
    private static final float BASIC_PERIOD_DECREMENT = 0.9F;
    private static final float BASIC_HEALTH_INCREMENT = 0.5F;
    private static final float BASIC_AMOUNT_INCREMENT = 1.3F;
    private static final float BASIC_START_PERIOD = 35;
    private static final float BASIC_START_AMOUNT = 10;
    private static final float BASIC_START_HEALTH = 1;

    //Teleporting enemy's initial spawnstats
    private static final float TELEPORTING_PERIOD_DECREMENT = 0.90F;
    private static final float TELEPORTING_HEALTH_INCREMENT = 1.15F;
    private static final float TELEPORTING_AMOUNT_INCREMENT = 1.3F;
    private static final float TELEPORTING_START_PERIOD = 50;
    private static final float TELEPORTING_START_AMOUNT = 1;
    private static final float TELEPORTING_START_HEALTH = 1;

    //Iron enemy's initial spawnstats
    private static final float IRON_PERIOD_DECREMENT = 0.90F;
    private static final float IRON_HEALTH_INCREMENT = 1.1F;
    private static final float IRON_AMOUNT_INCREMENT = 1.05F;
    private static final float IRON_START_PERIOD = 40;
    private static final float IRON_START_AMOUNT = 2;
    private static final float IRON_START_HEALTH = 4;

    private int width;
    private int height;
    private int round;
    private int health;
    private int score;
    private int money;
    private boolean gamePaused;
    private boolean gameOver;
    private Map map;
    private TowerType holdingTower = null;
    private Tower selectedTower = null;
    private Tower flowingTower = null;
    private EnemyMaker enemyMaker;
    private TowerMaker towerMaker;
    private HighscoreList highscores = null;
    private List<Enemy> enemies;
    private List<BoardListener> boardListeners;
    private List<Tower> towers;
    private SpawnStats[] spawnStats;
    private MusicPlayer musicPlayer;
    private MusicPlayer soundEffects;


    public Board(final int height, final int width, final Map map) {
	this.map = map;
	this.width = width;
	this.height = height;
	this.enemies = new ArrayList<>();
	this.towers = new ArrayList<>();
	this.boardListeners = new ArrayList<>();
	this.spawnStats = new SpawnStats[3];
	this.gamePaused = false;
	this.musicPlayer = new MusicPlayer("imperial_march");
	this.soundEffects = new MusicPlayer("pop");
	newGame();
	this.enemyMaker = new EnemyMaker(map.getCheckpoint(0));
	this.towerMaker = new TowerMaker();


	try {
	    this.highscores = FileManager.readHighscores();
	} catch(FileNotFoundException e){
	    highscores = new HighscoreList();
	}
	catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}

    }
    public int getScore() {
  	return score;
      }

    public int getMoney() {
	return money;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int getRound() {
	return round;
    }

    public Tile getTileAt(int x, int y) {
        return map.getTile(y,x);
    }

    public int getEnemyAmount(){
        return enemies.size();
    }

    public Enemy getEnemy(int i){
        return enemies.get(i);
    }

    public static int getTileSize() {
	return TILE_SIZE;
    }


    public int getHealth() {
	return health;
    }

    public Tower getSelectedTower() {
	return selectedTower;
    }

    public TowerType getHoldingTower() {
	return holdingTower;
    }

    public Tower getFlowingTower() {
	return flowingTower;
    }

    public int getTowerAmount(){
        return towers.size();
    }

    public Tower getTowerAt(int i){ return towers.get(i); }

    public Tower getTowerAtPos(int x, int y){
	for (Tower tower : towers) {
	    if(tower.getLeftX() <= x && x <= tower.getLeftX() + tower.getWidth() && tower.getTopY() <= y &&
	       y <= tower.getTopY() + tower.getHeight()){
	        return tower;
	    }
	}
	return null;
    }

    public boolean isGameOver() {
	return gameOver;
    }

    public void setHoldingTower(final TowerType holdingTower) {
	this.holdingTower = holdingTower;
    }

    public void setFlowingTower(final Tower flowingTower) {
	this.flowingTower = flowingTower;
    }

    public void addScore(String name, int score){
       if(highscores.getLenght(map) > 0) {
	    if (score < highscores.getScore(map, highscores.getLenght(map)-1)) {
		highscores.addScore(map, name, score);
	    }
	}
	else{
	    highscores.addScore(map,name,score);
	}
       highscores.addScore(map,name,score);
        FileManager.saveHighscores(highscores);
    }

    public void addMoney(int damage, int health) {
        if (health < 0) {
            money += damage + health;
            score += damage + health;
	}
	else {
            score += damage;
            money += damage;
	}
    }

    /**
     * This function is called each clock cycle of the clock started in SettingsFrame. It handles all functionality that needs
     * to be performed/checked each cycle: spawning enemies, moving them and having towers attack them.
     */
    public void tick() {
	if (health > 0 && !gamePaused) {
	    //spawn enemies
	    for (SpawnStats spawnStat : spawnStats) {
		spawnStat.updateTime();
		if (spawnStat.canSpawn()) {
		    enemies.add(enemyMaker.createEnemy(spawnStat.getEnemyType(), Math.round(spawnStat.getHealth())));
		    spawnStat.enemySpawned();
		}
	    }
	    //attack enemies
	    for (Tower tower : towers) {
	        Enemy enemy = null;
		tower.updateAttackDraw();
		tower.enableAttack();
		if (tower.canShoot()) {
		    List<Enemy> deadEnemies = new ArrayList<>();
		    for (int i = 0; i < getEnemyAmount(); i++) {
			enemy = getEnemy(i);
			if (tower.withinRange(enemy) && tower.canDamageAgain()) {
			    tower.attack(enemy);
			    tower.disableIndivdualAttacks();
			    if (enemy.isDamaged()) {
			        soundEffects.playSoundEffect();
			        addMoney(tower.getDamage(), enemy.getHealth());
			    }
			    enemy.resetDamageTaken();
			    if (enemy.isDead()) {
			        deadEnemies.add(enemy);
			    }
			}
		    }
		    destroyEnemies(deadEnemies);
		}

		if (enemy == null) {
		    tower.noShot();
		}
	    }

	    notifyListeners();
	    moveAllEnemies();
	}
    }

    public void newRound() {
        round ++;
        score += 100;
	for (SpawnStats spawnStat : spawnStats) {
	    spawnStat.newRound(round);
	}
    }

    public void addBoardListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    public void notifyListeners() {
	for (BoardListener bl : boardListeners) {
	    bl.boardChanged();
	}
    }

    /**
     * Moves all enemies on the board according to their function move(). If an enemy is at the map's last checkpoint
     * (located outside the map), remove the enemy and take damage.
     */
    private void moveAllEnemies(){
	List<Enemy> outOfBoundsEnemies = new ArrayList<>();
	for (Enemy enemy : enemies) {
	    enemy.move(map);
	    if (enemy.outOfBoard()) {
		outOfBoundsEnemies.add(enemy);
		health -= enemy.getHealth();
	    }
	    if (health <= 0) {
		gameOver = true;
	    }

	}
	enemies.removeAll(outOfBoundsEnemies);
	outOfBoundsEnemies.clear();
	notifyListeners();
    }

    private boolean canAfford(Tower tower) {
        return money >= tower.getCost();
    }

    public void placeTower(int x, int y){
        Tower tower = towerMaker.createTower(holdingTower, x, y);
        if(tower != null && canBePlaced(tower) && canAfford(tower)){
	    towers.add(tower);
	    holdingTower = null;
	    flowingTower = null;
	    money -= tower.getCost();
	    notifyListeners();
        }
    }

    public void flowTower(int x, int y){
	flowingTower = towerMaker.createTower(holdingTower, x, y);
    }

    /**
     * Checks if a tower can be placed on the board without it overlapping the enemies path or any other tower.
     * @param tower to be placed
     * @return if a tower can be placed.
     */
    private boolean canBePlaced(Tower tower){
	for (int y = tower.getTopY(); y < tower.getY()+tower.getHeight()/2; y++) {
	    for (int x = tower.getLeftX(); x < tower.getX()+tower.getWidth()/2; x++) {
		for (Tower t : towers) {
		    if (t.getTopY() < y && y < t.getY() + t.getHeight()/2 && t.getLeftX() < x && x < t.getX() + t.getWidth()/2) {
			return false;
		    }
		}
		Tile currentTile = map.getTile(y / TILE_SIZE,x/TILE_SIZE);
		if (currentTile != Tile.GROUND) {
		    return false;
		}
	    }
	}
	return true;
    }

    public void destroyEnemies(List<Enemy> deadEnemies) {
        enemies.removeAll(deadEnemies);
    }

    public boolean isActiveRound(){
	for (SpawnStats spawnStat : spawnStats) {
	    if (spawnStat.getCurrentAmount() != 0 || !enemies.isEmpty())
	        return true;
	}
	return false;
    }

    public void pauseGame() {
        if(gamePaused){
            musicPlayer.continueBackgroundMusic();
            gamePaused = false;
	}
        else{
            musicPlayer.pauseBackgroundMusic();
            gamePaused = true;
	}
    }

    public void selectTower(TowerType type){
        holdingTower = type;
    }

    public void setSelectedTower(final Tower selectedTower) {
	this.selectedTower = selectedTower;
    }

    public void upgrade(){
        if(money >= selectedTower.getUpgradeCost()){
            	money -= selectedTower.getUpgradeCost();
        	selectedTower.upgradeTower();
        }
    }

    public void sellTower(){
        towers.remove(selectedTower);
        money += selectedTower.getSellPrice();
        notifyListeners();
    }

    /**
     * With an existing board, this function will reset the game.
     */
    public void newGame(){
	musicPlayer.playBackgroundMusic();
        gameOver = false;
	score = 0;
	health = START_HEALTH;
	money = START_MONEY;
	round = 0;
	towers.clear();
	enemies.clear();
	spawnStats[0] =  new BasicSpawnStats(BASIC_PERIOD_DECREMENT, BASIC_HEALTH_INCREMENT,
					     BASIC_AMOUNT_INCREMENT, BASIC_START_AMOUNT, BASIC_START_HEALTH, BASIC_START_PERIOD);
	spawnStats[1] = new TeleportingSpawnStats(TELEPORTING_PERIOD_DECREMENT,
						  TELEPORTING_HEALTH_INCREMENT,
						  TELEPORTING_AMOUNT_INCREMENT, TELEPORTING_START_AMOUNT,
						  TELEPORTING_START_HEALTH, TELEPORTING_START_PERIOD);
	spawnStats[2] = new IronSpawnStats(IRON_PERIOD_DECREMENT, IRON_HEALTH_INCREMENT,
					   IRON_AMOUNT_INCREMENT, IRON_START_AMOUNT, IRON_START_HEALTH, IRON_START_PERIOD);
	notifyListeners();
    }

    public void stopBackgroundMusic(){
        musicPlayer.stopbackgroundMusic();
    }




}

