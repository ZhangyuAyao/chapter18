package com.hspedu.tankarea;

public class Hero extends Tank {
    Shot shot = null;
    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {
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
        //启动我们的Shot线程
        new Thread(shot).start();
    }
}
