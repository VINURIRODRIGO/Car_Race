package Formula1_Manager;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Managing the Formula 1 Championship
 */
public class Formula1ChampionshipManager implements ChampionshipManager {
    private static final Formula1Driver fd = new Formula1Driver();
    private static final Formula1ChampionshipManager fdm = new Formula1ChampionshipManager();
    private static final Formula1Runner raceRunner = new Formula1Runner();

    private static final Scanner STRING_INPUT = new Scanner(System.in);
    private static final Scanner INPUT = new Scanner(System.in);
    public static void main(String[] args) {
        String choice;
        do {
            fd.loadingDriverDetails();
            raceRunner.loadTotalScoresArray();
            raceRunner.loadRaceDate();
            System.out.println("----------Menu---------");
            System.out.println("Enter A: Add a new Driver");
            System.out.println("Enter B: Delete a Driver and Team");
            System.out.println("Enter C: Change a driver");
            System.out.println("Enter D: Find a driver's score");
            System.out.println("Enter E: Start a race");
            System.out.println("Enter F: Display the score board");
            System.out.println("Enter Q: Quit");
            System.out.print("Enter your choice: ");
            choice = STRING_INPUT.nextLine().toUpperCase();
            switch (choice){
                case "A" -> fdm.addDriver();
                case "B" -> fdm.deleteDriver();
                case "C" -> fd.changeDriver();
                case "D" -> fdm.driverStatistics();
                case "E" -> raceRunner.manualRace(fd.getDriverDetailsLinkedHashMap());
                case "F" -> fdm.comparison();
                case "Q" -> System.out.println("Thank you\n");
                default -> System.out.println("Invalid choice");
            }
        }while (!choice.equals("Q"));
    }

    /* Java Program to capitalise each word in String - javatpoint. (no date). www.javatpoint.com.
     * Available from https://www.javatpoint.com/java-program-to-capitalize-each-word-in-string
     * [Accessed 9 December 2021].
     */
    /**
     * Return capitalize each word of a given string.
     * @param str  get the String value
     * @return Capitalize the String
     */
    public static String wordCapitalize(String str){
            String[] wordsArray =str.split("\\s");
            StringBuilder capitalizeWord= new StringBuilder();
            for(String w:wordsArray){
                String first=w.substring(0,1);
                String afterFirst=w.substring(1);
                capitalizeWord.append(first.toUpperCase()).append(afterFirst).append(" ");
            }
            return capitalizeWord.toString().trim(); // (Java Program to capitalise each word in String - javatpoint, no date)
    }

    /*
     * YD. (2017). Validating A Name in Java. Object Oriented programming.
     * Available from https://www.youtube.com/watch?v=oMvbPZHAeks
     * [Accessed 9 December 2021].
     *
     * Max O’Didily. (2017). Java Validation: Validating a Name.
     * Available from https://www.youtube.com/watch?v=DrwLIRjHXRw
     * [Accessed 9 December 2021].
     */

    /**
     * Return true if the string only contain letters and spaces.
     * <p>
     * It will return true when the input contains only contain letters.
     * If the first index has a space will return false.
     * Also, Return false when the inserted data is null or the length is less than two.
     * </p>
     * @param str Getting the String values
     * @return Whether the name is a valid input or not
     */
    public  boolean isValidName(String str){
        if (str == null || str.startsWith(" ")||str.equals("")){ //(YD, 2017)
            System.out.println(("Error, input cannot be blank. Try again\n"));
            return false;
        }
        else if(str.length()<=1){
            System.out.println(("Add more than one character. Try again\n"));
            return false;
        }
        return Pattern.matches("[a-zA-Z\\s]+", str); //(Max O’Didily, 2017)
    }

    /**
     * Return false if the string is not an invalid team.
     * <p>
     * It will check Whether the input is null, or length is at least two.
     * It will also check if any spaces are contained or not in the team name.
     *</p>
     * @param str Getting the String values
     * @return Whether the team name is an invalid input or not
     */
    public  boolean isInvalidTeam(String str){
        if (str == null||str.equals("")){
            System.out.println(("Error, input cannot be blank. Try again\n"));
            return true;
        }
        else if (str.contains(" ")){
            System.out.println(("Error, input cannot contain any spaces. Try again\n"));
            return true;
        }
        else if(str.length()<=1){
            System.out.println(("Add more than one character. Try again\n"));
            return true;
        }
        return false;
    }

