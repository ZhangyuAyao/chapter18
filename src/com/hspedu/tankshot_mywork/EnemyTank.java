package com.hspedu.tankshot_mywork;

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
    Vector<Shot> shots = new Vector<>();

}
