package Formula1_Manager;

import java.io.*;
import java.util.*;

public class Formula1Driver extends Driver {
    private static final Scanner INPUT = new Scanner(System.in);
    private final LinkedHashMap <String, Formula1Driver> driverDetailsLinkedHashMap = new LinkedHashMap<>();
    private int points;

    /**
     * Invokes the no-argument constructor
     */
    public Formula1Driver(){}

    /**
     * <p>
     * Formula1Driver class contains a parameterized constructor that includes Driver  Name, Location
     * and Points.
     * </p>
     * @param driverName Getting Driver  name
     * @param location Location
     * @param points Points (All new drivers will get 0 points at the beginning)
     */
    public Formula1Driver(String driverName, String location, int points){
        setDriverName(driverName);
        setLocation(location);
        this.points = points;
    }

    /**
     * Return {@link #driverDetailsLinkedHashMap} LinkedHashMap data includes Team names,Driver Names, Location and total points.
     * @return The {@link #driverDetailsLinkedHashMap} LinkedHashMap
     */
    public LinkedHashMap<String, Formula1Driver> getDriverDetailsLinkedHashMap() {
        return driverDetailsLinkedHashMap;
    }

    /**
     * Return team's total number of points
     * @return The Total number of points
     */
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s,%s",super.toString(),getPoints());
    }


    /* Siva Reddy. (2017). How to use User defined Object as key in HashMap with an example.
     * Available from https://www.youtube.com/watch?v=HlpWrH3CcoM
     * [Accessed 18 November 2021].
     */
    /**
     * Storing all team details into the "DriverDetails.txt" file includes Team name, Driver name, location, and the total score of each team.
     * @param driverDetails Obtain the LinkedHashMap containing driver details.
     */
    public void storeDriverDetails(LinkedHashMap<String,Formula1Driver> driverDetails){
        Set<Map.Entry<String, Formula1Driver>> set = driverDetails.entrySet();//(Siva Reddy, 2017)
        try {
            FileWriter myWriter = new FileWriter("DriverDetails.txt");
            for (Map.Entry<String, Formula1Driver> data: set){
                myWriter.write(data.getKey()+","+data.getValue()+"\n");
            } myWriter.close();

        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
            e.printStackTrace(); }
    }

    /**
      * Loading the "DriverDetails.txt" file.
      * which includes Team name, Driver name, location and the total score of each team.
      * The method will add the data into the {@link #driverDetailsLinkedHashMap} LinkedHashMap.
      */
    public void loadingDriverDetails() {

        String line;
        String driver;
        String team;
        String location;
        int point;

        try {
            File myObj = new File("DriverDetails.txt");
            BufferedReader myReader = new BufferedReader(new FileReader(myObj));
            while ((line = myReader.readLine()) != null) {
                String[] data=line.split(",");
                driver = data[1];
                team = data[0];
                location = data[2];
                point = Integer.parseInt(data[3]);
                driverDetailsLinkedHashMap.put(team, new Formula1Driver(driver, location, point));

            }
        }
        catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace(); }
    }

    /**
     * Changing the driver of an existing team
     * <p>
     * The method is Obtaining the existing car manufacturer.
     * It will change the driver details of a given team if the team competes.
     * Every word in the input data will convert to capitalize by using {@link Formula1ChampionshipManager#wordCapitalize(String)}.
     * The new driver name and the location cannot include any numbers or symbols and will check using  {@link Formula1ChampionshipManager#isValidName(String)}.
     * </p>
     */
    @Override
    public void changeDriver() {
        String manufacturerName;
        String driverName;
        String location;
        do{
        System.out.print("Enter the name of the car manufacturer: ");
        manufacturerName = INPUT.nextLine();}while (new Formula1ChampionshipManager().isInvalidTeam(manufacturerName));
        manufacturerName= Formula1ChampionshipManager.wordCapitalize(manufacturerName);

        if(driverDetailsLinkedHashMap.containsKey(manufacturerName)){
            do{
                System.out.print("Enter the name of the new driver: ");
                driverName = INPUT.nextLine();}while (!new Formula1ChampionshipManager().isValidName(driverName));
                driverName= Formula1ChampionshipManager.wordCapitalize(driverName);

            do {
                System.out.print("Enter the location: ");
                location = INPUT.nextLine();}while (!new Formula1ChampionshipManager().isValidName(location));
                location= Formula1ChampionshipManager.wordCapitalize(location);


            Formula1Driver old = driverDetailsLinkedHashMap.get(manufacturerName);
            driverDetailsLinkedHashMap.replace(manufacturerName, old, new Formula1Driver(driverName,location,old.getPoints()));
            System.out.println("Driver's name successfully changed/updated\n");
            storeDriverDetails(driverDetailsLinkedHashMap);
        }
        else{
            System.out.printf("%s team is not registered for the competition.", manufacturerName).println("\n");
        }

    }
}
