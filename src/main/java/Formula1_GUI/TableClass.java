package Formula1_GUI;

import Formula1_Manager.Formula1Driver;
import Formula1_Manager.Formula1Runner;
import Formula1_Manager.GetDateAndStatistics;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.*;

public class TableClass extends JTable {
    private static final Formula1Driver fd = new Formula1Driver();
    private static final Formula1Runner fr = new Formula1Runner();
    protected TableClass(){
        fr.loadTotalScoresArray();
        fd.loadingDriverDetails();
        setBackground(new Color(254, 254, 254));
        setForeground(Color.black);
        setSelectionBackground(new Color(0, 150, 199));
        setGridColor(new Color(0, 0, 0));
        setSelectionForeground(Color.white);
        setFont(new Font("Arial", Font.PLAIN, 16));
        JTableHeader tableHeader = this.getTableHeader();
        Font headerFont = new Font("Arial", Font.PLAIN, 16);
        tableHeader.setFont(headerFont); // https://www.tutorialspoint.com/how-to-change-jtable-s-header-font-in-java
        setRowHeight(30);
        setAutoCreateRowSorter(true);
    }
    public TableModel sevenColumnModel(Object[] columns, Object[][] row){
        //http://www.java2s.com/Tutorial/Java/0240__Swing/TableRowSorterwithcolumnclass.htm
        return new DefaultTableModel(row, columns) {
            public Class<?> getColumnClass(int column) {
                if (column >= 0 && column <= getColumnCount()) {
                    return getValueAt(0, column).getClass();
                } else
                    return Object.class;
            }
        };
    }
    public TableModel updateModel(JTable table, Object[] columns, Object[][] row){
        //http://www.java2s.com/Tutorial/Java/0240__Swing/TableRowSorterwithcolumnclass.htm
        return new DefaultTableModel(row, columns) {
            public Class<?> getColumnClass(int column) {
                if (column >= 0 && column <= getColumnCount()&& row.length>0) {
                    //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
                    DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
                    leftAlign.setHorizontalAlignment(JLabel.LEFT);
                    table.getColumnModel().getColumn(3).setCellRenderer(leftAlign);
                    table.getColumnModel().getColumn(4).setCellRenderer(leftAlign);
                    return getValueAt(0, column).getClass();
                } else
                    return Object.class;
            }
        };
    }
    public void race_2(Object[][] row){
        fd.loadingDriverDetails();
        int count = 0;
        for (Map.Entry<String, Formula1Driver> x : fd.getDriverDetailsLinkedHashMap().entrySet()) {
            String tName = x.getKey();
            String dName = x.getValue().getDriverName();
            String location = x.getValue().getLocation();
            int points = x.getValue().getPoints();
            int position1 = Formula1Runner.getTotalPositionLinkedHashMap().get(tName).get(0);
            int position2 = Formula1Runner.getTotalPositionLinkedHashMap().get(tName).get(1);
            int position3 = Formula1Runner.getTotalPositionLinkedHashMap().get(tName).get(3);
            row[count][0] = tName;
            row[count][1] = dName;
            row[count][2] = location;
            row[count][3] = points;
            row[count][4] = position1;
            row[count][5] = position2;
            row[count][6] = position3;
            count++;
        }
    }
    public void UpdateRowData(Object[][] row){
        int count = 0;
        for (Map.Entry<String, Formula1Driver> x : Formula1Runner.getRaceEndPositions().entrySet()) {
            String tName = x.getKey();
            String dName = x.getValue().getDriverName();
            String location = x.getValue().getLocation();
            int position = x.getValue().getPoints();
            int points;
            if (position-1<new Formula1Runner().getPOINTS().length){
                points = new Formula1Runner().getPOINTS()[position-1];}
            else {
                points = 0;
            }
            row[count][0] = tName;
            row[count][1] = dName;
            row[count][2] = location;
            row[count][4] = points;
            row[count][3] = position;
            count++;
        }
    }
    public void showData(Object[][] row){
        fd.loadingDriverDetails();
        List<GetDateAndStatistics> d = new TableClass().dateComparison(Formula1Runner.getDateList());
        int index = 0;
        for (int x = d.size()-1; x >= 0; x--) {
            String tName = d.get(x).getManufacture();
            String dName = fd.getDriverDetailsLinkedHashMap().get(tName).getDriverName();
            String location = fd.getDriverDetailsLinkedHashMap().get(tName).getLocation();
            LocalDate date = d.get(x).getDate();
            int points = d.get(x).getPoint();
            row[index][0] = tName;
            row[index][1] = dName;
            row[index][2] = location;
            row[index][3] = date;
            row[index][4] = points;
            index++;
        }
    }
    public List<GetDateAndStatistics> dateComparison(List<GetDateAndStatistics> x ){
        x.sort((o1, o2) -> {
            if (o1.getDate().isAfter(o2.getDate())){
                return 1;
            }
            if (o1.getDate().isEqual(o2.getDate())){
                if (o1.getPoint()<o2.getPoint()){
                    return 1;
                }
                else if (o1.getPoint()>o2.getPoint()){
                    return -1;
                }
                return 0;
            }
            return -1;
        });
        return x;
    }
    public void algorithmRowData(Object[][] row){
        int count = 0;
        for (int x = 0; x<Formula1Runner.getRaceEndPosition().size(); x++){
            String tName = Formula1Runner.getRaceStartPosition().get(x);
            String dName = fd.getDriverDetailsLinkedHashMap().get(tName).getDriverName();
            String location = fd.getDriverDetailsLinkedHashMap().get(tName).getLocation();
            int points;
            int endPosition = Formula1Runner.getRaceEndPosition().indexOf(tName)+1;
            if (endPosition-1<new Formula1Runner().getPOINTS().length){
                points = new Formula1Runner().getPOINTS()[endPosition-1];}
            else {
                points = 0;
            }
            int startPosition = Formula1Runner.getRaceStartPosition().indexOf(tName)+1;
            row[count][0] = tName;
            row[count][1] = dName;
            row[count][2] = location;
            row[count][5] = points;
            row[count][4] = endPosition;
            row[count][3] = startPosition;
            count++;
        }
    }
    /* (Rose India Technologies Pvt. Ltd., 2018)
     * Rose India Technologies Pvt. Ltd. (2018). Removing a Column from a JTable.
     * Available from https://www.roseindia.net/java/example/java/swing/RemoveColumn.shtml
     * [Accessed 27 November 2021].
     */
    public void RemoveColumn(JTable table, int col_index){
        if (table.getColumnCount()>6){
            TableColumn tableColumn = table.getColumnModel().getColumn(col_index);
            table.removeColumn(tableColumn);
            TableColumn tableColumn2 = table.getColumnModel().getColumn(table.getColumnCount()-1);
            table.removeColumn(tableColumn2);
        }
        if (table.getColumnCount()>5){
            TableColumn tableColumn = table.getColumnModel().getColumn(col_index);
            table.removeColumn(tableColumn);
        }
    }
    public void RightJustify(){
        //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        getColumnModel().getColumn(3).setCellRenderer(leftAlign);
        getColumnModel().getColumn(4).setCellRenderer(leftAlign);
        getColumnModel().getColumn(5).setCellRenderer(leftAlign);
        getColumnModel().getColumn(6).setCellRenderer(leftAlign);
    }
    public void RightJustifyRace2(){
        //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);

    }
}
