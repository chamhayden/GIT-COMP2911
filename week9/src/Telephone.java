import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.applet.Applet;
import java.awt.GridBagLayout;

/**
   Presents a phone GUI for the voice mail system.
*/
public class Telephone extends Applet
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
      Constructs a telephone with a speaker, keypad, and microphone.
   */
   public Telephone()
   {
      initialise();
   }
   
   public void initialise()
   {
	   JFrame telephoneFrame = new JFrame("GridBagLayoutDemo");
	   telephoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       addComponentsToPane(telephoneFrame.getContentPane());
       telephoneFrame.pack();
       telephoneFrame.setVisible(true);
   }
   
   
   public void addComponentsToPane(Container pane) {
       if (RIGHT_TO_LEFT) {
           pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
       }

       JButton panelButton;
	JButton panelHangup;
	JButton panelSend;
       JLabel panelLabel;
       JTextArea panelTextArea;
       
       pane.setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
       c.fill = GridBagConstraints.HORIZONTAL;
       
       int gridY = 0;

       c.weightx = 1.0;
       c.gridwidth = 3;
       
       panelLabel = new JLabel("Speaker:");
       c.gridx = 0;
       c.gridy = gridY++;
       pane.add(panelLabel, c);
     
       panelTextArea = new JTextArea("Please enter mailbox number followed by #", 10,10);
       c.gridx = 0;
       c.gridy = gridY++;
       pane.add(panelTextArea, c);
       
       
       
       final String values[][] = {
    		   {"1", "2", "3"},
    		   {"4", "5", "6"},
    		   {"7","8","9"},
    		   {"*","0","#"}
       };
       
       c.gridwidth = 1;
       for (int i = 0; i < BUTTONS_HEIGHT; i++)
       {
    	   for (int j = 0; j < BUTTONS_WIDTH; j++)
    	   {
    		   final String buttonText = values[i][j];
    		   panelButton = new JButton(buttonText);
    		   c.gridx = j;
    		   c.gridy = gridY;
    		   panelButton.addActionListener(new
	             ActionListener()
	               {
	                  public void actionPerformed(ActionEvent event)
	                  {
	                     connect.dial(buttonText);
	                  }
	               });    		   
    		   pane.add(panelButton, c);
    		   
    	   }
    	   gridY++;
       }       
       
       c.gridwidth = 3;
       
       panelLabel = new JLabel("Microphone:");
       c.gridx = 0;
       c.gridy = gridY++;
       pane.add(panelLabel, c);
     
       final JTextArea microphoneField = new JTextArea(10,25);
       c.gridx = 0;
       c.gridy = gridY++;
       pane.add(microphoneField, c);

       c.gridx = 0;
       c.gridy = gridY++;
       JPanel actions = new JPanel();
       actions.setLayout(new BorderLayout());

	panelHangup = new JButton("Hangup");
	panelHangup.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               //connect.hangup();
            }
         });

	panelSend = new JButton("Send Speech");
	panelSend.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               //connect.record(microphoneField.getText());
               microphoneField.setText("");
            }
         });

       actions.add(panelSend, BorderLayout.WEST);
       actions.add(panelHangup, BorderLayout.EAST);
       pane.add(actions, c);
       
   }
   
   public void speak(String output)
   {
      speakerField.setText(output);
   }

   public void run(Connection c)
   {
      connect = c;
   }

   private JTextArea speakerField;
   private Connection connect;
   
   final static boolean shouldFill = true;
   final static boolean shouldWeightX = true;
   final static boolean RIGHT_TO_LEFT = false;

   private static final int BUTTONS_WIDTH = 3;
   private static final int BUTTONS_HEIGHT = 4;
}