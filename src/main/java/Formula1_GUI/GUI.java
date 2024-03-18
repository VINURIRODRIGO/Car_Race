package Formula1_GUI;

import Formula1_Manager.Formula1Driver;
import Formula1_Manager.Formula1Runner;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.regex.PatternSyntaxException;
import static javax.swing.SortOrder.ASCENDING;
import static javax.swing.SortOrder.DESCENDING;


public class GUI {
    static Object[][] row;
    static  Formula1Driver fd = new Formula1Driver();
    static  GUI gui = new GUI();
    private static TableClass table;
    private static Object[] columns;
    private static TableModel tm;
    private static TextField searchbar;
    static ScrollPaneClass pane;
    private static FrameClass frame;

    public static void main(String[] args) {
        //loading the files
        fd.loadingDriverDetails();
        new Formula1Runner().loadRaceDate();


        /* Borkar, R. (2012). java - Disable user edit in JTable. Stack Overflow.
         * Available from https://stackoverflow.com/questions/9919230/disable-user-edit-in-jtable
         * [Accessed 29 November 2021].
         */
        //Disable user JTable editing
        table = new TableClass() {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }};//(Borkar, 2012)

        columns = new Object[]{"Team Name", "Driver", "Location", "Points", "Winner", "1st-Runner-up", "2nd-Runner-up"};
        row = new Object[fd.getDriverDetailsLinkedHashMap().size()][columns.length];
        table.race_2(row);

        //http://www.java2s.com/Tutorial/Java/0240__Swing/TableRowSorterwithcolumnclass.htm
        tm = table.sevenColumnModel(columns, row);
        frame = new FrameClass();
        table.setModel(tm);

        searchbar = new TextField("");
        searchbar.setForeground(Color.black);
        searchbar.setBounds(540, 80, 200, 30);
        searchbar.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(searchbar);
        table.RightJustify();
        pane = new ScrollPaneClass(table);
        frame.getContentPane().add(pane, BorderLayout.CENTER);
        //frame.trophiesImage();

// Adding addActionListener for the Buttons
        FrameClass.descending.addActionListener(e -> {
            new GUI().mainScoreBoard(frame);
            new GUI().SortOrderName_and_Column(table, 3, DESCENDING);
        });
        FrameClass.ascending.addActionListener(e -> {
            new GUI().mainScoreBoard(frame);
            new GUI().SortOrderName_and_Column(table, 3, ASCENDING);
        });

        FrameClass.winnerOrder.addActionListener(e -> {
            new GUI().mainScoreBoard(frame);
            new GUI().SortOrderName_and_Column(table, 4, DESCENDING);
        });
        FrameClass.startGame.addActionListener(e -> {
            columns = new Object[]{"Team", "Driver", "Country", "Ending Position", "Points"};
            row = new Object[fd.getDriverDetailsLinkedHashMap().size()][columns.length];
            frame.raceImage();
            new Formula1Runner().guiFirstRace();
            gui.race1ScoreBoard();
        });
        FrameClass.startGame2.addActionListener(e -> {
            columns = new Object[]{"Team", "Driver", "Country", "Starting Position", "Ending Position", "Points"};
            row = new Object[fd.getDriverDetailsLinkedHashMap().size()][columns.length];
            frame.raceImage();
            new Formula1Runner().guiSecondRace();
            gui.winningPositionComparator();
        });
        //https://www.tutorialspoint.com/how-can-we-filter-a-jtable-in-java
        FrameClass.searchButton.addActionListener(e -> {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(new TableClass().sevenColumnModel(columns, row));
            table.setRowSorter(sorter);
            String text = searchbar.getText();
            //
            if (text.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                try {
                    text = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    System.out.println("Bad regex pattern");
                }
            }
        });
        FrameClass.raceDates.addActionListener(e -> {

            columns = new Object[]{"Team", "Driver", "Country", "Date", "Position"};
            row = new Object[Formula1Runner.getDateList().size()][columns.length];
            frame.trophiesImage();
            gui.DisplayingDate();
        });
        frame.setVisible(true);
    }

    /**
     *
     * @param frame JFrame
     */
    public void mainScoreBoard(FrameClass frame) {
        columns = new Object[]{"Team Name", "Driver", "Country", "Points", "Winner", "1st-Runner-up", "2nd-Runner-up"};
        row = new Object[fd.getDriverDetailsLinkedHashMap().size()][columns.length];
        frame.trophiesImage();
        gui.sevenColumnTableCreator();
        table.RightJustify();
    }

    public void race1ScoreBoard() {
        if (table.getColumnModel().getTotalColumnWidth() > 4) {
            table.RemoveColumn(table, 5);
        }
        table.UpdateRowData(row);
        tm = table.updateModel(table, columns, row);
        table.setModel(tm);
    }

    public void DisplayingDate() {
        if (table.getColumnModel().getTotalColumnWidth() > 4) {
            table.RemoveColumn(table, 5);
        }
        table.showData(row);
        tm = table.updateModel(table, columns, row);
        table.setModel(tm);
    }

    public void winningPositionComparator() {
        if (table.getColumnModel().getTotalColumnWidth() > 4) {
            table.RemoveColumn(table, 5);
        }
        table.RightJustifyRace2();
        table.algorithmRowData(row);
        tm = table.updateModel(table, columns, row);
        table.setModel(tm);
    }

    /**
     * Creating the table to display all the drivers and their statistics in descending order of
     * points, ascending order of points won by drivers and the largest number of first position won in races
     */
    public void sevenColumnTableCreator() {
        table.race_2(row);
        tm = table.sevenColumnModel(columns, row);
        table.setModel(tm);
    }

    /* (java - How to sort JTable in shortest way?, 2015)
     * java - How to sort JTable in shortest way? (2015). Stack Overflow.
     * Available from https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way
     * [Accessed 27 November 2021].
     */

    /**
     * Generating the descending order of points, ascending order of points won by drivers and the largest number of first position won in races.
     * @param table JTable
     * @param col_index column that need to sort
     * @param orderType Providing the computation type of sorting order.
     */
    public void SortOrderName_and_Column(JTable table, int col_index, SortOrder orderType) {

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(col_index, orderType));
        sorter.setSortKeys(sortKeys);
    }
}

