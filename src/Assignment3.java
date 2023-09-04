import java.io.*;
import java.util.*;

public class Assignment3
{
   public static void main(String[] args)
   {
   
      //read in the file
      File file = new File("Assignment3DataFile.txt");
      Scanner inputFile = null;
      
      //try catch block
      try
      {
         inputFile = new Scanner(file);
      } 
         catch (FileNotFoundException ex)
      {
         System.out.println("File not found");
      }
      
      //instantiate the trees
      BinarySearchTree mySaujanyaBST = new BinarySearchTree();
      AVLTree mySaujanyaAVLT = new AVLTree();
      
      //read in the Distance part of the file
      while(inputFile.hasNext())
      {
         String distance = inputFile.next();
         
         mySaujanyaBST.add(distance);
         mySaujanyaAVLT.add(distance);
      }
      
      //test/demonstrate all functionality/methods for both
      System.out.println("Testing all functionality/methods for both SaujanyaBinaryST and SaujanyaAVLT...");
      mySaujanyaBST.print();
      mySaujanyaAVLT.print();
      
      //read in the City1 part of the file
      while(inputFile.hasNext())
      {
         String city1 = inputFile.next();
         
         mySaujanyaHS.add(city1);
      }
      
      //test/demonstrate all functionality/methods for it
      System.out.println("Testing all functionality/methods for MySaujanyaHS...");
      mySaujanyaHS.print();
      
      //read in the City2 and Distance part of the file
      while(inputFile.hasNext())
      {
         String city2 = inputFile.next();
         String distance = inputFile.next();
         
         mySaujanyaHM.add(city2, distance);
      }
      
      //test/demonstrate all functionality/methods for it
      System.out.println("Testing all functionality/methods for MySaujanyaHM...");
      mySaujanyaHM.print();
      
      //read in the City1 and City2 part from the file
      while(inputFile.hasNext())
      {
         String city1 = inputFile.next();
         String city2 = inputFile.next();
         
         mySaujanyaUG.add(city1, city2);
      }
      
      //test/demonstrate all functionality/methods for it
      System.out.println("Testing all functionality/methods for MySaujanyaUG...");
      mySaujanyaUG.print();
      
      //close the file
      inputFile.close();
   }
}