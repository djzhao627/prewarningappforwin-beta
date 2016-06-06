package com.prewarningapp.test;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
 
 
/**
 *
 * @date   17/10/2012
 */
public class SizeFixedStackTableModel<R> extends AbstractTableModel {
   
  public static interface RecordRenderer<R> {
     
    int getColumnCount();
     
    /**
     * 
     * @param record
     * @param column
     * @return 
     * @throws IndexOutOfBoundsException when column is not valid 
     *         {@code ( column < 0 || column >= getColumnCount() )}
     */
    Object getValueAt(R record, int column);
  }
   
  private final RecordRenderer<? super R> recordRenderer;
  private volatile int size;
  private List<R> records;
   
  public SizeFixedStackTableModel(RecordRenderer<? super R> recordRenderer,
                              int size) {
     
    if( recordRenderer == null )
      throw new NullPointerException();
     
    if( size < 0 )
      throw new IllegalArgumentException();
     
    this.recordRenderer = recordRenderer;
    this.size = size;
     
    this.records = new LinkedList<R>();
  }
   
  @Override
  public int getRowCount() {
     
    assert SwingUtilities.isEventDispatchThread();
     
    return Math.min(size, records.size());
  }
 
  @Override
  public int getColumnCount() {
     
    assert SwingUtilities.isEventDispatchThread();
     
    return recordRenderer.getColumnCount();
  }
 
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
     
    assert SwingUtilities.isEventDispatchThread();
     
    // potentially throws IndexOutOfBoundsException
    R record = records.get(rowIndex); 
    return recordRenderer.getValueAt(record, columnIndex);
  }
   
  public void setSize(int size) {
     
    if( size < 0 )
      throw new IllegalArgumentException("Invalid table model size: " + size);
     
    if( this.size == size )
      return;
     
    this.size = size;
    fireTableDataChanged();
  }
   
  public void insert(final R record) {
     
    if( SwingUtilities.isEventDispatchThread() ) {
       
      records.add(0, record);
      fireTableDataChanged();
    }
    else {
       
      SwingUtilities.invokeLater(new Runnable() {
 
        @Override
        public void run() {
           
          insert(record);
        }
      });
    }
  }
}