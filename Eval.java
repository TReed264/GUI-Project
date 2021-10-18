package databaseexample;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.derby.jdbc.*;


/**
*
* @author brilaw
*/
public class Eval extends JFrame implements ActionListener, ItemListener
{
//DECLARE THE ELEMENTS OR OBJECTS THAT YOU WILL PUT IN YOUR FRAME
//NOTICE HOW A PANEL IS CREATED FOR EACH ONE THIS WILL MAKE IT EASIER BUILD

    public JLabel teamLabel;
    private JComboBox teamComboBox;
    private JPanel teamPanel;


    private JLabel questionLabel;
    private JLabel questionLabel2;
    private JLabel questionLabel3;
    private JLabel questionLabel4;

    
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JPanel questionPanel;
    private ButtonGroup questionGroup1;


    private JButton submitButton;
    private JButton clearButton;
    private JButton averageButton;
    private JPanel buttonPanel;
    private JPanel clearPanel;

    //instance variables to hold our data from the gui object to update the database
    String myteamname;
    // String courseName;
    int q1;
    int q2;
    int q3;
    int q4;
    double teamavg;
    boolean avgcalculated;
    String teamcomments;
    // instance variables used to manipulate database
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;

 

    //MAIN METHOD: NOTICE WE TAKE IN THE ARGUMENTS THAT ARE
    //PASSED IN AND INSTANTIATE OUR CLASS WITH THEM
    public static void main(String args[])
    {
        
        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        //String databaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
       
        String databaseURL = "jdbc:derby://localhost:1527/Eval";

        // create new Eval
        Eval eval = new Eval( databaseDriver, databaseURL );
        //eval.createUserInterface();
         eval.createUserInterface(); // set up GUI
         
        eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
         
    }

    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
    //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval(String databaseDriver, String databaseURL)
    {
        // establish connection to database
            try
            {
                //Class.forName( "org.apache.derby.jdbc.ClientDriver");
                DriverManager.registerDriver(new ClientDriver());
                // connect to database
                myConnection = DriverManager.getConnection( "jdbc:derby://localhost:1527/Eval" );

                // create Statement for executing SQL
                myStatement = myConnection.createStatement();
            }
            catch ( SQLException exception )
            {
                System.out.println(exception.getMessage());
            }
            //catch ( ClassNotFoundException exception )
            //{
            //  exception.printStackTrace();
            //}   
            

    }

    void createUserInterface()
    {
        // get content pane for attaching GUI components
        Container contentPane = getContentPane();

        // enable explicit positioning of GUI components
        contentPane.setLayout( null );

        // TEAM COMBO BOX SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // set up Team Panel
        //Teams text and drop down menu for selecting teams within border
        teamPanel = new JPanel();
        teamPanel.setBounds(30, 20, 470, 48 );
        teamPanel.setBorder( BorderFactory.createEtchedBorder() );
        teamPanel.setLayout( null );
        contentPane.add( teamPanel );

        // set up Instructor Label
        // teams text
        teamLabel = new JLabel();
        teamLabel.setBounds( 15, 13, 100, 20 );
        teamLabel.setText( "TEAMS:" );
        teamPanel.add( teamLabel );

        // set up accountNumberJComboBox
        //teams drop down menu
        teamComboBox = new JComboBox();
        teamComboBox.setBounds( 170, 10, 275, 25 );
        teamComboBox.addItem( "" );
        teamComboBox.setSelectedIndex( 0 );
        teamPanel.add( teamComboBox );


        //RADIO BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // set up Question Panel and Radio Buttons
        //question box (text and option buttons)
        questionPanel = new JPanel();
        questionPanel.setBounds( 30, 80, 470, 245 );
        questionPanel.setBorder( BorderFactory.createEtchedBorder() );
        questionPanel.setLayout( null );
        contentPane.add( questionPanel );

        // set up question1 Label
        //question text
        questionLabel = new JLabel();
        questionLabel.setBounds( 15, 20, 115, 20 );
        //questionLabel.setBorder(BorderFactory.createEtchedBorder());
        questionLabel.setText( "Q1: Technical?" );
        questionPanel.add( questionLabel );
        
        questionLabel2 = new JLabel();
        questionLabel2.setBounds( 15, 82, 115, 20 );
        //questionLabel2.setBorder(BorderFactory.createEtchedBorder());
        questionLabel2.setText( "Q2: Useful?" );
        questionPanel.add( questionLabel2 );
        
        questionLabel3 = new JLabel();
        questionLabel3.setBounds( 15, 144, 115, 20 );
       // questionLabel3.setBorder(BorderFactory.createEtchedBorder());
        questionLabel3.setText( "Q3: Clarity?" );
        questionPanel.add( questionLabel3 );
        
        questionLabel4 = new JLabel();
        questionLabel4.setBounds( 15, 206, 115, 20 );
        //questionLabel4.setBorder(BorderFactory.createEtchedBorder());
        questionLabel4.setText( "Q4: Overall?" );
        questionPanel.add( questionLabel4 );

        // set up the radio buttons for question 1
        //buttons to select score
//        rb1 = new JRadioButton( "1", false );
//        rb1.setBounds(20, 30, 40, 40 );
//        rb1.setVisible(true);
//        rb1.addItemListener(this);
//
//        rb2 = new JRadioButton("2", false);
//        rb2.setBounds(80, 30, 40, 40 );
//        rb2.setVisible(true);
//        rb2.addItemListener(this);
//
//        rb3 = new JRadioButton( "3", false );
//        rb3.setBounds(140, 30, 40, 40 );
//        rb3.setVisible(true);
//        rb3.addItemListener(this);
//
//        // create logical relationship between JRadioButtons
//        questionGroup1 = new ButtonGroup();
//        questionGroup1.add( rb1 );
//        questionGroup1.add( rb2 );
//        questionGroup1.add( rb3 );
//
//        // add radio button to the panel
//        questionPanel.add( rb1 );
//        questionPanel.add( rb2 );
//        questionPanel.add( rb3 );

        // SUBMIT BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        buttonPanel = new JPanel();
        buttonPanel.setBounds( 100, 550, 120, 40 );
        //buttonPanel.setBorder( BorderFactory.createEtchedBorder() );
        buttonPanel.setLayout( null );
        contentPane.add( buttonPanel );

        submitButton = new JButton( "SUBMIT" );
        submitButton.setBounds(10, 5, 100, 30);
        submitButton.setVisible(true);
        buttonPanel.add(submitButton);
        submitButton.addActionListener(this);
        
        clearPanel = new JPanel();
        clearPanel.setBounds(300, 550, 120, 40);
        //clearPanel.setBorder(BorderFactory.createEtchedBorder());
        clearPanel.setLayout( null );
        contentPane.add(clearPanel);
        
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(10, 5, 100, 30);
        clearButton.setVisible(true);
        clearPanel.add(clearButton);
        clearButton.addActionListener(this);
        
        

 
        //slider
        JSlider myslider = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        myslider.setBounds(170, 7, 280, 45);
        //myslider.setBorder(BorderFactory.createEtchedBorder());
        questionPanel.add(myslider);
        
        JSlider myslider2 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        myslider2.setBounds(170, 69, 280, 45);
        //myslider2.setBorder(BorderFactory.createEtchedBorder());
        questionPanel.add(myslider2);
        
        JSlider myslider3 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        myslider3.setBounds(170, 131, 280, 45);
        //myslider3.setBorder(BorderFactory.createEtchedBorder());
        questionPanel.add(myslider3);
        
        JSlider myslider4 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        myslider4.setBounds(170, 193, 280, 45);
        //myslider4.setBorder(BorderFactory.createEtchedBorder());
        questionPanel.add(myslider4);
       
        
        //read teams from database and
        // place them in teamsJComboBox
        loadTeams();
        

        setTitle( "Team presentation grading" ); // set title bar string
        setSize( 550, 650 ); // set window size
        setVisible( true ); // display window
    }

