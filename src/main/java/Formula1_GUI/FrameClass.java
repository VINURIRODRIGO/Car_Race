package Formula1_GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class FrameClass extends JFrame{
    ImageIcon trophies;
    ImageIcon race;
    ImageIcon logo;
    JLabel label;
    static ButtonClass descending, ascending, winnerOrder, searchButton, raceDates, startGame, startGame2;
    /**
     * Creating the Frame
     */
    public FrameClass(){
        super("Formula 1 Championship"); // set title of the frame
        setLayout(new FlowLayout());
        JLabel heading = new JLabel("Formula 1 Championship"); // heading of the frame
        heading.setBounds(180, 20, 1300, 40);
        heading.setFont(new Font("Times new Roman", Font.BOLD,40));
        heading.setForeground(Color.darkGray);
        // a GUI display area for the images and the text
        label = new JLabel();
        logo = new ImageIcon("src/main/java/Images/images.png");// creating an ImageIcon
        trophies = new ImageIcon("src/main/java/Images/car_trophies.png");
        race = new ImageIcon("src/main/java/Images/Car_Race.gif");

        this.getContentPane().setBackground(new Color(185, 144, 120));// change color of the frame
        this.getContentPane().setForeground(Color.white);
        this.setBounds(150, 150, 850, 1000);
        trophiesImage();
        MatteBorder matteBorder = new MatteBorder(10,10,10,10,Color.black);

        // Add buttons to the frame
        addDescendingButton(this);
        addAscendingButton(this);
        addWinnerOrderButton(this);
        addSearchButton(this);
        addRaceDatesButton(this);
        addStartGameButton(this);
        addStartGame2Button(this);

        label.setBorder(matteBorder);
        Container displayImages = this.getContentPane();
        displayImages.add(label);
        add(label);
        add(heading);
        setIconImage(logo.getImage()); // change icon of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of the application
        getContentPane().setLayout(null);
        setResizable(false);// prevent frame from being resized
        setVisible(true);// make frame visible

    }

    /**
     * Adding the "Car_Race.gif" to the frame
     */
    public void raceImage(){
        label.setIcon(race);
        label.setBounds(100, 560, 610, 360);
    }

    /**
     * Adding the "car_trophies.png" to the frame
     */
    public void trophiesImage(){
        label.setIcon(trophies);
        label.setBounds(230, 580, 350, 370);
    }

    // creating buttons
    /**
     * Creating Descending Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addDescendingButton(JFrame frame){
        descending = new ButtonClass(30, 400, 250, 60, frame, "src/main/java/Images/descending scores.png");
    }

    /**
     * Creating Descending Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addAscendingButton(JFrame frame){
       ascending = new ButtonClass(300, 400, 250, 60, frame, "src/main/java/Images/ascending Scores.png");
    }

    /**
     * Creating Winner Order Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addWinnerOrderButton(JFrame frame){
        winnerOrder = new ButtonClass(560, 400, 250, 60, frame, "src/main/java/Images/winners_order.png");
    }

    /**
     * Creating Search Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addSearchButton(JFrame frame){
        searchButton = new ButtonClass(750, 60, 50, 69, frame, "src/main/java/Images/search_button.png");
    }

    /**
     * Creating Search Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addRaceDatesButton(JFrame frame){
        raceDates = new ButtonClass(10, 490, 230, 60, frame, "src/main/java/Images/Race_dates.png");
    }

    /**
     * Creating Start Game Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addStartGameButton(JFrame frame){
        startGame = new ButtonClass(280, 490, 230, 60, frame, "src/main/java/Images/Race_1.png");
    }

    /**
     * Creating Start Game2 Button using {@link ButtonClass}
     * @param frame JFrame
     */
    public void addStartGame2Button(JFrame frame){
        startGame2 = new ButtonClass(540, 490, 230, 60, frame, "src/main/java/Images/race_2.png");
    }

}
