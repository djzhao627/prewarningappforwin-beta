package com.prewarningapp.test;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * 
 * @date 17/10/2012
 */
public class TestTable {

	public static void main(String[] args) throws Exception {

		final int rows = 30;

		final SizeFixedStackTableModel<Date> model = new SizeFixedStackTableModel<Date>(
				new SizeFixedStackTableModel.RecordRenderer<Date>() {

					private final DateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss:SSS");

					@Override
					public int getColumnCount() {

						return 2;
					}

					@Override
					public Object getValueAt(Date record, int column) {

						if (column == 0)
							return format.format(record);
						else if (column == 1)
							return record.getTime();
						else
							throw new IndexOutOfBoundsException(
									"Invalid column : " + column);
					}
				}, rows);

		final CountDownLatch cd = new CountDownLatch(1);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame f = new JFrame("Test Table Model");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JTable table = new JTable(model);
				f.getContentPane().add(table, BorderLayout.CENTER);
				f.setSize(800, table.getRowHeight() * rows + 50);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				cd.countDown();
			}
		});

		cd.await();

		while (true) {

			Thread.sleep(500);
			model.insert(new Date());
		}
	}
}