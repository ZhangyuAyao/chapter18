package com.hspedu.tankshot_mywork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 张宇
 * @version 1.0
 * 坦克大战的绘图区域
 */


public class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义我的坦克
    Hero hero = null;

    //定义敌人的坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(200, 200);
        hero.setSpeed(5);

        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);

            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }



    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出自己的坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

        //画出hero射击的子弹
        if(hero.shot != null && hero.shot.isLive == true) {
            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
            System.out.println("子弹被绘制");
        }

        //画出敌人的坦克，遍历vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
            //画出 enemyTank 所有子弹
            for(int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //绘制
                if(shot.isLive) {
                    g.draw3DRect(shot.x, shot.y, 1, 1, false);
                } else {
                    //从Vector 移除
                    enemyTank.shots.remove(j);
                }
            }
        }

        //判断自己的子弹是否击中敌方坦克，如果击中了敌方的坦克，如果击中了敌方的坦克，则将敌方的坦克移除
        for (int i = 0; i < enemyTanks.size(); i++) {
            //先判断自己发射的子弹是否存在
            if(hero.shot != null && hero.shot.isLive == true) {
                EnemyTank enemyTank = enemyTanks.get(i);
                //判断子弹是否击中了敌方的坦克
                if ((hero.shot.x > enemyTank.getX() && hero.shot.x < enemyTank.getX() + 40) &&
                        (hero.shot.y > enemyTank.getY() && hero.shot.y < enemyTank.getY() + 60)){
                    //击中之后，则将该坦克移除
                    enemyTanks.remove(i);
                }
            }
        }
    }

    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    //编写方法，画出坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type) {


        //根据不同类型的坦克，设置不同的颜色
        switch (type) {
            case 0: //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //自己的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克的方向，来绘制对应形状的坦克
        // direct 表示方向（0：向上 1：向右 2：向下 3 向左
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形的盖子
                g.drawLine(x + 20, y + 30, x + 20, y); //画出坦克的炮管
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上面的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下面的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形的盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20); //画出坦克的炮管
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形的盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60); //画出坦克的炮管
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上面的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下面的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形的盖子
                g.drawLine(x + 30, y + 20, x, y + 20); //画出坦克的炮管
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            //改变坦克的方向
            hero.setDirect(0);
            //修改坦克的坐标 y -= 1
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }

        //如果用户按下的是J，就发射
        if(e.getKeyCode() == KeyEvent.VK_J) {
            hero.shotEnemyTank();
        }
        //让面板重新绘制
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { //每隔 100毫秒，重绘区域
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
