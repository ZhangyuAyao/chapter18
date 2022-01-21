package com.hspedu.tankmove_mywork;

import java.util.Vector;

/**
 * @author: bytedance
 * @date: 2022/1/18
 * @description:
 */
public class EnemyTank extends Tank {
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();


    public void moveOn() {
        switch (this.getDirect()) {
            case 0: //向上走
                this.setY(this.getY() - this.getSpeed());
                break;
            case 1: //向右走
                this.setX(this.getX() + this.getSpeed());
                break;
            case 2: //向下走
                this.setY(this.getY() + this.getSpeed());
                break;
            case 3: //向左走
                this.setX(this.getX() - this.getSpeed());
                break;
        }
    }
}
