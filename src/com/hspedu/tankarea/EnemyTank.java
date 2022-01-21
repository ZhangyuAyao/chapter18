package com.hspedu.tankarea;

import java.util.Vector;

/**
 * @author: bytedance
 * @date: 2022/1/18
 * @description:
 */
public class EnemyTank extends Tank implements Runnable {
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();

    @Override
    public void run() {
        while (true) {
            //根据坦克的方向来继续移动
            switch (getDirect()) {
                case 0: //向上
                    //让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        //防止坦克超出边界
                        if(getY() <= 0) {
                            break;
                        }
                        moveUp();
                        //休眠50毫秒

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: //向右
                    for (int i = 0; i < 30; i++) {
                        //防止坦克超出边界
                        if(getX() >= 1000) {
                            break;
                        }
                        moveRight();
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: //向下
                    for (int i = 0; i < 30; i++) {
                        //防止坦克超出边界
                        if(getY() >= 750) {
                            break;
                        }
                        moveDown();
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //向左
                    //防止坦克超出边界
                    if(getX() <= 0) {
                        break;
                    }
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }



            //然后随机的改变坦克方向
            setDirect((int)(Math.random() * 4));
            //一旦写并发程序，一定要考虑清楚，线程什么时候结束
            if(!isLive) {
                break; //退出线程
            }
        }
    }
}
