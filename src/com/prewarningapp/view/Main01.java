package com.prewarningapp.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import com.prewarningapp.model.Warning;
import com.prewarningapp.tools.Mysqlutil;
import com.prewarningapp.tools.SystemCount;

public class Main01 extends JFrame implements TreeSelectionListener, ActionListener, java.awt.event.MouseMotionListener,
		Runnable, SerialPortEventListener {

	private static final long serialVersionUID = -2266437280314385773L;

	private JPanel contentPane;

	private JScrollPane scrollPane, scrollPane_1;
	private JTree tree;
	private JLabel label;
	private JPanel panel;
	int width;
	int height;
	Image src, smallImg;
	ImageIcon icon;
	// private Object[][] SystemCount.oArr;
	private static JTable table;
	String sql = "";
	Timer timer;
	private JPopupMenu popupMenu;
	private static boolean isGetMousePosition = true;
	private static int mouseX;
	private static int mouseY;

	private static int position = 0;
	private JMenuItem menuItem;

	private JLabel point01;
	private JLabel point02;
	private JLabel point03;
	private JLabel point04;
	private JLabel point05;
	private JLabel point06;
	private JLabel point07;
	private JLabel point08;

	/** 端口获取器 */
	static CommPortIdentifier portId;
	/** 端口集合 */
	static Enumeration<?> portList;
	/** 串口号 */
	private static String comPort = "";

	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;
	private int numBytes;
	/** 时间格式化 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 初始化板子状态 */
	private String[] boardsState = { "00000000", "00000000", "00000000", "00000000", "00000000", "00000000", "00000000",
			"00000000", "00000000", "00000000", "00000000", "00000000", "00000000", "00000000", "00000000", "00000000",
			"00000000", "00000000", "00000000", "00000000" };

	/** 板子ID */
	private String boardID = "";

	// private JPopupMenu pointPopupMenu;
	//
	// private JMenuItem pointMenuItem;

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);// 去掉标题栏中的设置按钮
		} catch (Exception e) {
			System.out.print("Look And Feel Exception");
			System.exit(0);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File file = new File("C:\\config\\port.txt");
					if (file.isFile() && file.exists()) {
						FileInputStream fis = new FileInputStream(file);
						InputStreamReader isr = new InputStreamReader(fis);
						BufferedReader br = new BufferedReader(isr);
						String line = null;
						boolean isFound = false;
						while ((line = br.readLine()) != null) {
							if (line.startsWith("COM:")) {
								isFound = true;
								comPort = line.substring(4);
								break;
							}
						}
						br.close();
						if (!isFound) {
							JOptionPane.showMessageDialog(null, "配置信息不正确，请检查！");
							System.exit(0);
						}
						Main01 frame = new Main01();
						frame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "无法加载配置文件，请检查！");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// thread t = new thread();
		// t.run();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Main01() throws IOException {
		Mysqlutil mysql = new Mysqlutil();
		this.setResizable(false);
		setTitle("乐维-预警平台");
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 得到屏幕的尺寸
		int x = screenSize.width; // 宽度
		int y = screenSize.height; // 高度
		setBounds((x - 1080) / 2, (y - 750) / 2, 1080, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);

		tree = new JTree();
		tree.addTreeSelectionListener(this);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("报警位置");
		DefaultMutableTreeNode xin = new DefaultMutableTreeNode("新厂区");
		DefaultMutableTreeNode yi = new DefaultMutableTreeNode("一号厂区");
		DefaultMutableTreeNode yi_1 = new DefaultMutableTreeNode("一号厂区1");
		DefaultMutableTreeNode er = new DefaultMutableTreeNode("二号厂区");
		xin.add(new DefaultMutableTreeNode("AA线"));
		xin.add(new DefaultMutableTreeNode("AP线"));
		xin.add(new DefaultMutableTreeNode("AMD线"));
		yi.add(yi_1);
		yi.add(new DefaultMutableTreeNode("AMD线"));
		yi_1.add(new DefaultMutableTreeNode("包装线"));
		yi_1.add(new DefaultMutableTreeNode("产品线"));
		yi_1.add(new DefaultMutableTreeNode("包装线"));
		yi_1.add(new DefaultMutableTreeNode("产品线"));
		yi_1.add(new DefaultMutableTreeNode("包装线"));
		yi_1.add(new DefaultMutableTreeNode("产品线"));
		er.add(new DefaultMutableTreeNode("质检线"));
		er.add(new DefaultMutableTreeNode("生产线"));
		er.add(new DefaultMutableTreeNode("质检线"));
		er.add(new DefaultMutableTreeNode("生产线"));
		er.add(new DefaultMutableTreeNode("质检线"));
		er.add(new DefaultMutableTreeNode("生产线"));
		er.add(new DefaultMutableTreeNode("质检线"));
		er.add(new DefaultMutableTreeNode("生产线"));
		er.add(new DefaultMutableTreeNode("质检线"));
		er.add(new DefaultMutableTreeNode("生产线"));
		root.add(xin);
		root.add(yi);
		root.add(er);
		// 树的数据模型o
		DefaultTreeModel model = new DefaultTreeModel(root);
		// 设置数据模型
		tree.setModel(model);
		// 展开所有树
		for (int i = 0; i < tree.getRowCount(); i++)
			tree.expandRow(i);

		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		cellRenderer.setOpenIcon(new ImageIcon("..\\icons\\open.gif"));
		cellRenderer.setClosedIcon(new ImageIcon("..\\icons\\close.gif"));
		contentPane.setLayout(null);

		scrollPane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 20, 179, 620);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBounds(209, 20, 799, 418);
		contentPane.add(panel);
		panel.setLayout(null);

		label = new JLabel("");
		label.addMouseMotionListener(this);
		label.setBounds(10, 10, 779, 403);
		src = ImageIO.read(getClass().getClassLoader().getResource("factory1.jpg"));
		width = 775; // 假设要缩小到600点像素
		height = src.getHeight(null) * 775 / src.getWidth(null);// 按比例，将高度缩减
		smallImg = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);// 缩小
		icon = new ImageIcon(smallImg);

		point01 = new JLabel();
		point01.setBounds(10, 10, 30, 15);
		point01.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point02 = new JLabel();
		point02.setBounds(10, 10, 30, 15);
		point02.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point03 = new JLabel();
		point03.setBounds(10, 10, 30, 15);
		point03.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point04 = new JLabel();
		point04.setBounds(10, 10, 30, 15);
		point04.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point05 = new JLabel();
		point05.setBounds(10, 10, 30, 15);
		point05.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point06 = new JLabel();
		point06.setBounds(10, 10, 30, 15);
		point06.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point07 = new JLabel();
		point07.setBounds(10, 10, 30, 15);
		point07.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		point08 = new JLabel();
		point08.setBounds(10, 10, 30, 15);
		point08.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));

		panel.add(point01);
		panel.add(point02);
		panel.add(point03);
		panel.add(point04);
		panel.add(point05);
		panel.add(point06);
		panel.add(point07);
		panel.add(point08);

		label.setIcon(icon);
		panel.add(label);

		popupMenu = new JPopupMenu();
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				isGetMousePosition = true;
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				isGetMousePosition = true;
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				isGetMousePosition = false;
			}
		});

		addPopup(label, popupMenu);
		menuItem = new JMenuItem("\u6DFB\u52A0\u9884\u8B66\u70B9");
		menuItem.addActionListener(new ActionListener() {

			private JLabel newOne;

			public void actionPerformed(ActionEvent e) {
				newOne = new JLabel();
				newOne.setBounds(mouseX, mouseY, 30, 15);
				newOne.setIcon(new ImageIcon(Main01.class.getResource("/img/redpoint.png")));
				newOne.setText(position + 1 + "");
				newOne.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							panel.remove(e.getComponent());
							panel.updateUI();
							position--;
						}
					}

				});
				panel.add(newOne);
				panel.setComponentZOrder(newOne, position++);
				panel.setComponentZOrder(label, position);
				panel.updateUI();
			}
		});
		popupMenu.add(menuItem);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(209, 448, 792, 189);
		contentPane.add(scrollPane_1);

		table = new JTable();
		sql = "select * from warningtb";
		ArrayList<Warning> warningList = mysql.getWarningList(sql);
		int k = warningList.size();
		SystemCount.oArr = new Object[k + 1][7];
		for (int i = 0; i < warningList.size(); i++) {
			SystemCount.oArr[i][0] = warningList.get(i).getWarningPlace();
			SystemCount.oArr[i][1] = warningList.get(i).getWarningTime();
			SystemCount.oArr[i][2] = warningList.get(i).getBoardID();
			SystemCount.oArr[i][3] = warningList.get(i).getWarningType();
			SystemCount.oArr[i][4] = warningList.get(i).getWarningHandle();
			SystemCount.oArr[i][5] = warningList.get(i).getWarningHTime();
			SystemCount.oArr[i][6] = warningList.get(i).getWarningHPeople();
		}
		SystemCount.oArr[k][0] = "";
		SystemCount.oArr[k][1] = "";
		SystemCount.oArr[k][2] = "";
		SystemCount.oArr[k][3] = "";
		SystemCount.oArr[k][4] = "";
		SystemCount.oArr[k][5] = "总计";
		SystemCount.oArr[k][6] = k + "条报警信息";
		table.setModel(new DefaultTableModel(SystemCount.oArr,
				new String[] { "报警地点", "报警时间", "板块ID", "报警类型", "处理情况", "处理时间", "处理人", }));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_1.setViewportView(table);

		timer = new Timer(10000, this);
		timer.start();

		receiveData().execute();

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		String str_pic = "";
		if (node.getLevel() == 1) {
			String str = node.getUserObject().toString();
			if (str.equals("新厂区")) {
				str_pic = "factory2.jpg";
			} else if (str.equals("一号厂区")) {
				str_pic = "factory3.jpg";
			} else if (str.equals("二号厂区")) {
				str_pic = "factory4.jpg";
			}
			try {
				src = ImageIO.read(getClass().getClassLoader().getResource(str_pic));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			width = 775; // 假设要缩小到600点像素
			height = src.getHeight(null) * 775 / src.getWidth(null);// 按比例，将高度缩减
			smallImg = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);// 缩小
			icon = new ImageIcon(smallImg);
			label.setIcon(icon);

		}
	}

	SwingWorker<Void, Void> receiveData() {
		SwingWorker<Void, Void> receive = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() {

				// 获取存在的端口
				portList = CommPortIdentifier.getPortIdentifiers();
				while (portList.hasMoreElements()) {
					portId = (CommPortIdentifier) portList.nextElement();
					if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
						if (portId.getName().equals(comPort)) {
							SimpleRead();
						}
					}
				}

				return null;
			}
		};
		return receive;
	}

	@Override
	public void actionPerformed(
			ActionEvent e) {/*
							 * if (e.getSource() == timer) { Mysqlutil mysql =
							 * new Mysqlutil(); String sql =
							 * "select * from warningtb"; ArrayList<Warning>
							 * warningList = mysql.getWarningList(sql); int k =
							 * warningList.size(); SystemCount.oArr = new
							 * Object[k + 1][7]; for (int i = 0; i <
							 * warningList.size(); i++) { SystemCount.oArr[i][0]
							 * = warningList.get(i).getWarningPlace();
							 * SystemCount.oArr[i][1] =
							 * warningList.get(i).getWarningTime();
							 * SystemCount.oArr[i][2] =
							 * warningList.get(i).getBoardID();
							 * SystemCount.oArr[i][3] =
							 * warningList.get(i).getWarningType();
							 * SystemCount.oArr[i][4] =
							 * warningList.get(i).getWarningHandle();
							 * SystemCount.oArr[i][5] =
							 * warningList.get(i).getWarningHTime();
							 * SystemCount.oArr[i][6] =
							 * warningList.get(i).getWarningHPeople(); }
							 * SystemCount.oArr[k][0] = "";
							 * SystemCount.oArr[k][1] = "";
							 * SystemCount.oArr[k][2] = "";
							 * SystemCount.oArr[k][3] = "";
							 * SystemCount.oArr[k][4] = "";
							 * SystemCount.oArr[k][5] = "总计";
							 * SystemCount.oArr[k][6] = k + "条报警信息"; //
							 * System.out.println("数据加载完成"); table.setModel(new
							 * DefaultTableModel(SystemCount.oArr, new String[]
							 * { "报警地点", "报警时间", "板块ID", "报警类型", "处理情况", "处理时间",
							 * "处理人", })); }
							 * 
							 */

	}

	public void SimpleRead() {
		try {
			serialPort = (SerialPort) portId.open("Reader", 1000);
			System.out.println("OK");
		} catch (PortInUseException e) {
			JOptionPane.showMessageDialog(null, comPort + "端口被占用，请检查！");
			System.exit(0);
		}
		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
		}
		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		readThread = new Thread(this);
		readThread.start();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					
				}
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (isGetMousePosition) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[16];
			try {
				while (inputStream.available() > 0) {
					numBytes = inputStream.read(readBuffer);
					// System.out.println(numBytes);
				}
				String reader = (new String(readBuffer, 0, numBytes)).trim();
				// for instance : 2E 7B 05 05 01 00 00 00
				if (reader.startsWith("2E7B05") && reader.length() == 16) {
					// 获取板号
					int board = Integer.valueOf(reader.substring(6, 8), 16);
					// 转为二进制
					String key = "00000000" + Integer.toBinaryString(Integer.valueOf(reader.substring(8, 10), 16));
					key = key.substring(key.length() - 8, key.length());
					char[] keys = key.toCharArray();
					char[] boardKeys = boardsState[board - 1].toCharArray();
					boolean isSame = true;
					// TODO 修改这边
					for (int i = 0; i < 8; i++) {
						if (keys[i] != boardKeys[i]) {
							isSame = false;
							String state = "按下";
							if (keys[i] == '1') {
								state = "按下";
							} else {
								state = "抬起";
							}
							// 按钮报警 ,更新UI
							// model.addRow(new String[] { board + "号板，按钮：" + (8
							// - i), state, sdf.format(new Date()) });
							System.out.println(board + ">>>>>");
						}
					}
					// 更新源状态
					if (!isSame) {
						boardsState[board - 1] = key;
					}

					// 模块上线监控
					if (!boardID.contains("<" + board + ">")) {
						boardID += "<" + board + ">";
						// list.add(" " + board);
						System.out.println(boardID + "<<<<<");
					}
				}
			} catch (IOException e) {
			}
			break;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}
}
