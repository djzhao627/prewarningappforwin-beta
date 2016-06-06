package com.prewarningapp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.prewarningapp.model.Board;
import com.prewarningapp.tools.Mysqlutil;

public class PositionSetting extends JFrame {

	private JPanel contentPane;
	private JTextField point01X;
	private JTextField point01Y;
	private JTextField point02X;
	private JTextField point02Y;
	private JTextField point03X;
	private JTextField point03Y;
	private JTextField point04X;
	private JTextField point04Y;
	private JTextField point05X;
	private JTextField point05Y;
	private JTextField point06X;
	private JTextField point06Y;
	private JTextField point07X;
	private JTextField point07Y;
	private JTextField point08X;
	private JTextField point08Y;

	Mysqlutil mysql = new Mysqlutil();
	public static int boardID = 1;
	
	private static PositionSetting ps = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);// 去掉标题栏中的设置按钮
					PositionSetting frame = new PositionSetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static PositionSetting getScreen(int id){
		boardID = id;
		if(ps == null){
			ps = new PositionSetting();
		}
		return ps;
	}
	
	/**
	 * Create the frame.
	 */
	private PositionSetting() {
		setTitle("\u8BBE\u7F6E\u62A5\u8B66\u70B9");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BBE\u7F6E\u70B9");
		label.setForeground(Color.LIGHT_GRAY);
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 31, 551, 2);
		contentPane.add(separator);

		JLabel label1 = new JLabel("\u70B901     X:");
		label1.setBounds(10, 48, 66, 15);
		contentPane.add(label1);

		point01X = new JTextField();
		point01X.setBounds(75, 45, 40, 21);
		contentPane.add(point01X);
		point01X.setColumns(10);

		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(125, 48, 21, 15);
		contentPane.add(lblY);

		point01Y = new JTextField();
		point01Y.setColumns(10);
		point01Y.setBounds(135, 45, 40, 21);
		contentPane.add(point01Y);

		JLabel lblX = new JLabel("\u70B902     X:");
		lblX.setBounds(10, 92, 66, 15);
		contentPane.add(lblX);

		point02X = new JTextField();
		point02X.setColumns(10);
		point02X.setBounds(75, 89, 40, 21);
		contentPane.add(point02X);

		JLabel label_2 = new JLabel("Y:");
		label_2.setBounds(125, 92, 21, 15);
		contentPane.add(label_2);

		point02Y = new JTextField();
		point02Y.setColumns(10);
		point02Y.setBounds(135, 89, 40, 21);
		contentPane.add(point02Y);

		JLabel lblX_1 = new JLabel("\u70B903     X:");
		lblX_1.setBounds(10, 135, 66, 15);
		contentPane.add(lblX_1);

		point03X = new JTextField();
		point03X.setColumns(10);
		point03X.setBounds(75, 132, 40, 21);
		contentPane.add(point03X);

		JLabel label_4 = new JLabel("Y:");
		label_4.setBounds(125, 135, 21, 15);
		contentPane.add(label_4);

		point03Y = new JTextField();
		point03Y.setColumns(10);
		point03Y.setBounds(135, 132, 40, 21);
		contentPane.add(point03Y);

		JLabel lblX_2 = new JLabel("\u70B904     X:");
		lblX_2.setBounds(10, 178, 66, 15);
		contentPane.add(lblX_2);

		point04X = new JTextField();
		point04X.setColumns(10);
		point04X.setBounds(75, 175, 40, 21);
		contentPane.add(point04X);

		JLabel label_6 = new JLabel("Y:");
		label_6.setBounds(125, 178, 21, 15);
		contentPane.add(label_6);

		point04Y = new JTextField();
		point04Y.setColumns(10);
		point04Y.setBounds(135, 175, 40, 21);
		contentPane.add(point04Y);

		JLabel lblX_3 = new JLabel("\u70B905     X:");
		lblX_3.setBounds(241, 46, 66, 15);
		contentPane.add(lblX_3);

		point05X = new JTextField();
		point05X.setColumns(10);
		point05X.setBounds(306, 43, 40, 21);
		contentPane.add(point05X);

		JLabel label_8 = new JLabel("Y:");
		label_8.setBounds(356, 46, 21, 15);
		contentPane.add(label_8);

		point05Y = new JTextField();
		point05Y.setColumns(10);
		point05Y.setBounds(366, 43, 40, 21);
		contentPane.add(point05Y);

		JLabel lblX_4 = new JLabel("\u70B906     X:");
		lblX_4.setBounds(241, 95, 66, 15);
		contentPane.add(lblX_4);

		point06X = new JTextField();
		point06X.setColumns(10);
		point06X.setBounds(306, 92, 40, 21);
		contentPane.add(point06X);

		JLabel label_10 = new JLabel("Y:");
		label_10.setBounds(356, 95, 21, 15);
		contentPane.add(label_10);

		point06Y = new JTextField();
		point06Y.setColumns(10);
		point06Y.setBounds(366, 92, 40, 21);
		contentPane.add(point06Y);

		JLabel lblX_5 = new JLabel("\u70B907     X:");
		lblX_5.setBounds(241, 138, 66, 15);
		contentPane.add(lblX_5);

		point07X = new JTextField();
		point07X.setColumns(10);
		point07X.setBounds(306, 135, 40, 21);
		contentPane.add(point07X);

		JLabel label_12 = new JLabel("Y:");
		label_12.setBounds(356, 138, 21, 15);
		contentPane.add(label_12);

		point07Y = new JTextField();
		point07Y.setColumns(10);
		point07Y.setBounds(366, 135, 40, 21);
		contentPane.add(point07Y);

		JLabel lblX_6 = new JLabel("\u70B908     X:");
		lblX_6.setBounds(241, 181, 66, 15);
		contentPane.add(lblX_6);

		point08X = new JTextField();
		point08X.setColumns(10);
		point08X.setBounds(306, 178, 40, 21);
		contentPane.add(point08X);

		JLabel label_14 = new JLabel("Y:");
		label_14.setBounds(356, 181, 21, 15);
		contentPane.add(label_14);

		point08Y = new JTextField();
		point08Y.setColumns(10);
		point08Y.setBounds(366, 178, 40, 21);
		contentPane.add(point08Y);

		JButton button = new JButton("提交变更");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO
				int x = 0, y = 0;
				// 报警点
				int point = 1;
				String sql = "";
				// 01
				x = Integer.parseInt(point01X.getText().toString());
				y = Integer.parseInt(point01Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 02
				x = Integer.parseInt(point02X.getText().toString());
				y = Integer.parseInt(point02Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 03
				x = Integer.parseInt(point03X.getText().toString());
				y = Integer.parseInt(point03Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 04
				x = Integer.parseInt(point04X.getText().toString());
				y = Integer.parseInt(point04Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 05
				x = Integer.parseInt(point05X.getText().toString());
				y = Integer.parseInt(point05Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 06
				x = Integer.parseInt(point06X.getText().toString());
				y = Integer.parseInt(point06Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 07
				x = Integer.parseInt(point07X.getText().toString());
				y = Integer.parseInt(point07Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				// 08
				x = Integer.parseInt(point08X.getText().toString());
				y = Integer.parseInt(point08Y.getText().toString());
				sql = getUpdateSQL(x, y, boardID, point++);
				mysql.uodatePosition(sql);
				JOptionPane.showMessageDialog(null, "更新完毕");
			}

			private String getUpdateSQL(int x, int y, int boardID, int point) {
				String sql = "update boardtb set posX = " + x + ", posY = " + y + " where position = " + point
						+ " and boardID = " + boardID;
				return sql;
			}
		});
		button.setBounds(313, 224, 93, 23);
		contentPane.add(button);

		inintPosition();
	}

	private void inintPosition() {
		String sql = "select posX, posY, position from boardtb where boardID = "+boardID+" order by position";
		ArrayList<Board> list = mysql.getBoardByBoardId(sql);
		if(list.size()<=0){
			JOptionPane.showMessageDialog(null, "出现异常！");
			this.dispose();
		}
		int index = 0;
		// 01
		point01X.setText(list.get(index).getPosX()+"");
		point01Y.setText(list.get(index++).getPosY()+"");
		// 02
		point02X.setText(list.get(index).getPosX()+"");
		point02Y.setText(list.get(index++).getPosY()+"");
		// 03
		point03X.setText(list.get(index).getPosX()+"");
		point03Y.setText(list.get(index++).getPosY()+"");
		// 04
		point04X.setText(list.get(index).getPosX()+"");
		point04Y.setText(list.get(index++).getPosY()+"");
		// 05
		point05X.setText(list.get(index).getPosX()+"");
		point05Y.setText(list.get(index++).getPosY()+"");
		// 06
		point06X.setText(list.get(index).getPosX()+"");
		point06Y.setText(list.get(index++).getPosY()+"");
		// 07
		point07X.setText(list.get(index).getPosX()+"");
		point07Y.setText(list.get(index++).getPosY()+"");
		// 08
		point08X.setText(list.get(index).getPosX()+"");
		point08Y.setText(list.get(index++).getPosY()+"");
	}
}
