package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.tanks.Tank;

public class BulletEmitter {
    private TextureRegion bulletTexture;
    private Bullet[] bullets;

    public Bullet[] getBullets(){
        return bullets;
    }
    public static final int MAX_BULLETS_COUNT = 50;

    public BulletEmitter(TextureAtlas atlas){
        this.bulletTexture = atlas.findRegion("bullet");
        this.bullets = new Bullet[MAX_BULLETS_COUNT];
        for ( int i =0 ; i < bullets.length; i++){
            this.bullets[i] = new Bullet();
        }
    }

    public void activate(Tank owner, float x, float y, float vx, float vy, int damage){
        for ( int i = 0; i < bullets.length; i++){
            if (!bullets[i].isActive()){
                bullets[i].activate(owner, x, y, vx, vy, damage);
                break;
            }
        }
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < bullets.length; i++){
            if (bullets[i].isActive()){
                batch.draw(bulletTexture, bullets[i].getPosition().x - 8, bullets[i].getPosition().y - 8 );
            }
        }
    }

    public void update (float dt){
        for (int  i = 0; i < bullets.length; i++){
            if (bullets[i].isActive()){
                bullets[i].update(dt);
            }
        }
    }
}
