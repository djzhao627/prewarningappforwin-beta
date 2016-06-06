package com.prewarningapp.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class _PopupMenu extends JFrame{
    private JPopupMenu popupMenu;
    private JMenuItem copy = new JMenuItem("复制");
     
    public _PopupMenu(){
        super("右键子菜单");
         
        popupMenu = new JPopupMenu();
        popupMenu.add(copy);
         
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //如果点击右键  显示子菜单
                if(e.getButton() == MouseEvent.BUTTON3){
                    //获取相对于窗口的逻辑位置
                    Point p = e.getPoint();
                     
                    popupMenu.show(e.getComponent(), p.x, p.y);
                }
                 
                //否则不显示子菜单
                else{
                    popupMenu.setVisible(false);
                }
            }
        });
         
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
    }
     
    public static void main (String[] args) {
        new _PopupMenu();
    }
}