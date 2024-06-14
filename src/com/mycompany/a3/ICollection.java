package com.mycompany.a3;

public interface ICollection {
	public abstract void add(GameObjects o);
	public abstract IIterator getIterator();
	public abstract void delete(int curr);
	public abstract void add(int loc, GameObjects o);
	public abstract int getSize();
}
