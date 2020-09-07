package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Scanner;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Keoabetswe Nthite 13019459
 */
public class HangMan 
{
  static String[] blanks;
  static int limit=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Call the path to the text file
        Path filePath = Paths.get("C:\\Users\\hp\\Documents\\NetBeansProjects\\Hangman\\HangmanWords.txt");
        InputStream input = null;
       try
        {
          
           input = Files.newInputStream(filePath);
           BufferedReader reader = new BufferedReader(new InputStreamReader(input));
           String word = null;
           int Letters =0;
           Scanner hangman = new Scanner(System.in);
           String answer = "";
           
           System.out.println("****************");
           System.out.println("*              *");
           System.out.println("*   Hangman    *");
           System.out.println("*              *");
           System.out.println("****************");
           
           
           
            for(int x = 0; x < 10 ; ++x)
            {               
                
                word = reader.readLine(); 
                Letters = word.length(); 
                 
                if(x > 0)
                {
                    System.out.print("Would you like to play again?");
                    limit = 0;
                    answer = hangman.nextLine();
                     
                    if(answer.equals("no"))
                    {
                      break;
                    }                     
                }
                else 
                {
                    System.out.print("Would you like to play? ");
                    limit = 0;
               
                    answer = hangman.nextLine();
                     
                    if(answer.equals("no") || answer.equals("n"))
                    {
                      break;
                    }   
                }
                
                PlayGame(word, Letters);                          
            }            
               
           input.close();
       }
       catch (IOException words)
       {
           System.out.println(words);
       }
    }
    
   public static void PlayGame(String Word, int numLetters)
   {
      
     String Guessed = ""; 
     String end = "zzz";
     boolean next = false;
     int lives = 6;
     Scanner hangman = new Scanner(System.in);
     blanks = new String[Word.length()];
     System.out.println("You have " + lives + " lives.");
     
    
        for(int num=0; num <numLetters; ++num)
           {
             blanks[num] = "-";
             System.out.print(blanks[num]);
           }
        System.out.println();
      
     
       while(!Guessed.equals(end) && next != true)
       {
           if (limit == Word.length())
           {
              System.out.println(" Well done you have completed the word :)");
              limit = 0;
              break; 
               
           }
           
            System.out.print("Enter a letter or zzz to quit: ");
            Guessed = hangman.nextLine();
            
            if(!Guessed.equals(end))
              {
                  if (checkWord(Word, Guessed, blanks) !=true)
                  {
                      lives -= 1;     
                      if (lives > 0 && lives <6)
                      {
                        System.out.println();
                        System.out.println("You did not get the letter correct." + "\nYou have " + lives + " lives left");  
                        System.out.println();
                       
                      }
                      else if(lives == 0)
                      {
                        System.out.println();  
                        System.out.println(" You failed to complete the word.");
                        System.out.println();
                        limit = 0;
                        
                       
                        break;
                      }
                      
                  }                  
              }      
            else
              {
               System.out.println();
               System.out.println("Thanks for playing");  
               break;
              }            
       }    
   }
   
   public static boolean checkWord(String word, String lettersGuessed,String[] dashes)
   {
      
     boolean correct = false;     
     int numDashes;       
     int bodyParts =6;
     numDashes = word.length();
     String[] arrayWord = new String[numDashes];         
          
     
      for(int x=0; x< numDashes; ++x)
      {         
         arrayWord[x] = word.substring(x,x+1);
       
        if (lettersGuessed.equals(arrayWord[x]))
         {         
             limit += 1;             
             dashes[x] = lettersGuessed;  
             correct = true;    
             
         }            
      }     
      
         for(int y=0; y< numDashes; ++y)
             {
                  System.out.print(dashes[y]);
             }    
          System.out.println();
    
    return correct;
   }  
}
