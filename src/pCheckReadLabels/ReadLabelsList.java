/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://stackoverflow.com/questions/18099717/how-to-add-jcheckbox-in-jtable
https://stackoverflow.com/questions/5594639/how-can-i-put-a-jcheckbox-on-a-jtable
https://coderanch.com/t/343795/java/Check-Box-JTable-header
https://www.programcreek.com/java-api-examples/index.php?class=javax.swing.table.DefaultTableModel&method=getDataVector
https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
https://docs.oracle.com/javase/tutorial/uiswing/examples/events/MouseEventDemoProject/src/events/MouseEventDemo.java
https://stackoverflow.com/questions/4795586/determine-which-jtable-cell-is-clicked
https://stackoverflow.com/questions/1378096/actionlistener-on-jlabel-or-jtable-cell?rq=1
 */
package pCheckReadLabels;

import javax.swing.*;
import java.text.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.border.*;
import pGlobals.globals;
//import pDataBase.*;
import pDialogs.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
//import static pDataBase.DAOFirst.DAOloggger;
import pDataBase1.*;
import pDataBase1.StaticConnectToDatabase;
import pDataBase1.StaticConnectToDatabaseException;
import pErrorCodes.ErrorCodes;


/**
 *
 * @author Andrzej
 */
public class ReadLabelsList extends JFrame implements  MouseListener ,ActionListener
{
    private javax.swing.JPanel Panel;
    private javax.swing.JScrollPane  ScrollPane;
    private javax.swing.JTable ReadLabels;
    private TableMModel Mmodel ;
    private JButton bClose,bSelectAll,bDeselectAll,bWriteToBaseSelectedRows;
    private Connection conn;
       private Object[][] data= {
            
            
            {new  Boolean(false),
                                            "1",
                                            "dfghyu",
                                            null,
                                            "bluzka",
                                            "XXL",
                                            "czarny",
                                            new  Boolean(false)
            },
            
        { new Boolean(false),
                                        "2",
                                        "aaaaa",
                                        null,
                                        "spodnie",
                                        "M",
                                        "granatowy",
                                        new  Boolean(false)
        }
        };
        final  static  String[] columnNames = {"Wybierz",
                                                "Lp",
                                                "Epc",
                                                "Data",
                                                "Nazwa",
                                                "Rozmiar",
                                                "Kolor",
                                                "Dodano"
        };


  
    public ReadLabelsList() {
         initComponents();
         try {
             conn=StaticConnectToDatabase.Connect();
         }
         catch (StaticConnectToDatabaseException e) {
           int exit_code=e.GetExitCode();
           String mess=e.GetInterMessage();
           String message_full=mess+"\n\r"+"Błąd połączenie z bazą danych w konstruktorze klasy ReadLabelsList";
           ExceptionMessage.ShowExceptionMessageAndExit1(message_full,exit_code);            
         }
    };
    
    
    
    
    private void initComponents() {
        try {
        Panel= new javax.swing.JPanel();
//        Panel=null;
        assert Panel!=null:"Panel w InitComponent jest null";
        ScrollPane = new javax.swing.JScrollPane();  
        assert ScrollPane!=null:"ScrollPanel w InitComponent jest null";
        Mmodel=new TableMModel(data,columnNames);
        assert Mmodel!=null:"Mmodel w InitComponent jest null";
       this.add(Panel);
       Panel.add(ScrollPane, java.awt.BorderLayout.CENTER);
       ReadLabels=new javax.swing.JTable();
       
       ReadLabels.setModel(Mmodel);
 //      ReadLabels.setDefaultRenderer(Boolean.class, new  BooleanRenderer());
       ReadLabels.addMouseListener(this);
      ScrollPane.setViewportView(ReadLabels);
       Panel.add(ScrollPane);
       bClose=new JButton("Zamknij");
       bClose.setBounds(20, 20, 200, 50);
       bClose.addActionListener(this);
       Panel.add(bClose);
//
        bSelectAll=new JButton("Wybierz wszystko");
        bSelectAll.setBounds(20, 20, 200, 50);
        bSelectAll.addActionListener(this);
        Panel.add(bSelectAll);
//
        bDeselectAll=new JButton("Skauj wybór");
        bDeselectAll.setBounds(20, 20, 200, 50);
       bDeselectAll.addActionListener(this);
        Panel.add(bDeselectAll);
        
        bWriteToBaseSelectedRows=new JButton("Zapisz do bazy wybrane");
        bWriteToBaseSelectedRows.setBounds(20, 20, 200, 50);
        bWriteToBaseSelectedRows.addActionListener(this);
        Panel.add(bWriteToBaseSelectedRows); 

       Dimension dim=new Dimension(700,500);
       this.setPreferredSize(dim);
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     
       pack();
        }
            catch (java.lang.AssertionError r) {
            ExceptionMessage.ShowExceptionMessage("Błąd assercji w klasie ReadLabelsLis: " + r.getMessage());
        }

    };
    
public void CloseConnection() {
   try {
   if (conn!=null) conn.close();
   }
//          catch (StaticConnectToDatabaseException e) {
         catch (SQLException e) {
              String message="Błąd funkcji klasy ReadLabelsList -  zmknięcie Connection";
 //           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji Createtable - zmknięcie Statement");
              int exit_code=ErrorCodes.ReadLabelsListConnectionClosingError.ordinal();
              ExceptionMessage.ShowExceptionMessageAndExit1(message,exit_code);            
//            DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
 //          throw dae;
         }
}
    

