package com.hspedu.tankarea;

import javax.swing.*;

public class HspTankGame04_area extends JFrame {//JFrame就是面板

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {

        HspTankGame04_area hspTankGame01 = new HspTankGame04_area();

    }

    public HspTankGame04_area() {
        mp = new MyPanel();

        //启动mp的线程，让其不停地重绘
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//把面板加入到绘图区，里面会自动调用paint方法
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
