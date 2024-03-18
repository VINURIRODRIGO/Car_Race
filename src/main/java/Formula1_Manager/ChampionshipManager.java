package Formula1_Manager;

import java.util.ArrayList;

public interface ChampionshipManager {
    int LastEndPointReceiver = 10;
    void addDriver();
    void deleteDriver();
    void driverStatistics();

    /**
     * Updating the positions by adding the current position
     * @param manufacturer Team name (manufacturer name)
     * @param position current race position
     */
    static void updatingPosition(String manufacturer, int position) {

        try {
            new Formula1Runner().loadTotalScoresArray();
            ArrayList<Integer> totalPosition = new ArrayList<>();
            ArrayList <Integer> x = Formula1Runner.getTotalPositionLinkedHashMap().get(manufacturer);
            //updating the position array
            for (int i=0; i< x.size(); i++){
                if (i==position){
                    totalPosition.add(x.get(i)+1);
                }
                else{
                    totalPosition.add(x.get(i));
                }
            }
            Formula1Runner.getTotalPositionLinkedHashMap().put(manufacturer,totalPosition);
        }
        catch (NullPointerException e){
            ArrayList<Integer> totalPosition = new ArrayList<>();
            for (int i=0; i< LastEndPointReceiver; i++){
                if (i==position){
                    totalPosition.add(1);
                }
                else {
                    totalPosition.add(0);
                }
            }
            Formula1Runner.getTotalPositionLinkedHashMap().put(manufacturer,totalPosition);
        }
    }

}
