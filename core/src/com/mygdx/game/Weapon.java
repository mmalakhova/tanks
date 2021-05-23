package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon {
    private TextureRegion texture;
    private float firePeriod;
    private int damage;

    public Weapon(TextureAtlas atlas) {
        this.texture = atlas.findRegion("dulo4");
        this.firePeriod = 0.2f;
        this.damage = 2;
    }

    public TextureRegion getTexture(){
        return texture;
    }

    public  float getFirePeriod(){
        return firePeriod;
    }

    public  int getDamage(){
        return damage;
    }
}
