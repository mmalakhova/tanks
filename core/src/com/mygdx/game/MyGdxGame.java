package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.tanks.BotTank;
import com.mygdx.game.tanks.PlayerTank;
import com.mygdx.game.tanks.Tank;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Map map;
    private PlayerTank playerTank;
    private BulletEmitter bulletEmitter;
    private BotEmitter botEmitter;
    private float gameTimer;

    private static final boolean FRIENDLY_FIRE = false;

    public PlayerTank getPlayerTank(){
        return playerTank;
    }

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }

    @Override
    public void create() {
        TextureAtlas atlas = new TextureAtlas("game.pack.atlas");
        batch = new SpriteBatch();
        map = new Map(atlas);
        playerTank = new PlayerTank(this, atlas);
        bulletEmitter = new BulletEmitter(atlas);
        botEmitter = new BotEmitter(this, atlas);
        botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        ScreenUtils.clear(0, 0.75f, 0, 1);
        batch.begin();
        map.render(batch);
        playerTank.render(batch);
        botEmitter.render(batch);
        bulletEmitter.render(batch);
        batch.end();
    }

    public void update(float dt) {
        gameTimer += dt;
        if (gameTimer > 10.0f) {
            gameTimer = 0;
            botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
        }
        playerTank.update(dt);
        botEmitter.update(dt);
        bulletEmitter.update(dt);
        checkCollisions();
    }

    public void checkCollisions() {
		for (int i = 0; i < bulletEmitter.getBullets().length; i++){
		    Bullet bullet = bulletEmitter.getBullets()[i];
		    if (bullet.isActive()){
		        for ( int j = 0; j < botEmitter.getBots().length; j++){
                    BotTank bot = botEmitter.getBots()[i];
                    if (bot.isActive()){
                        if (checkBulletOwner(bot, bullet) && bot.getCircle().contains(bullet.getPosition())){
                            bullet.deactivate();
                            bot.takeDamage(bullet.getDamage());
                            break;
                        }
                    }
                }
                if (checkBulletOwner(playerTank, bullet) && playerTank.getCircle().contains(bullet.getPosition())){
                    bullet.deactivate();
                    playerTank.takeDamage(bullet.getDamage());
                }
            }
        }
    }

    public boolean checkBulletOwner(Tank tank, Bullet bullet){
        if (!FRIENDLY_FIRE){
            return tank.getOwnerType() != bullet.getOwner().getOwnerType();
        } else {
            return tank != bullet.getOwner();
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
