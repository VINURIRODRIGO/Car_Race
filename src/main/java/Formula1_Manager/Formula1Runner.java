package Formula1_Manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * Oracle. (no date). Java API Reference. LinkedHashMap (Java SE 11 & JDK 11).
 * Available from https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashMap.html
 * [Accessed 18 November 2021].
 */

/**
 * Formula1 Race management
 */
public class Formula1Runner {

    private static final int MIN =1;
    private static final int MAX = 100;
    private static final Formula1Driver fd = new Formula1Driver();
    /**
     * ArrayList data that includes end positions for all teams in the second race
     */
    private static ArrayList<String> raceEndPosition;
    /**
     * ArrayList data that includes, starting positions for all teams in the second race
     */
    private static ArrayList<String> raceStartPosition;
    /**
     * Array that includes points awarded for each driver in a race
     */
    private final int [] POINTS = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
    private static final LinkedHashMap<String, ArrayList<Integer>> totalPositionLinkedHashMap = new LinkedHashMap<>(); //(Oracle, no date)
    private static final LinkedHashMap<String, Formula1Driver> raceEndPositions = new LinkedHashMap<>();
    private static final List<GetDateAndStatistics> DateList = new ArrayList <>();

    /**
     * Return the {@link #POINTS} Array includes points awarded for each driver in a race
     * @return The {@link #POINTS} Array
     */
    public int[] getPOINTS() {
        return POINTS;
    }

    /**
     * Return the {@link #raceEndPosition} ArrayList data including, all teams' end positions in the second race
     * @return The {@link #raceEndPosition} ArrayList
     */
    public static ArrayList<String> getRaceEndPosition() {
        return raceEndPosition;
    }

    /**
     * Return the {@link #raceStartPosition} ArrayList data including, all teams' starting positions in the second race
     * @return The {@link #raceStartPosition} ArrayList
     */
    public static ArrayList<String> getRaceStartPosition() {
        return raceStartPosition;
    }

    /**
     * Return the {@link #totalPositionLinkedHashMap} LinkedHashMap data, including manufacturers, and array including the total of all the first ten positions
     * @return The {@link #totalPositionLinkedHashMap} LinkedHashMap
     */
    public static LinkedHashMap<String, ArrayList<Integer>> getTotalPositionLinkedHashMap() {
        return totalPositionLinkedHashMap;
    }

    /**
     * Return the {@link #raceEndPosition} LinkedHashMap data including, manufacturers, Driver names, locations, and the final places of each team in a race.
     * @return The {@link #raceEndPosition} LinkedHashMap
     */
    public static LinkedHashMap<String, Formula1Driver> getRaceEndPositions() {
        return raceEndPositions;
    }

    /**
     * Return the {@link #DateList} List data that includes manufacturers, race dates, and race end positions
     * @return The {@link #DateList} List
     */
    public static List<GetDateAndStatistics> getDateList() {
        return DateList;
    }

    /**
     * Creating a manual race
     * <p>
     * Obtain the race's end position of each player and the race date.
     * One player cannot have two places.
     * And the race's end position should be less than or equal to the number of drivers.
     * The date cannot include a future date, including the ISO date format. For example, a text should be formatted as "1970-01-01".
     * Date validation will check using {@link #dateValidation(String)}.
     * </p>
     *
     * @param driverDetailsLinkedHashMap LinkedHashMap which contain driver detail
     */
    public void manualRace(LinkedHashMap<String, Formula1Driver> driverDetailsLinkedHashMap){
        final Scanner input = new Scanner(System.in);
        List<String> race = new ArrayList<>(driverDetailsLinkedHashMap.keySet());
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<String> endPositions = new ArrayList<>();
        LocalDate date;
        String dateInput;
        boolean valid;
        int position = 0;
        int count = 0;
        for (Formula1Driver x : driverDetailsLinkedHashMap.values()){

            System.out.printf("%d.  %s",count+1,x.getDriverName()).println();
            count++;
        }
        do {
            System.out.print("Enter the Date ('YYYY-MM-DD'): ");
            dateInput = input.nextLine();
            valid = dateValidation(String.valueOf(dateInput));

        } while (!valid);// Getting a valid date

        date = LocalDate.parse(dateInput);
        count=0;
        while (count<driverDetailsLinkedHashMap.size()){
            // Getting race's end position of each player
            System.out.printf("Provide the %d position: ", count+1);
            try {
                position = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Invalid input");
                e.printStackTrace();
            }

            if (position<=race.size()){
                if(!pos.contains(position-1)){
                String d= race.get(position-1);
                pos.add(position-1);
                endPositions.add(d);//Adding the race's end position to an  endPositions array
                count++;}
                else {
                    System.out.printf("You have already provide a position for %d",position).println("\n");
                }
            }
            else{
            System.out.printf("Only %d are in the competition", race.size()).println("\n");}
        }
        update(endPositions, date);
    }

