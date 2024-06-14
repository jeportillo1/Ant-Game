package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import com.codename1.ui.Display;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

public class GameWorld extends Observable {
	private GameObjectsCollection myGame;
	private int clock; //game world clock variable
	private int gameLives; //game lives variable
	private Random ranNums1 = new Random(); //variable to get random numbers
	private Random ranNums2 = new Random(); //variable to get random numbers
	private int numFlags; //variable for number of Flags
	private boolean sound;
	private String antData;
	private int xVal;
	private int yVal;
	private int myGameTime;
	private Sound flagColliSound;
	private Sound foodColliSound;
	private Sound spiderColliSound;
	private BGSound backGroundSound;
	
	
	// game world constructor 
	public GameWorld() {
		this.setClock(0); //sets game clock to zero
		this.setGameLives(3); //sets number of lives to 3
		this.myGame = new GameObjectsCollection();
		this.setSound(false);
		this.setAntData("Last Flag Reached : " + 1 + " Food Level : " + 20 + " Health : " + 10);
	}
	
	public BGSound getBGSound() {
		return backGroundSound;
	}
	
	public void createSounds() {
		flagColliSound = new Sound("CHEERS.wav");
		foodColliSound = new Sound("TOON50.wav");
		spiderColliSound = new Sound("SkunkSqueak.wav");
		backGroundSound = new BGSound("forest1.wav");
	}