    /**
     * A new team will add to the competition.
     * <p>
     *     Getting the new car manufacturer's and driver's name, and the country.
     *     Cannot contain duplicate teams. Each teams can only have a single driver.
     *     No spaces can be entered between the team name and will be checked using {@link #isInvalidTeam(String)}.
     *     Each word of the input data will convert to capitalize and will check by using {@link #wordCapitalize(String)}.
     *     All input data lengths should be at least two.
     *     All input data cannot include spaces at the beginning.
     *     Driver name and the location cannot include any numbers or symbols and will checked using {@link #isValidName(String)}.
     * </p>
     */
    @Override
    public void addDriver(){
        // getting user inputs and make them capitalize
        String manufacturerName;
        do{
        System.out.print("Enter the name of the car manufacturer: ");
        manufacturerName = INPUT.nextLine();}while (isInvalidTeam(manufacturerName));
        manufacturerName= wordCapitalize(manufacturerName);
        String driverName;
        do{
        System.out.print("Enter the name of the driver: ");
        driverName = INPUT.nextLine();}while (!isValidName(driverName));
        System.out.println(driverName);
        driverName= wordCapitalize(driverName);
        String location;
        do{
        System.out.print("Enter the location: ");
        location = INPUT.nextLine();}while (!isValidName(location));
        location= wordCapitalize(location);

        fd.setCarManufacturer(manufacturerName);
        Formula1Driver newDriver = new Formula1Driver(driverName, location, 0);
        if(!fd.getDriverDetailsLinkedHashMap().containsKey(manufacturerName)){
            fd.getDriverDetailsLinkedHashMap().put(fd.getCarManufacturer(), newDriver);
            fd.storeDriverDetails(fd.getDriverDetailsLinkedHashMap());
            ArrayList<Integer> totalPosition = new ArrayList<>();
            for (int u=0; u< LastEndPointReceiver; u++){
                totalPosition.add(0);
            }
            Formula1Runner.getTotalPositionLinkedHashMap().put(manufacturerName,totalPosition);
            System.out.println("Welcome to Formula 1 Championship\n");
            raceRunner.storeTotalScoresArray();
        }
        else{
            System.out.printf("%s team is already registered for the competition.", manufacturerName).println("\n");
        }
    }

    /**
     * Deleting the existing team from the race.
     * <p>
     * Each word of the input data will convert to capitalize and by using {@link #wordCapitalize(String)}.
     * </p>
     *  The whole team will delete from the race.
     */
    @Override
    public void deleteDriver() {
        System.out.print("Enter the name of the car manufacturer: ");
        String manufacturerName = INPUT.next();
        manufacturerName= wordCapitalize(manufacturerName);

        if(fd.getDriverDetailsLinkedHashMap().containsKey(manufacturerName)) {
            fd.getDriverDetailsLinkedHashMap().remove(manufacturerName);
            Formula1Runner.getTotalPositionLinkedHashMap().remove(manufacturerName);//removing the team
            raceRunner.storeTotalScoresArray();
            System.out.println("Team successfully deleted\n");
            fd.storeDriverDetails(fd.getDriverDetailsLinkedHashMap());
        }
        else{
            System.out.println("car manufacturer cannot found\n");
        }
    }

    /* Erickson. (2011). Adding whitespace in Java.
     * Stack Overflow. Available from https://stackoverflow.com/questions/5249566/adding-whitespace-in-java
     * [Accessed 18 November 2021].
     */
    /**
     * Display driver statistics including name, team, total score, number of wins, 1st runners-up and 2nd runners-up
     * <p>If two drivers have the same name, both will be displayed.</p>
     */
    @Override
    public void driverStatistics() {
        int count = 0;
        int found = 0;
        System.out.print("Enter the name of the driver: ");
        String driverName = INPUT.nextLine();
        driverName= wordCapitalize(driverName);
        raceRunner.loadTotalScoresArray();
        for (Formula1Driver x : fd.getDriverDetailsLinkedHashMap().values()) {
            if (Objects.equals(x.getDriverName(), driverName)) {
                String manufacturer = String.valueOf(fd.getDriverDetailsLinkedHashMap().keySet().toArray()[count]);

                String leftAlignFormat = "| %-20s | %-20s | %-20s | %-12d | %-20s |%n";
                System.out.format("+----------------------+----------------------+----------------------+--------------+-----------------------------------------------+%n");
                System.out.format("| Car manufacturer     |         Driver       | Location             | Total Points |_______________________________________________|%n");
                System.out.format("|                      |                      |                      |              |      Win      | 1st-Runner-up | 2nd-Runner-up |%n");
                System.out.format("+----------------------+----------------------+----------------------+--------------+---------------+---------------+---------------+%n");
                ArrayList<Integer> points = Formula1Runner.getTotalPositionLinkedHashMap().get(manufacturer);
                StringBuilder str = new StringBuilder();

                for (int i = 0; i < 3; i++)
                {   int myNumbersInt = points.get(i);
                    if (i==1){
                        str.append( String.format(" %s %-11s |",myNumbersInt," "));
                    }
                    else if (i!=2){
                        str.append( String.format(" %s %-10s |",myNumbersInt," ")); // (Erickson, 2011)
                    }else{
                        str.append( String.format(" %s %-11s",myNumbersInt," "));
                    }
                }System.out.format(leftAlignFormat, manufacturer, x.getDriverName(),  x.getLocation(), x.getPoints(),str);
                 System.out.format("+----------------------+----------------------+----------------------+--------------+---------------+---------------+---------------+%n");
                 System.out.println();
                found++;
            }
            else if (count == fd.getDriverDetailsLinkedHashMap().size()-1 && found ==0){
                System.out.printf("%s is not an existing driver",driverName).println();
            }
            count++;
        }
        System.out.println("\n");
    }

