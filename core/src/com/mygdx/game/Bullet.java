package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.tanks.Tank;

public class Bullet {
    private Tank owner;
    private Vector2 position;
    private Vector2 velocity;
    private int damage;
    private boolean active;

    public Bullet(){
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.active = false;
        this.damage = 2;
    }

    public int getDamage(){
        return damage;
    }

    public Tank getOwner() {
        return owner;
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isActive(){
        return active;
    }

    public void activate(Tank owner, float x, float y, float vx, float vy, int damage){
        this.active = true;
        this.position.set(x,y);
        this.velocity.set(vx,vy);
        this.damage = damage;
        this.owner = owner;
    }

    public void deactivate()  {
        active = false;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0.0f || position.x > 1280.0f || position.y < 0.0f || position.y > 960.0f ){
            active = false;
        }
    }
}
