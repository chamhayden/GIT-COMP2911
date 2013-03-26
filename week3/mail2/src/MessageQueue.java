
/**
   A first-in, first-out collection of messages. This
   implementation is not very efficient. We will consider
   a more efficient implementation in chapter 3.
*/
public class MessageQueue
{
   /**
      Constructs an empty message queue.
   */
   public MessageQueue()
   {
	   queue = new Message[SIZE];
	   int pointer = 0;
   }
   
   /**
    * Deletes a message from the MessageQueue
    * @param message message from MessageQueu
    * @param index index of item to be deleted
    */
   public void delete(Message message)
   {
	   int Location = -1;
	   for (int c = 0; c <= pointer; c++)
	   {
		   if (queue[c] == message)
		   {
			   Location = c;
		   }
	   }
	   if (Location >= 0)
	   {
		   for (int c = (Location + 1); c <= pointer; c++)
		   {
		      queue[c - 1] = queue[c];
		   }
		   pointer--;
	   }
   }

   /**
      Remove message at head.
      @return message that has been removed from the queue
   */
   public Message remove()
   {
	  Message temp = queue[0];
      for (int c = 1; c <= pointer; c++)
      {
    	  queue[c - 1] = queue[c];
      }
      pointer--;
      return temp;
   }

   /**
      Append message at tail.
      @param newMessage the message to be appended
   */
   public void add(Message newMessage)
   {
      queue[pointer] = newMessage;
      pointer++;
   }

   /**
      Get the total number of messages in the queue.
      @return the total number of messages in the queue
   */
   public int size()
   {
	   return pointer;
   }

   /**
      Get message at head.
      @return message that is at the head of the queue, or null
      if the queue is empty
   */
   public Message peek()
   {
      return queue[0];
   }
   
   /**
    * @param Input index
    * @return Returns string of element, given it's index
    */
   public String getStr(int c)
   {
	   return queue[c].getText();
   }
   
   private Message[] queue;
   private static final int SIZE = 10; 
   private int pointer;
}