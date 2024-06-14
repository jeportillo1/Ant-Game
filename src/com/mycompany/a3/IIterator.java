package com.mycompany.a3;

public interface IIterator {
	
	public abstract boolean hasNext();
	public abstract GameObjects getNext();
	public abstract GameObjects getCurr();
	public abstract int getIndex();
}
