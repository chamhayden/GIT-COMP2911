import java.util.Scanner;

public class Comparison
{
   /**
      Reads in two lines of text from standard input, then prints whether or not the second line of text is contained within the first
   */
   public static void main(String[] args)
   {
      // Get two lines of text from system input
      Scanner sc = new Scanner(System.in);
      String first = sc.nextLine();
      String second = sc.nextLine();
      
      // Provide response based on whether second is within first
      if (first.contains(second))
      {
         System.out.println("Second string IS in the first string");
      }
      else
      {
         System.out.println("Second string is NOT in the first string");
      }   
   }
}