    /*
     * (Java Made Easy, 2017)
     * Java Made Easy. (2017). How To Sort Objects On Multiple Fields Using Comparator Interface.
     * Available from https://www.youtube.com/watch?v=buD-_4-PXWw
     * [Accessed 18 November 2021].
     */
    /**
     * Sorts the drivers using their total scores in descending order.
     * <p>
     * If two drivers have the same score, the driver who wins maximum first place appears first.
     * </p>
     */
    public void comparison() {
        List<ArrayList<Integer>> list2 = new ArrayList<>(Formula1Runner.getTotalPositionLinkedHashMap().values());
        Set<Map.Entry<String, Formula1Driver>> entries = fd.getDriverDetailsLinkedHashMap().entrySet();
        List<Map.Entry<String, Formula1Driver>> listData = new ArrayList<>(entries);
        listData.sort((o1, o2) -> {
            if (o1.getValue().getPoints() > o2.getValue().getPoints()) {
                return 1;
            } else if (o1.getValue().getPoints() == o2.getValue().getPoints()) {
                ArrayList<Integer> o1Array = list2.get(listData.indexOf(o1));
                ArrayList<Integer> o2Array = list2.get(listData.indexOf(o2));
                for (int i = 0; i < LastEndPointReceiver; i++) {
                    //getting the driver who wins maximum first place.
                    if (!o1Array.get(i).equals(o2Array.get(i))) {
                        if (o1Array.get(i) > (o2Array.get(i))) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                return 0;
            }
            return -1;
        });descendingTable(listData);
    }

    /*
     * Erickson. (2011). Adding whitespace in Java.
     * Stack Overflow. Available from https://stackoverflow.com/questions/5249566/adding-whitespace-in-java
     * [Accessed 18 November 2021].
     *
     * Pshemo. (2013). java - How can I create table using ASCII in a console? Stack Overflow.
     * Available from https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
     * [Accessed 18 November 2021].
     *
     * Kartik. (2014). java - Conversion of Integer Arraylist to string.
     * Stack Overflow.
     * Available from https://stackoverflow.com/questions/21412933/conversion-of-integer-arraylist-to-string
     * [Accessed 18 November 2021].
     */
    /**
     * Display all the drivers competing with their statistics in descending order by using {@link #comparison()}.
     * @param list list which sorts the drivers using their total scores in descending order.
     */
    public void descendingTable(List<Map.Entry<String, Formula1Driver>> list){
        //(Pshemo, 2013)
        String leftAlignFormat = "| %-20s | %-20s | %-20s | %-12d | %-20s |%n";
        System.out.format("+----------------------+----------------------+----------------------+--------------+----------------------------------------------------------------------+%n");
        System.out.format("| Car manufacturer     |         Driver       | Location             | Total Points |_______________________Total Positions _______________________________|%n");
        System.out.format("|                      |                      |                      |              | 1st   | 2nd  | 3rd  | 4th  | 5th  | 6th  | 7th  | 8th  | 9th  | 10th |%n");
        System.out.format("+----------------------+----------------------+----------------------+--------------+-------+------+------+------+------+------+------+------+------+------+%n");
        for (int r = list.size()-1; r>=0; r--) {
            Map.Entry<String, Formula1Driver> listDatum = list.get(r);


            StringBuilder str = new StringBuilder();// (Kartik, 2014)
            ArrayList<Integer> x = Formula1Runner.getTotalPositionLinkedHashMap().get(listDatum.getKey());
            for (int i = 0; i < x.size(); i++)
            {   int myNumbersInt = x.get(i);
                if (i!=x.size()-1){
                    str.append( String.format(" %s %-2s |",myNumbersInt," "));//(Erickson, 2011)
                }else{
                    str.append( String.format(" %s %-2s",myNumbersInt," "));
                }
            }
            System.out.format(leftAlignFormat, listDatum.getKey(), listDatum.getValue().getDriverName(), listDatum.getValue().getLocation(), listDatum.getValue().getPoints(), str);
        }System.out.format("+----------------------+----------------------+----------------------+--------------+-------+------+------+------+------+------+------+------+------+------+%n");
        System.out.println("\n");
    }
}
