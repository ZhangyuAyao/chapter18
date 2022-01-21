package com.hspedu.tankgame4;

import javax.swing.*;

public class HspTankGame04 extends JFrame {//JFrame就是面板

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {

        HspTankGame04 hspTankGame01 = new HspTankGame04();

    }

    public HspTankGame04() {
        mp = new MyPanel();

        //启动mp的线程，让其不停地重绘
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//把面板加入到绘图区，里面会自动调用paint方法
        this.setSize(1200, 850);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
