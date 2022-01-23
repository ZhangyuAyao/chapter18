package com.hspedu.tankgame4;

import java.util.Vector;

public class Hero extends Tank {
    Shot shot = null;

    //子弹集合
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {
        //面板上最多只能有5颗子弹
        if (shots.size() == 5) {
            return;
        }

        //创建 Shot 对象，根据当前Hero对象的位置和方向来创建Shot对象
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }

        //把新创建的shot放入到shots
        shots.add(shot);

        //启动我们的Shot线程
        new Thread(shot).start();
    }

}
