package com.shelton.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {

        //1.绘制一个静态窗口
        JFrame frame = new JFrame("贪吃蛇");

        //设置界面大小，弹出窗口坐标(x,y)；窗口大小(width,height)
        frame.setBounds(300,120,625,470);
        frame.setResizable(false);//窗口大小不可改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件

        //2.添加游戏操作面板和蛇
        frame.add(new GamePanel());

        frame.setVisible(true);//展现窗口
    }
}
