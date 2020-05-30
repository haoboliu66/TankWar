package com.andy.tank;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();
//        JPanel panel = new JPanel();
//
//        // 创建一个按钮
//        final JButton btn = new JButton("测试按钮");
//
//        // 添加按钮的点击事件监听器
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 获取到的事件源就是按钮本身
//                // JButton btn = (JButton) e.getSource();
//
//                System.out.println("按钮被点击");
//            }
//        });
//        panel.add(btn);
//        tf.setContentPane(panel);

        while (true) {
            Thread.sleep(50);
            tf.repaint(); //invoke paint()
        }


    }
}
