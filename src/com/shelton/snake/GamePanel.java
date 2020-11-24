package com.shelton.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//继承JPanel实现操作面板的展示
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length;//小蛇长度
    int[] snakex = new int[700];
    int[] snakey = new int[700];
    String fx;
    boolean isStart;//游戏是否开始
    boolean isfail;//小蛇是否死亡
    int score;//分数
    int degree;//难度

    //食物坐标
    int foodx;
    int foody;
    int food2x;
    int food2y;
    int food3x;
    int food3y;

    Random random = new Random();

    Timer timer = new Timer(120,this);

    //构造器初始化
    public GamePanel() {
        init();
        newFood();
        isStart = false;
        isfail = false;
        timer.start();

        //获取键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    //小蛇最初的状态
    public void init() {
        length = 3;
        snakex[0] = 460; snakey[0] = 340;
        snakex[1] = 480; snakey[1] = 340;
        snakex[2] = 500; snakey[2] = 340;
        fx = "L";
        score = 0;
        degree = 0;
        timer.setDelay(120);
    }

    //食物坐标
    public void newFood() {

        if (random.nextInt(100)>=90 && degree>14) {
            //减速果实10%概率且难度14以上才会出现
            food3x = 40 + 20 * random.nextInt(28);
            food3y = 120 + 20 * random.nextInt(15);
            foodx = 700;
            foody = 700;
            food2x = 700;
            food2y = 700;
        } else if (random.nextInt(100)>=80) {
            //加倍果实10%概率
            food2x = 40 + 20 * random.nextInt(28);
            food2y = 120 + 20 * random.nextInt(15);
            foodx = 700;
            foody = 700;
            food3x = 700;
            food3y = 700;
        } else {
            //普通果实80%概率
            foodx = 40 + 20 * random.nextInt(28);
            foody = 120 + 20 * random.nextInt(15);
            food2x = 700;
            food2y = 700;
            food3x = 700;
            food3y = 700;
        }
    }

    //游戏难度
    public void difficulty() {
        if (degree>14) {
            timer.setDelay(60);
        } else if (degree > 9) {
            timer.setDelay(70);
        } else if (degree <= 9){
            timer.setDelay(120-degree*5);
        }
    }

    //绘制界面、蛇、食物
    @Override
    protected void paintComponent(Graphics g) {
        //Graphics——画笔
        super.paintComponent(g);//清屏
        this.setBackground(Color.gray);//设置背景颜色


        Data.header.paintIcon(this,g,20,20);//绘制广告栏
        g.fillRect(20,100,580,320);//绘制游戏框

        //绘制静态小蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this,g,snakex[0],snakey[0]);//蛇头
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this,g,snakex[0],snakey[0]);//蛇头
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this,g,snakex[0],snakey[0]);//蛇头
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this,g,snakex[0],snakey[0]);//蛇头
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakex[i],snakey[i]);//蛇身
        }

        //绘制食物
        //成长果实
        Data.food.paintIcon(this,g,foodx,foody);
        Data.food2.paintIcon(this,g,food2x,food2y);
        Data.food3.paintIcon(this,g,food3x,food3y);

        //绘制得分与蛇长
        g.setFont(new Font("微软雅黑",Font.BOLD,14));
        g.setColor(Color.white);
        g.drawString("长度："+length,510,42);
        g.setFont(new Font("微软雅黑",Font.BOLD,14));
        g.setColor(Color.white);
        g.drawString("分数："+score,510,67);

        //游戏提示
        if (!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("按下空格开始游戏",200,240);
        }

        //死亡提示
        if (isfail) {
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("游戏结束，按下空格重新开始",150,240);
        }
    }

    //监听键盘的输入
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下：未释放
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (isfail) {
                isfail = false;
                init();
                newFood();
            } else {
                isStart = !isStart;
                repaint();
            }
        }

        if (isStart) {
            if (key == KeyEvent.VK_UP) {
                if (fx.equals("L") || fx.equals("R")) {
                    fx = "U";
                }
            } else if (key == KeyEvent.VK_DOWN) {
                if (fx.equals("L") || fx.equals("R")) {
                    fx = "D";
                }
            } else if (key == KeyEvent.VK_LEFT) {
                if (fx.equals("U") || fx.equals("D")) {
                    fx = "L";
                }
            } else if (key == KeyEvent.VK_RIGHT) {
                if (fx.equals("U") || fx.equals("D")) {
                    fx = "R";
                }
            }
        }

    }

    //Timer定时器
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isfail==false) {

            //身体的移动
            for (int i = length; i > 0; i--) {
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];
            }

            //通过方向控制头移动
            if (fx.equals("L")) {
                snakex[0] -= 20;
                if (snakex[0] < 20) {
                    isfail = true;
                    snakex[0] = 20;
                    for (int i = 1; i < length-1; i++) {
                        snakex[i] = snakex[i+1];
                    }
                    snakex[length-1] += 20;
                }
            } else if (fx.equals("R")) {
                snakex[0] += 20;
                if (snakex[0] > 580) {
                    isfail = true;
                    snakex[0] = 580;
                    for (int i = 1; i < length-1; i++) {
                        snakex[i] = snakex[i+1];
                    }
                    snakex[length-1] -= 20;
                }
            } else if (fx.equals("U")) {
                snakey[0] -= 20;
                if (snakey[0] < 100) {
                    isfail = true;
                    //防止小蛇死亡时头部超出游戏框
                    snakey[0] = 100;
                    for (int i = 1; i < length-1; i++) {
                        snakey[i] = snakey[i+1];
                    }
                    snakey[length-1] += 20;
                }
            } else if (fx.equals("D")) {
                snakey[0] += 20;
                if (snakey[0] > 400) {
                    isfail = true;
                    snakey[0] = 400;
                    for (int i = 1; i < length-1; i++) {
                        snakey[i] = snakey[i+1];
                    }
                    snakey[length-1] -= 20;
                }
            }

            //吃到食物
            if ((snakex[0]==foodx && snakey[0]==foody)) {
                //吃到普通果实
                length++;
                score += 10;
                degree++;
                difficulty();
                newFood();
            }  else if (snakex[0]==food2x && snakey[0]==food2y) {
                //吃到加倍果实
                length += 1;
                score *= 2;
                degree++;
                difficulty();
                newFood();
            }  else if (snakex[0]==food3x && snakey[0]==food3y) {
                //吃到减速果实
                length += 1;
                score += 10;
                degree = 0;
                difficulty();
                newFood();
            }


            //死亡判断
            for (int i = 1; i < length; i++) {
                if (snakex[0]==snakex[i] && snakey[0]==snakey[i]) {
                    isfail = true;
                }
            }

            repaint();//刷新页面
        }
        timer.start();

    }




    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下：按下

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //键盘按下：释放
    }
}