    //OVERRIDING THIS FUNCTION BECAUSE OUR CLASS IMPLEMENTS THE ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent event)
    {
        
        if(event.getSource().equals(submitButton))
        {
            myteamname = (String)teamComboBox.getSelectedItem();


            if ( rb1.isSelected())
            {
                q1 = Integer.parseInt(rb1.getText());
            }
            else if (rb2.isSelected())
            {
                q1 = Integer.parseInt(rb2.getText());
            }
            else if (rb3.isSelected())
            {
                q1 = Integer.parseInt(rb3.getText());
            }

            q2 = 8;
            q3 = 2;
            q4 = 5;
            teamavg = ((q1+q2+q3+q4)/4);
            teamcomments = "Not a bad presentation not a good one either";

            updateTeams();
        }
        // else if(event.getSource().equals(clearButton))
        // {
        // textavgtextbox.text = "";
        // submitButton.setEnabled(false);
        // }
        // else if(event.getSource().equals(teamavgButton))
        // {
        // int tempval1 = slidertechnical.getValue();
        // int tempval2 = slideruse.getValue();
        // int tempval3 = sliderclarity.getValue();
        // int tempval4 = slideroverall.getValue();
        // teamavg = (double)(tempval1 + teampval2 + tempval3 + tempval4)/4.0
        // teamavgTextBox.text = teamavg;
        // submitButton.setEnabled(true);
        // avgcalculated = true;

    }

    @Override
    public void itemStateChanged(ItemEvent event)
    {

        if ( event.getSource() == rb1 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb1.getText());
        }
        else if (event.getSource() == rb2 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb2.getText());
        }
        else if (event.getSource() == rb3 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb3.getText());
        }
        else if( event.getSource() == rb1 && event.getStateChange() == ItemEvent.DESELECTED)
        {
            JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
        }
    }
    
    private void updateTeams()
    {
        // update teams in database
        try
        {
            // Below is an example of creating a SQL statement that updated more than a single field in one statement.
            String sql1 = "UPDATE APP.TEAM SET Q1 = " + q1
            + ", Q2 = " + q2
            + ", Q3 = " + q3
            + ", Q4 = " + q4
            + ", TEAMAVG = " + teamavg
            + ", COMMENTS = " + "'" + teamcomments
            + "'" + "WHERE " + "TEAMNAME = " + "'" + myteamname + "'";
            String sql2 = "UPDATE APP.TEAM SET Q2USEFUL = " + q2 + " WHERE " + "TEAMNAME = " + "'" + myteamname + "'";
            // String sql3;
            // String sql4;
            // String sql5;
            // String sql6 = "UPDATE APP.TEAM SET COMMENTS = " + "'" + teamcomments + "'" + " WHERE " + "TEAMS = " + "'" + myteamname + "'";
            myStatement.executeUpdate(sql1);
        }
        catch ( SQLException exception )
        {
            exception.printStackTrace();
        }

    } // end method updateBalance
    
    private void loadTeams()
    {
    // get all account numbers from database
        try
        {

            myResultSet = myStatement.executeQuery( "SELECT TEAMNAME FROM APP.TEAM" );
            // add account numbers to accountNumberJComboBox
            while ( myResultSet.next() )
            {
                teamComboBox.addItem( myResultSet.getString( "TEAMNAME" ) );
            }

            myResultSet.close(); // close myResultSet

        } // end try
        catch ( SQLException exception )
        {
            System.out.println(exception.getMessage());
        }
    }
}