	public void init() {
		int flagSize = 70;
		Random ranVal = new Random();
		float a = (float) 0.0; //local variables for location (0,0)
		float b = (float) 0.0; //local variables for location (0,0)
	
		//initializing my ant
		Ant hormiga = new Ant(20,60,120,15,1,1,255,0,0,0,a,b);
		this.setAntData(hormiga.toStringData());
		
		//initializing my flags
		Flag flag1 = new Flag(65,105,225,1,flagSize,a,b);
		Flag flag2 = new Flag(65,105,225,2,flagSize,((float) 400),((float) 250));
		Flag flag3 = new Flag(65,105,225,3,flagSize,((float) 650),((float) 550));
		Flag flag4 = new Flag(65,105,225,4,flagSize,((float) 1250),((float) 850));
		Flag flag5 = new Flag(65,105,225,5,flagSize,((float) 1650),((float) 1100));
		numFlags = 5;
		
		//initializing my food stations
		FoodStation oldCandy = new FoodStation((45 + (ranVal.nextInt(25))),0,255,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		FoodStation oldFruit = new FoodStation((45 + (ranVal.nextInt(25))),0,255,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		FoodStation trashCan = new FoodStation((45 + (ranVal.nextInt(25))),0,255,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		
		//initializing my spiders
		Spider blackWidow = new Spider((30 + (ranVal.nextInt(20))),ranVal.nextInt(359),(150 + (ranVal.nextInt(5))),0,0,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		Spider brownRecluse = new Spider((30 + (ranVal.nextInt(20))),ranVal.nextInt(359),(150 + (ranVal.nextInt(5))),0,0,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		Spider demon = new Spider((30 + (ranVal.nextInt(20))),ranVal.nextInt(359),(150 + (ranVal.nextInt(5))),0,0,0,((float) ranNums1.nextInt(xVal)),((float) ranNums2.nextInt(yVal)));
		
		
		myGame.add(hormiga);
		myGame.add(flag1);
		myGame.add(flag2);
		myGame.add(flag3);
		myGame.add(flag4);
		myGame.add(flag5);
		myGame.add(oldCandy);
		myGame.add(oldFruit);
		myGame.add(trashCan);
		myGame.add(blackWidow);
		myGame.add(brownRecluse);
		myGame.add(demon);
		
		
	}
	
	//method to reinitialize my world
	private void reInit() {
		GameObjectsCollection myReGame = new GameObjectsCollection(); //creating new ArrayList
		this.setMyGame(myReGame); // setting old ArrayList to be new ArrayList
		this.init(); //initializing the game world objects again
		this.setChanged();
		this.notifyObservers();
	}
	
	//method to access clock value
	public int getClock() {
		return clock;
	}

	//method to mutate the clocks value
	public void setClock(int clock) {
		this.clock = clock;
		this.setChanged();
		this.notifyObservers();
	}

	//method to access the game lives value
	public int getGameLives() {
		return gameLives;
	}

	//method to mutate the game lives value
	public void setGameLives(int gameLives) {
		this.gameLives = gameLives;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setSound(boolean b) {
		this.sound = b;
		this.setChanged();
		this.notifyObservers();
	}
	//method to accelerate the ant
	public void accel() {
		
		System.out.println("Accelerate Applied");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();
		
		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				ant.speedUp(); // Ant Class method to speed up
				setAntData(ant.toStringData());
				this.setChanged();
				this.notifyObservers();
				break;
			}
			else
				myIt.getNext();
		}
		
	}
	
	//method to tell ant to brake
	public void brake(){
		System.out.println("Brake Applied");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt.getNext();
		}
		ant.slowDown(); // Ant Class method to slow down
		this.setAntData(ant.toStringData());
		this.setChanged();
		this.notifyObservers();
	}
	
	//method to tell ant to go left
	public void left(){
		System.out.println("Go Left Applied");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt.getNext();
		}
		ant.goLeft(); // Ant Class method to go left
		this.setAntData(ant.toStringData());
		this.setChanged();
		this.notifyObservers();
	}
	
	//method to tell ant to go right
	public void right(){
		System.out.println("Go Right Applied");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt.getNext();
		}
		ant.goRight(); // Ant Class method to go right
		this.setAntData(ant.toStringData());
		this.setChanged();
		this.notifyObservers();
	}
	
	//method to pretend collision with a flag
	public void colliFlag(int flag){
		System.out.println("Flag Collision");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				
				// if statement to check if flag is equal to the next flag ant is suppose to go to
				if(flag == (ant.getLastFlagReached()+1)) {
					ant.nextFlagReached();
					this.setAntData(ant.toStringData());
					this.flagColliSound.play();
					// if statement to check if you made it to the last flag 
					if(ant.getLastFlagReached() == numFlags) {
						System.out.println("Game Over , you have succeded. Total Time: " + clock +
											"\nThe Ant has comleted his mission...\nThank you - The Ant");
						System.exit(0);
					}
				}
				// else statement that shows if you try to go to the wrong flag
				else
					System.out.println("Wrong flag, Go to flag = " + (ant.getLastFlagReached()+1));
			break;
			}
			else
				myIt.getNext();
		}
	}
	
	//method to pretend collision with food station
	public void colliFood(){
		System.out.println("Food Station Collision");
		int temp = 0; //temp variable to hold the capacity of the food station
		Random fSize = new Random();
		FoodStation f1 = null; //create new food station to access food station in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find food station object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof FoodStation ) {
				f1 = (FoodStation) myIt.getCurr(); //assign f1 to the food station object in ArrayList to access FoodStation Class methods
				temp = f1.getCapacity(); //capacity value held in temp variable
				f1.setColor(158, 235, 52); //Food Station method that sets food station color to light green
				f1.depleteCapacity(); //Food Station method that depletes food station capacity to zero
				
				myGame.delete(myIt.getIndex());
				
				//create new food station object
				FoodStation newHood = new FoodStation((10 + (fSize.nextInt(40))),0,255,0,((float) ranNums1.nextInt(1000)),((float) ranNums2.nextInt(1000)));
				myGame.add(myIt.getIndex(),newHood); //add new food station object into ArrayList
				myGame.add(f1);
				break;
			}
			else
				myIt.getNext();
		}
		
		
	
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt2 = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt2.hasNext()) {
			if(myIt2.getCurr() instanceof Ant ) {
				ant = (Ant) myIt2.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt2.getNext();
		}
		ant.increaseFoodLevel(temp);// Ant Class method that increases food level by temp
		this.setAntData(ant.toStringData());
		this.foodColliSound.play();
	}
	
	//method to pretend collision with spider
	public void colliSpider(){
		System.out.println("Spider Collision");
		@SuppressWarnings("unused") //here because not using s1 for this assign since it is a pretend collission
		Spider s1 = null; //create new spider to access spider in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find spider object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Spider ) {
				s1 = (Spider) myIt.getCurr(); //assign spider to the spider object in ArrayList to access Spider Class methods
				break;
			}
			else
				myIt.getNext();
		}
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt2 = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt2.hasNext()) {
			if(myIt2.getCurr() instanceof Ant ) {
				ant = (Ant) myIt2.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				ant.decreaseHealth(); // Ant Class method to decrease its health
				
				ant.redColor(); // Ant Class method to change the redness of the ant
				break;
				}
			else
				myIt2.getNext();
		}
		
		this.spiderColliSound.play();
		//if statement in case ant health gets to zero
		if (ant.getHealth() == 0 ) {
			ant.resetHealth();
			this.setGameLives((gameLives - 1)); //decrement gameLives value
			System.out.println("The ant is dead....\nSpider's got it..... \nYummy - Spider.... \nI'm Dead - Ant");
				if (gameLives == 0) {
					System.out.println("GAME OVER");
					System.exit(0);
				}
				else if (gameLives == 1) {
					System.out.println("One life remaining...Don't messup the Ant is depending on you...");
					System.out.println("Lost a life, Remaining lives: " + gameLives);
					this.reInit();
				}
				else {
					System.out.println("Lost a life, Remaining lives: " + gameLives);
					this.reInit();
				}	
		}
		this.setAntData(ant.toStringData());
		
	}
	
	//method to make game world clock tick forward
	public void tickA1(int time){
		
		Spider s1 = null; //create new spider to access spider in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find spider object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Spider ) {
				s1 = (Spider) myIt.getCurr(); //assign spider to the spider object in ArrayList to access Spider Class methods
				s1.move(time); // Spider Class method to make spider s1 move
				this.setChanged();
				this.notifyObservers();
				myIt.getNext();
			}
			else
				myIt.getNext();	
		}
		
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt2 = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt2.hasNext()) {
			if(myIt2.getCurr() instanceof Ant ) {
				ant = (Ant) myIt2.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				ant.move(time); // Ant Class Method that makes ant move
				this.setChanged();
				this.notifyObservers();
				
				break;	
			}
			else
				myIt2.getNext();
		}
		
		this.myGameTime = this.myGameTime + 1;
		
		if((myGameTime % 50) == 0) {
			this.setClock(clock + 1); 
			ant.decreaseFoodLevel();
		}
		//if statement if food level for ant goes to zero
		if (ant.getFoodLevel() == 0) {
			this.setGameLives(gameLives - 1); //decrement gameLives value
			System.out.println("I died of hunger and starvation - The Ant");
			if (gameLives == 0) {
				System.out.println("GAME OVER");
				System.exit(0);
			}
			else if (gameLives == 1) {
				System.out.println("One life remaining...Don't messup the Ant is depending on you...");
				System.out.println("Lost a life, Remaining lives: " + gameLives);
				this.reInit();	
			}
			else {
				System.out.println("Lost a life, Remaining lives: " + gameLives);
				this.reInit();
			}	
		}
		// increment game world clock
		this.setAntData(ant.toStringData());
	}
	
	//method to print out game world data
	public void data(){
		System.out.println("Display Applied");
		Ant ant = null;//create new ant to access ant in ArrayList
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof Ant ) {
				ant = (Ant) myIt.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt.getNext();
		}
		
		this.setAntData(ant.toStringData());
		
		//System.outs to print out game data
		System.out.println("Game World:");
		System.out.println("Number of lives = " + gameLives);
		System.out.println("Elapsed Time = " + clock);
		System.out.println(antData);
		
		
	}
	
	//method to print out map of world
	public void map(){
		System.out.println("Map Applied");
		IIterator myIt = myGame.getIterator();

		//for loop to find ant object in ArrayList
		while (myIt.hasNext()) {
			System.out.println(myIt.getCurr().toString()); //each object prints out there own toString method
			myIt.getNext();
		}
		
	}
	
	//method to exit game
	public void exit(){
		Display.getInstance().exitApplication();
	}

	//method to get my ArrayList
	public GameObjectsCollection getMyGame() {
		return myGame;
	}

	//method to set my ArrayList
	private void setMyGame(GameObjectsCollection yo) {
		this.myGame = yo;
	}
	
	public void toggleSound() {
		this.sound = !sound;
		if(sound == true) {
			System.out.println("Sound On");
			//this.setSound(true);
			backGroundSound.play();
		}
		else {
			System.out.println("Sound Off");
			//this.setSound(false);
			backGroundSound.pause();
		}
	}
	
	public void setAntData(String yo) {
		this.antData = yo;
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public String getAntData() {
		return antData;
		
	}
	
	public boolean getSound() {
		return sound;
	}



	public int getxVal() {
		return xVal;
	}



	public void setxVal(int x) {
		this.xVal = x;
	}



	public int getyVal() {
		return yVal;
	}



	public void setyVal(int y) {
		this.yVal = y;
	}
	
	
}
