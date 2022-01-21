package com.hspedu.tankmove_mywork;

/**
 * @author: bytedance
 * @date: 2022/1/21
 * @description:
 */
public class Bomb {
    int x, y; //炸弹的坐标
    int life = 9; //炸弹的生命周期
    boolean isLive = true; //是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown() {
        if(life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
