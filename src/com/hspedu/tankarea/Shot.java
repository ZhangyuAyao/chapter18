package com.hspedu.tankarea;

/**
 * @author: bytedance
 * @date: 2022/1/21
 * @description:
 */
public class Shot implements Runnable {
    int x; //子弹x坐标
    int y; //子弹y坐标
    int direct = 0; //子弹方向
    int speed = 2; //子弹的速度
    boolean isLive = true; // 子弹是否还存活

    //构造器


    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击行为
        while (true) {
            //休眠 50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0: //上
                    y -= speed;
                    break;
                case 1: //右
                    x += speed;
                    break;
                case 2: //下
                    y += speed;
                    break;
                case 3: //左
                    x -= speed;
                    break;
            }
            //老师测试，这里我们输出x，y的坐标
            System.out.println(Thread.currentThread().getName() + "子弹坐标：(" + x + ", " + y + ")");
            // 当子弹移动到面板边界的时候，就应该销毁（把启动的子弹的线程销毁）
            // 当子弹碰到敌人坦克时，也应该结束该线程
            if(!(x >= 0 && x <= 1000 && y >=0 && y <=750 && isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