    @Override
    public void actionPerformed(ActionEvent e)  {
        Object source=e.getSource();
        assert source!=null:"Object w actionPerformed(ActionEvent e) jest null; ";
      if (source==bClose){
          CloseConnection();
          dispose();
      }
      else if (source==bSelectAll) SetRowSelection(true);
      else if (source==bDeselectAll) SetRowSelection(false);
      else /* (source=bWriteToBaseSelectedRows)*/{
        try {
          WriteToDatabaseCheckedRows();
      }
       catch ( DAOfunctionException ed) {
           String message="Błąd przy zapisie do bazy wybranych rzędów\n\r"+ed.getMessage();
           int exit_code=ErrorCodes.ReadLabelsListWriteToBaseSelectedRowsError.ordinal();
           ExceptionMessage.ShowExceptionMessageAndExit1(message,exit_code);
           System.exit(exit_code);
        }
    }
    }
    
 private void SetRowDisabled(int row) {
     
       for (int n=1;n<Mmodel.getColumnCount();n++) {
         TableCellRenderer renderer=ReadLabels.getCellRenderer(0, n);
         Component    comp = ReadLabels.prepareRenderer(renderer, row, n);
          if (!ReadLabels.isRowSelected(row))  /*comp.setBackground(cl);*/
                 comp.setEnabled(false);
       }
 }
/*    
 public  class BooleanRenderer extends JCheckBox implements TableCellRenderer
     {
        private  final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        public BooleanRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
            setBorderPainted(true);
            setOpaque(true);
 //           setText("Hello");
        }
 */
/* 
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column)
        {
    
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
 //               setBackground(Color.WHITE);
                setForeground(new Color(255, 0, 255));
            }
            setSelected((value != null && ((Boolean) value).booleanValue()));

            if (hasFocus) {
                setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            } else {
                setBorder(noFocusBorder);
            }
 //           setHorizontalAlignment(JLabel.RIGHT);
            return this;
        }
    
 }
 */       
/* 
public TableCellEditor getCellEditor(int row, int col){
             if (Mmodel.getValueAt(row, col) instanceof Boolean) {
                 JCheckBox checkBox=new JCheckBox();
                  return new DefaultCellEditor(checkBox);
            }
            else 
            if  (Mmodel.getValueAt(row, col) instanceof String) {
                 JTextField tField=new  JTextField();
                  return new DefaultCellEditor( tField);
            } 
             return getCellEditor(row, col);
//      }// class TableMModel extends AbstractTableModel {
      } 

 */
public  void AddRow(int lp,String EPC) 
{
    String str_date=GetNow();
//    Mmodel.addRow(new Object[]{false,lp,EPC,str_date,"","",""});
    Vector vct=new Vector();
    vct.addElement(false);
    vct.addElement(lp);
    vct.addElement(EPC);
    vct.addElement(str_date);
    vct.addElement("");
    vct.addElement("");
    vct.addElement("");
    vct.addElement(false);

    Mmodel.addRow(vct);
    Mmodel.fireTableDataChanged();

    
}
 