    /*
     * Yarram, A. (2010).validation - How to sanity check a date in Java. Stack Overflow.
     * Available from https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
     * [Accessed 1 December 2021].
     *----------------------------
     * Avinash, A. (2021). java - Ask user again if input date is in the future. Stack Overflow.
     * Available from https://stackoverflow.com/questions/66613460/ask-user-again-if-input-date-is-in-the-future
     * [Accessed 2 December 2021].
     *
     */
    /**
     * Return true if the date is a valid date.
     * <p>
     * Return true when the date is not a future date and in a correct format (ISO-8601 calendar format).
     * @param date Obtain date in String format
     * </p>
     * @return Whether the date is a valid date or not
     */
    public static boolean dateValidation(String date){
        String DATE_FORMAT ="yyyy-MM-dd";
        LocalDate today = LocalDate.now();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
        try {
            LocalDate  tDate = LocalDate.parse(date, parser);

            // Check whether the date ia a future date or not
            if (!tDate.isAfter(today)) { // (Avinash, 2021)
                // Check that the format is in the ISO-8601 calendar format
                DateFormat formatter = new SimpleDateFormat(DATE_FORMAT); // (Yarram, 2010)
                formatter.setLenient(false);
                formatter.parse(date);
                return true;
            }
            System.out.println("The date should not be a future date. Please try again.\n");
            return false;
        }catch (Exception e){
            System.out.println("Invalid date/format input. Please try again. \n");
        }
        return false;
    }

