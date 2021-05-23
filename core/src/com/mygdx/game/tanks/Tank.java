package com.mygdx.game.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Angles_Utils;
import com.mygdx.game.utils.TankOwner;

public abstract class Tank {
    MyGdxGame game;

    TankOwner ownerType;
    TextureRegion texture;
    TextureRegion textureHp;
    Weapon weapon;


    Vector2 position;
    Circle circle;

    int hp;
    int hpMax;

    float speed;
    float angle;
    float turrentAngle;
    float fireTimer;

    int width;
    int height;

    public Tank(MyGdxGame game) {
        this.game = game;
    }

    public Circle getCircle() {
        return circle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public TankOwner getOwnerType() {
        return ownerType;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle);
        batch.draw(weapon.getTexture(), position.x - 32, position.y - 32, 32, 32, 64, 64, 1, 1, turrentAngle);
        if (hp < hpMax) {
            batch.setColor(0, 0, 0, 1);
            batch.draw(textureHp, position.x - width  / 2 - 2, position.y + height / 2 - 8 - 2, 52, 12);
            batch.setColor(0, 1, 0, 1);
            batch.draw(textureHp, position.x - width  / 2, position.y + height / 2 - 8, ((float) hp / (float)hpMax) * 48, 8);
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) destroy();
    }

    public abstract void destroy();

    public void update(float dt) {
        fireTimer += dt;
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
        if (position.x > Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth();
        }
        if (position.y > Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight();
        }
        circle.setPosition(position);
    }

    public void rotateTurretToPoint(float pointX, float pointY, float dt) {
        float angleTo = Angles_Utils.getAngle(position.x, position.y, pointX, pointY);
        turrentAngle = Angles_Utils.makeRotation(turrentAngle, angleTo, 100.0f, dt);
        turrentAngle = Angles_Utils.angleToFromNegPiToPosPi(turrentAngle);
    }


    public void fire(float dt) {
        if (fireTimer >= weapon.getFirePeriod()) {
            fireTimer = 0.0f;
            float angleRad = (float) Math.toRadians(turrentAngle);
            game.getBulletEmitter().activate(this, position.x, position.y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad), weapon.getDamage());
        }
    }
}
