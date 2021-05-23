package com.mygdx.game.tanks;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Direction;
import com.mygdx.game.utils.TankOwner;

public class BotTank extends Tank {
    Direction prefferedDirection;
    float aiTimer;
    float aiTimerTo;
    float pursuitRadius;
    boolean active;

    public boolean isActive() {
        return active;
    }

    public BotTank(MyGdxGame game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.AI;
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("enemy");
        this.textureHp = atlas.findRegion("hpline");
        this.position = new Vector2(500.0f, 500.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = hpMax;
        this.aiTimerTo = 3.0f;
        this.pursuitRadius = 300.0f;
        this.prefferedDirection = Direction.UP;
        this.active = false;
        this.circle = new Circle(position.x, position.y, ((float)width + (float)height) / 2);
    }

    public void activate(float x, float y) {
        hpMax = 10;
        hp = hpMax;
        position.set(x, y);
        aiTimer = 0.0f;
        prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = prefferedDirection.getAngle();
        active = true;
    }

    @Override
    public void destroy(){
        active = false;
    }

    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.5f, 4.0f);
            prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = prefferedDirection.getAngle();
        }
        position.add(speed * prefferedDirection.getVx() * dt, speed * prefferedDirection.getVy() * dt);
        float dst = this.position.dst(game.getPlayerTank().getPosition());
        if (dst < pursuitRadius){
            rotateTurretToPoint(game.getPlayerTank().getPosition().x, game.getPlayerTank().getPosition().y, dt);
            fire(dt);
        }
        super.update(dt);
    }


}
