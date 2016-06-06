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
 * @author ��ˮ
 * 
 * 
 */

public class JTreeDemo extends JFrame implements TreeSelectionListener {

	private static final long serialVersionUID = -6209666185329417356L;

	// �����

	protected JTree jTree;

	public JTreeDemo() {

		init();

	}

	// ��ʼ��

	private void init() {

		jTree = new JTree();

		// ���ڵ���������

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("��������");

		DefaultMutableTreeNode shu = new DefaultMutableTreeNode("���");

		DefaultMutableTreeNode wei = new DefaultMutableTreeNode("κ��");

		DefaultMutableTreeNode wu = new DefaultMutableTreeNode("���");

		shu.add(new DefaultMutableTreeNode("����"));

		shu.add(new DefaultMutableTreeNode("�ŷ�"));

		shu.add(new DefaultMutableTreeNode("����"));

		shu.add(new DefaultMutableTreeNode("��"));

		shu.add(new DefaultMutableTreeNode("����"));

		wei.add(new DefaultMutableTreeNode("����"));

		wei.add(new DefaultMutableTreeNode("��Τ"));

		wei.add(new DefaultMutableTreeNode("�ĺ"));

		wei.add(new DefaultMutableTreeNode("����"));

		wei.add(new DefaultMutableTreeNode("���"));

		wu.add(new DefaultMutableTreeNode("̫ʷ��"));

		wu.add(new DefaultMutableTreeNode("����"));

		wu.add(new DefaultMutableTreeNode("���"));

		wu.add(new DefaultMutableTreeNode("���"));

		wu.add(new DefaultMutableTreeNode("½ѷ"));

		root.add(shu);

		root.add(wei);

		root.add(wu);

		// ��������ģ��

		DefaultTreeModel model = new DefaultTreeModel(root);

		// ��������ģ��

		jTree.setModel(model);

		// չ��������

		for (int i = 0; i < jTree.getRowCount(); i++)

			jTree.expandRow(i);

		// ����¼�

		jTree.addTreeSelectionListener(this);

		// �������

		JScrollPane jScrollPane = new JScrollPane(jTree,

		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,

		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// ��������������

		jScrollPane.getViewport().add(jTree);

		// ��ӹ�����嵽������

		this.getContentPane().add(jScrollPane);

		this.setTitle("JTree���¼�����");

		this.pack();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		JTreeDemo demo = new JTreeDemo();

		demo.setVisible(true);

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		// ��ȡѡ��Ľڵ�

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree

		.getLastSelectedPathComponent();

		if (node.getLevel() == 0) {

			// ��ʾ��ʾ��Ϣ

			JOptionPane.showMessageDialog(null,

			node.getUserObject() + ": ��" + node.getChildCount() + "������");

		} else if (node.getLevel() == 1) {

			// ��ʾ��ʾ��Ϣ

			JOptionPane.showMessageDialog(null,

			node.getUserObject() + ": ��" + node.getChildCount() + "������");

		} else if (node.getLevel() == 2) {

			// ��ʾ��ʾ��Ϣ

			JOptionPane.showMessageDialog(null, node.getParent() + "����: " +

			node.getUserObject());

		}

	}

}