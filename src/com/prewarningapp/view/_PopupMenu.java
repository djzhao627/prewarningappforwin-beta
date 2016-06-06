package com.prewarningapp.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class _PopupMenu extends JFrame{
    private JPopupMenu popupMenu;
    private JMenuItem copy = new JMenuItem("����");
     
    public _PopupMenu(){
        super("�Ҽ��Ӳ˵�");
         
        popupMenu = new JPopupMenu();
        popupMenu.add(copy);
         
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //�������Ҽ�  ��ʾ�Ӳ˵�
                if(e.getButton() == MouseEvent.BUTTON3){
                    //��ȡ����ڴ��ڵ��߼�λ��
                    Point p = e.getPoint();
                     
                    popupMenu.show(e.getComponent(), p.x, p.y);
                }
                 
                //������ʾ�Ӳ˵�
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