 public  String GetNow() {
  DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
  Date today = Calendar.getInstance().getTime();
  String DNow=df.format(today);
  return DNow;
} 

 
private boolean  IsTableEmpty () {
       return Mmodel.getRowCount()>0;
}
    
private ArrayList<String> GetOneRowData(final int RowNumber) {
    ArrayList<String> RowData=new  ArrayList<>();
    String val="";
    int n=2;
    Integer na;
    Object bb;
    int ClCn=Mmodel.getColumnCount()-2;
    while(n<=ClCn)
       {
           bb=Mmodel.getValueAt(RowNumber, n);
           switch(n) {
               case 2:
               {
                  val=bb.toString();
                  RowData.add(val);
                  RowData.add(globals.NOT_SOLD);
                  n++;
                  break;
               }
               case 3:
               {
//                   if (bb==null) val=null;else val=bb.toString();
                   RowData.add(GetNow());
                   n++;
                   break;
               }
               
                default: {
                            val=bb.toString();
                            RowData.add(val);
                            n++;
                          }

                      }//switch(n)
           
        };//while(n<=ClCn)
//        RowData.add(val);
    return RowData;
}

private void WriteToDatabaseCheckedRows() throws  DAOfunctionException {
   for (int n=0;n<Mmodel.getRowCount();n++) {
       String ax=Mmodel.getValueAt(n,0).toString();
        if ( Boolean.valueOf(ax)) {
             WriteRowToDatabase(n);
              Mmodel.setValueAt(false, n, 0);
            Mmodel.setValueAt(true, n, 7);
       }
    }    
}

private void SetRowSelection(final boolean val) {
   for (int n=0;n<ReadLabels.getRowCount();n++) {
      Mmodel.setValueAt(val, n, 0);
      Mmodel.fireTableCellUpdated(n,0);
   }
}

private void WriteRowToDatabase(final int RowNumber) throws DAOfunctionException
{ 
     ArrayList<String> rrw= GetOneRowData(RowNumber);
//
         DAOfunctions daof=new DAOfunctions(conn);
         try {
//                   if (!daof.InsertEPC(globals.TableName, rrw.get(0), globals.NOT_SOLD))
                     if (!daof.InsertEPC(globals.TableName, rrw))
                    {
                       ShowSimplestMessage. ShowSimplestMessage(this,"Etykieta : "+rrw.get(0)+"jest już w bazie");   
                   }
             }
         catch(DAOfunctionException df)
         {
             throw df;
       // ShowSimplestMessage. ShowSimplestMessage(this,"Etykieta : "+rrw.get(0)+"jest już w bazie");     
         }
 }

private void WriteAllToDatabase() throws DAOfunctionException {
     for (int n=0;n<ReadLabels.getRowCount();n++) {
         try {
                 WriteRowToDatabase(n);
         }
         catch (DAOfunctionException df) {
             throw df;
         }
     }
 }



             @Override
             public void mouseClicked(MouseEvent e) { 
                int row = ReadLabels.rowAtPoint(e.getPoint());
                int col = ReadLabels.columnAtPoint(e.getPoint());
                 if (!Mmodel.isCellEditable(row, col))  return;
 //                TableCellEditor tce=getCellEditor(row,col);
 //               ReadLabels.setCellEditor(tce);
                ReadLabels.editCellAt(row, col);
                ReadLabels.requestFocus();
                Mmodel.fireTableCellUpdated(row,col);
             };
 
            @Override
             public void mouseExited(MouseEvent e) { }
 
            @Override
            public void mouseEntered(MouseEvent e)  {}

 
            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

//            @Override
//            public void mouseMoved(MouseEvent e) {};
 
//            @Override
//            public void mouseDragged(MouseEvent e) {};
            
////////////////////////////////////////////////////////////////////////////////
class TableMModel extends   DefaultTableModel
{   
    

    public TableMModel(Object [][]dataP,Object [] columnsNames ) {
            super(dataP,columnsNames);
    }
    
 @Override
    public boolean isCellEditable(int row, int column) {
      String  ax=getValueAt(row,7).toString();     
//      Boolean ax=(Boolean)data[row][(int)7];
      if (Boolean.valueOf(ax)) return false;
      else
      if (( (column==0 ) || (column>3)) && (column<7)) return true;else return false;
 //   }
}


 @Override       
  public Class<?> getColumnClass(int columnIndex) {      
      Class clazz = String.class;
      switch (columnIndex) {
        case 0:
          clazz = Boolean.class;
          break;
        case 1:
            clazz=String.class;
            break;
        case 2:
          clazz = String.class;
          break;
        case 3:
            clazz=Integer.class;
           break;
        case 4:
          clazz = String.class;
          break;
        case 5:
          clazz = String.class;
          break;
        case 6:
          clazz = String.class;
          break;
        case 7:
            clazz=Boolean.class;
            break;
      }
      return clazz;
    }   
}

           
}//public class ReadLabelsList1 extends JFrame  implements MouseInputListener {


 
