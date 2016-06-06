package com.prewarningapp.test;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

import javax.swing.JTree;

import javax.swing.ScrollPaneConstants;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.event.TreeSelectionEvent;

import javax.swing.event.TreeSelectionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.tree.DefaultTreeModel;

/**
 * 
 * 
 * 
 * @author 逝水
 * 
 * 
 */

public class JTreeDemo extends JFrame implements TreeSelectionListener {

	private static final long serialVersionUID = -6209666185329417356L;

	// 树组件

	protected JTree jTree;

	public JTreeDemo() {

		init();

	}

	// 初始化

	private void init() {

		jTree = new JTree();

		// 树节点的相关数据

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("三国名将");

		DefaultMutableTreeNode shu = new DefaultMutableTreeNode("蜀国");

		DefaultMutableTreeNode wei = new DefaultMutableTreeNode("魏国");

		DefaultMutableTreeNode wu = new DefaultMutableTreeNode("吴国");

		shu.add(new DefaultMutableTreeNode("关羽"));

		shu.add(new DefaultMutableTreeNode("张飞"));

		shu.add(new DefaultMutableTreeNode("赵云"));

		shu.add(new DefaultMutableTreeNode("马超"));

		shu.add(new DefaultMutableTreeNode("黄忠"));

		wei.add(new DefaultMutableTreeNode("张辽"));

		wei.add(new DefaultMutableTreeNode("典韦"));

		wei.add(new DefaultMutableTreeNode("夏侯惇"));

		wei.add(new DefaultMutableTreeNode("许褚"));

		wei.add(new DefaultMutableTreeNode("徐晃"));

		wu.add(new DefaultMutableTreeNode("太史慈"));

		wu.add(new DefaultMutableTreeNode("甘宁"));

		wu.add(new DefaultMutableTreeNode("孙策"));

		wu.add(new DefaultMutableTreeNode("周瑜"));

		wu.add(new DefaultMutableTreeNode("陆逊"));

		root.add(shu);

		root.add(wei);

		root.add(wu);

		// 树的数据模型

		DefaultTreeModel model = new DefaultTreeModel(root);

		// 设置数据模型

		jTree.setModel(model);

		// 展开所有树

		for (int i = 0; i < jTree.getRowCount(); i++)

			jTree.expandRow(i);

		// 添加事件

		jTree.addTreeSelectionListener(this);

		// 滚动面板

		JScrollPane jScrollPane = new JScrollPane(jTree,

		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,

		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// 添加树到滚动面板

		jScrollPane.getViewport().add(jTree);

		// 添加滚动面板到窗口中

		this.getContentPane().add(jScrollPane);

		this.setTitle("JTree的事件例子");

		this.pack();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		JTreeDemo demo = new JTreeDemo();

		demo.setVisible(true);

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		// 获取选择的节点

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree

		.getLastSelectedPathComponent();

		if (node.getLevel() == 0) {

			// 显示提示信息

			JOptionPane.showMessageDialog(null,

			node.getUserObject() + ": 共" + node.getChildCount() + "个国家");

		} else if (node.getLevel() == 1) {

			// 显示提示信息

			JOptionPane.showMessageDialog(null,

			node.getUserObject() + ": 共" + node.getChildCount() + "名名将");

		} else if (node.getLevel() == 2) {

			// 显示提示信息

			JOptionPane.showMessageDialog(null, node.getParent() + "名将: " +

			node.getUserObject());

		}

	}

}