package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y =  15;
    public static final int CELL_SIZE = 64;

    private TextureRegion grassTexture;
    private TextureRegion wallTexture;
    private int obstacles[][];

    public Map(TextureAtlas atlas){
        this.grassTexture = atlas.findRegion("grass2");
        //this.wallTexture = atlas.findRegion("wall");
        this.obstacles = new int[SIZE_X][SIZE_Y];
        this.obstacles[1][1] = 5;
    }

    public void render(SpriteBatch batch){
        for ( int i = 0; i < SIZE_X; i ++){
            for ( int j = 0; j < SIZE_Y; j++){
                batch.draw(grassTexture, i * CELL_SIZE, j * CELL_SIZE);
//                if (obstacles[i][j] > 0){
//                    batch.draw(wallTexture, i * CELL_SIZE, j * CELL_SIZE);
//                }
            }
        }
    }
}
