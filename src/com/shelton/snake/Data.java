package com.shelton.snake;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {

    //广告栏图片   URL
    public static URL headerURL = Data.class.getResource("/sources/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //蛇头
    public static URL upURL = Data.class.getResource("/sources/up.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static URL downURL = Data.class.getResource("/sources/down.png");
    public static ImageIcon down = new ImageIcon(downURL);
    public static URL leftURL = Data.class.getResource("/sources/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);
    public static URL rightURL = Data.class.getResource("/sources/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);
    public static URL bodyURL = Data.class.getResource("/sources/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    //食物
    public static URL foodURL = Data.class.getResource("/sources/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
    public static URL food2URL = Data.class.getResource("/sources/food2.png");
    public static ImageIcon food2 = new ImageIcon(food2URL);
    public static URL food3URL = Data.class.getResource("/sources/food3.png");
    public static ImageIcon food3 = new ImageIcon(food3URL);






}