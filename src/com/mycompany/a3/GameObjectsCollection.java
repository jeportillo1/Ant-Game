package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectsCollection implements ICollection{

	private ArrayList<GameObjects> myGame; //my game objects ArrayList
	
	public GameObjectsCollection() {
		this.setMyGame(new ArrayList<GameObjects>());
	}
	
	private void setMyGame(ArrayList<GameObjects> myGame) {
		this.myGame = myGame;
	}
	
	@Override
	public void add(GameObjects gO) {
		myGame.add(gO);
	
	}
	
	@Override
	public void delete(int curr) {
		myGame.remove(curr);
	}

	@Override
	public IIterator getIterator() {
		return new myGOIterator();
	}
	
	@Override
	public void add(int lo, GameObjects t) {
		myGame.add(lo, t);
	}
	@Override
	public int getSize() {
	// TODO Auto-generated method stub
		return myGame.size();
	}
private class myGOIterator implements IIterator {
	private int currentIndex;
	
	public myGOIterator() {
		this.currentIndex = 0;
	}
	@Override
	 public boolean hasNext() {
        return currentIndex < myGame.size();
    }

	@Override
	public GameObjects getNext() {
		return myGame.get(currentIndex++);
	}
	
	@Override
	public GameObjects getCurr() {
		return myGame.get(currentIndex);
	}
	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return currentIndex;
	}
	
	
}



}
