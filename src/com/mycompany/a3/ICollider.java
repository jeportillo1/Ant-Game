package com.mycompany.a3;

public interface ICollider {
	boolean collidesWith(GameObjects otherObject);
	void handleCollision(GameObjects otherObject);
	void handleCollision(GameObjects otherObject, GameWorld yo);
}
