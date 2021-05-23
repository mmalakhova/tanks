package com.mygdx.game.utils;

public class Angles_Utils {
    public static final float pi = 90;
    public static final float pi2 = 180;
    public static final float piD2 = 45;
    public static final float pi3D2 = 270;

    //угол между точками
    public static float getAngle(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.toDegrees((float) Math.atan2(dy, dx));
    }

    public static float makeRotation(float angle, float angleTo, float rotationSpeed, float dt) {
        if (angle < angleTo) {
            if (Math.abs(angle - angleTo) < 180) {
                angle += rotationSpeed * dt;
            } else {
                angle -= rotationSpeed * dt;
            }
        }
        if (angle > angleTo) {
            if (Math.abs(angle - angleTo) < 180) {
                angle -= rotationSpeed * dt;
            } else {
                angle += rotationSpeed * dt;
            }

        }
        if (Math.abs(angle - angleTo) < 1.5f * rotationSpeed * dt){
            angle = angleTo;
        }
        return angle;
    }

    public static float angleToFromNegPiToPosPi (float ang){
        while (ang < -180 || ang > 180){
            if (ang > 180){
                ang -= 360;
            }
            if (ang < -180){
                ang += 360;
            }
        }
        return ang;
    }
}