    /**
     * Loading the "TotalScoresArray.txt" file.
     * <p>It contains the team name and the number of the first 10.
     * The method will add the data into the {@link #totalPositionLinkedHashMap} LinkedHashMap</p>
     */
    public void loadTotalScoresArray() {
        String line;
        String team;
        try {
            File myObj = new File("TotalScoresArray.txt");
            BufferedReader myReader = new BufferedReader(new FileReader(myObj));
            while ((line = myReader.readLine()) != null) {
                line = line.replace("[","").replace("]","");
                line = line.replace(" ","");
                String[] data=line.split(",");
                team = data[0];
                ArrayList<Integer> totalPoints = new ArrayList<>();
                for(int i=1;i<11; i++){
                    totalPoints.add(Integer.valueOf(data[i]));
                }
                totalPositionLinkedHashMap.put(team, totalPoints);
            }
        }
        catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace(); }
    }

    /* Skinner, C. and Matthieu. (2010). iteration - Java HashMap: How to get a key and value by index? Stack Overflow.
     * Available from https://stackoverflow.com/questions/3973512/java-hashmap-how-to-get-a-key-and-value-by-index
     * [Accessed 18 November 2021].
     */

    ///https://java2blog.com/how-to-convert-hashmap-to-arraylist-in-java/
    /**
     * Storing data into the "TotalScoresArray.txt".
     * <p>
     * Storing all total positions details in {@link #totalPositionLinkedHashMap} LinkedHashMap into the "TotalScoreArray.txt" file includes team name and the total number of first ten end positions of each team in the races.
     * </p>
     */
    protected void storeTotalScoresArray() {
        Set<Map.Entry<String, ArrayList<Integer>>> set = totalPositionLinkedHashMap.entrySet();//(Skinner and Matthieu, 2010)
        try {
            FileWriter myWriter = new FileWriter("TotalScoresArray.txt");
            for (Map.Entry<String, ArrayList<Integer>> data: set){
                myWriter.write(data.getKey()+","+ data.getValue() +"\n");
            } myWriter.close();

        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
            e.printStackTrace(); }
    }
    //https://www.youtube.com/watch?v=9Xr-o_QWMeE
    /**
     * Storing data into the "RaceDate.json".
     * <p>
     * Storing all Race date details in {@link #DateList} file includes the team name, date, and the end position of the race.</p>
     */
    protected void storeRaceDate() {
        List<GetDateAndStatistics> tp = new ArrayList<>();
        // converting the LocalDate into String type before add to the json file.
        for (GetDateAndStatistics t: DateList){
            tp.add(new GetDateAndStatistics(t.getManufacture(), String.valueOf(t.getDate()), t.getPoint()));
        }
        try {
            ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
            om.writerWithDefaultPrettyPrinter().writeValue(new File("RaceDate.json"), tp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading the "RaceDate.json" file.
     * <p>
     * The text file contains the team names and the total number of ten end positions of each team in the races.
     * Add data into {@link #DateList} List.</p>
     */
    public void loadRaceDate() {
            try {
                ObjectMapper mapper = new ObjectMapper();
                File myObj = new File("RaceDate.json");
                InputStream inputStream = new FileInputStream(myObj);
                TypeReference<List<GetDateAndStatistics>> typeReference = new TypeReference<>() {
                };
                List<GetDateAndStatistics> tests = mapper.readValue(inputStream, typeReference);
                for (GetDateAndStatistics data : tests) {
                    DateList.add(new GetDateAndStatistics(data.getManufacture(), LocalDate.parse(data.getStrDate()), data.getPoint()));
                }
            } catch (Exception fileNotFoundException){
                fileNotFoundException.printStackTrace();
            }
    }

    /* (Topolnik, 2021)
     * Topolnik, M. (2021). java - Shuffling values in HashMap. Stack Overflow.
     * Available from https://stackoverflow.com/questions/10571712/shuffling-values-in-hashmap
     * [Accessed 18 November 2021].
     */
    /* How to Get Current Date and Time in Java - Javatpoint. (2021). www.javatpoint.com.
     * Available from https://www.javatpoint.com/java-get-current-date
     * [Accessed 19 November 2021].
     */
    /**
     * Generating a random race
     *
     */
    public void guiFirstRace() {
        fd.loadingDriverDetails();
        ArrayList<String> teamNames = new ArrayList<>(fd.getDriverDetailsLinkedHashMap().keySet());
        Collections.shuffle(teamNames, new Random());
        update(teamNames, LocalDate.now()); // (How to Get Current Date and Time in Java - Javatpoint, 2021)
    }

    /**
     * <p>
     * Updating the total score of {@link Formula1Driver#getDriverDetailsLinkedHashMap()} LinkedHashMap and
     * adding the end position of each driver to {@link #raceEndPosition} LinkedHashMap and to {@link #getDateList()}
     * </p>
     */
    public void update(ArrayList<String> teamNames, LocalDate date){
        // loading the text files
        loadTotalScoresArray();
        fd.loadingDriverDetails();

        for (int i=0; i< teamNames.size(); i++){
            String key = teamNames.get(i);
            Formula1Driver oldData = fd.getDriverDetailsLinkedHashMap().get(key);
            String driver = oldData.getDriverName();
            String location = oldData.getLocation();
            try {
                //Updating the positions
                ChampionshipManager.updatingPosition(key, i);
                int points = oldData.getPoints()+ getPOINTS()[i];// Adding new points to the total number of points

                //Updating the driver details by updating the total points.
                fd.getDriverDetailsLinkedHashMap().replace(key, oldData, new Formula1Driver(driver,location,points));
                Formula1Runner.getDateList().add(new GetDateAndStatistics(key, date, i+1));
                Formula1Runner.raceEndPositions.put(key, new Formula1Driver(driver,location,i+1));
            } catch (ArrayIndexOutOfBoundsException e){
                fd.getDriverDetailsLinkedHashMap().replace(key, oldData, new Formula1Driver(driver,location,oldData.getPoints()));
                Formula1Runner.getDateList().add(new GetDateAndStatistics(key, date, i+1));
                Formula1Runner.raceEndPositions.put(key, new Formula1Driver(driver,location,i+1));
            }
            // storing data to the text files
            storeTotalScoresArray();
            storeRaceDate();
        }
        System.out.println("Race successfully completed\n");
        fd.storeDriverDetails(fd.getDriverDetailsLinkedHashMap());
    }

    /**
     * Return the winner
     * @param winner Probability of winning
     * @return The winner of the race
     */
    public int selectingWinner(int winner){
        if (winner<41){
            return 1;
        }
        else if (winner<71){
            return 2;
        }
        else if (winner<81){
            return 3;
        }
        else if (winner<91){
            return 4;
        }
        else if (winner<93){
            return 5;
        }
        else if (winner<95){
            return 6;
        }
        else if (winner<97){
            return 7;
        }
        else if (winner<99){
            return 8;
        }
        else
            return 9;
    }

    /**
     * GUI Second race Generator
     * @throws IndexOutOfBoundsException Indicate that the array indicator is out of range.
     */
    public void guiSecondRace() throws IndexOutOfBoundsException{
        fd.loadingDriverDetails();
        raceEndPosition = new ArrayList<>(fd.getDriverDetailsLinkedHashMap().keySet());
        Collections.shuffle(raceEndPosition, new Random());
        raceStartPosition = new ArrayList<>(raceEndPosition);
        String winner;
        String changePosition;
        int position;
        do {
            int winnerPos = (int)(Math.random()*(MAX-MIN+1)+MIN);
            position = new Formula1Runner().selectingWinner(winnerPos);
        }while (position> raceEndPosition.size()-1);
        winner = raceEndPosition.get(position - 1);
        Collections.shuffle(raceEndPosition, new Random());
        int winner_index = raceEndPosition.indexOf(winner);
        changePosition = raceEndPosition.get(0);
        raceEndPosition.set(0, winner);
        raceEndPosition.set(winner_index, changePosition);
        update(raceEndPosition, LocalDate.now());
        }
}



