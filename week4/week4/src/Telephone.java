import java.util.Scanner;
import java.util.ArrayList;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Telephone
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
   public Telephone(Scanner aScanner, MailSystem mailsystem)
   {
      scanner = aScanner;
      this.mailsystem = mailsystem;
      connections = new ArrayList<Connection>();    
   }

   /**
      Speak a message to System.out.
      @param output the text that will be "spoken"
   */
   public void speak(String output)
   {
      System.out.println(output);
   }

   /**
      Loops reading user input and passes the input to the
      Connection object's methods dial, record or hangup.
      @param c the connection that connects this phone to the
      voice mail system
   */
   public void run()
   {
	  currentConnectionIndex = defaultConnection; 
	  connections.add(new Connection(mailsystem, this));  
	  
      boolean more = true;
      while (more)
      {
    	 
    	 String input = scanner.nextLine();
         if (input == null) return;
         else if (input.contains(":")) {
        	 String connectionString = input.substring(0, input.indexOf(":"));
        	 Integer connectionNumber = Integer.valueOf(connectionString);
        	 while (connections.size() <= connectionNumber.intValue())
        	 {
        		  connections.add(null);
        	 }
        	 if (connections.get(connectionNumber.intValue()) == null)
        	 {
        		 Connection newCon = new Connection(mailsystem, this);
        		 connections.set(connectionNumber.intValue(), newCon);
        	 }
        	 currentConnectionIndex = connectionNumber.intValue();
        	 c = connections.get(currentConnectionIndex);
         }
         if (input.equalsIgnoreCase("H"))
            c.hangup();
         else if (input.equalsIgnoreCase("Q"))
            more = false;
         else if (input.length() == 1
            && "1234567890#".indexOf(input) >= 0)
            c.dial(input);
         else
            c.record(input);
      }
   }

   private Scanner scanner;
   private ArrayList<Connection> connections;
   private MailSystem mailsystem;
   private int currentConnectionIndex;
   private Connection c;
   private static final int defaultConnection = 0;
}
