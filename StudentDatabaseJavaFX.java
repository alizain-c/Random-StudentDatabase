/*
Ali Zain Charolia
11/26/2019

This program is a JavaFX application program which is useful in a school environment..
It has five buttons - Help, SetParam, FillArray, DisplayResults and Quit with each performing a specific task
It asks the user to enter number of students and number of quizzes they took (upto a limit) 
then it fills the arrays defined in the program and then displays random grades result, average, highest, lowest and median.
After the user has received the proper expected output, he/she can press the button "Quit" and exit the program
*/

//importing all javafx packages
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.collections.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//importing all java classes necessary in this program
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class StudentDatabaseJavaFX extends Application              //Program Name
  {
  //Fields
  private Label label = new Label();                              //Label class field
  private Pane layout = new Pane();                               //Pane class field
  int students = 0;
  int quiz = 0;
  double avg;
  GridPane grid = new GridPane();                                    //creating the grid object
  
  //ArrayList Fields to be used throughout the class
  ArrayList<Integer> studentIDs = new ArrayList<Integer>(250);       //ArrayList studentIDs
  ArrayList<Integer> quiz1Grades = new ArrayList<Integer>(250);      //ArrayList quizGrades
  ArrayList<Integer> quiz2Grades = new ArrayList<Integer>(250);      //ArrayList quizGrades
  ArrayList<Integer> quiz3Grades = new ArrayList<Integer>(250);      //ArrayList quizGrades
  ArrayList<Integer> quiz4Grades = new ArrayList<Integer>(250);      //ArrayList quizGrades
  ArrayList<Integer> quiz5Grades = new ArrayList<Integer>(250);      //ArrayList quizGrades
  ArrayList<Integer> lowest = new ArrayList<Integer>(5);             //ArrayList for lowest
  ArrayList<Integer> highest = new ArrayList<Integer>(5);            //ArrayList for highest
  ArrayList<Double> Average = new ArrayList<Double>(5);              //ArrayList for Average
  ArrayList<Integer> Median = new ArrayList<Integer>(5);             //ArrayList for Median

  GridPane dis = new GridPane();                                   //creating the grid object
  
  @Override          //Override
  public void start(Stage primaryStage) throws Exception          //void method start (passing object of Stage class as an argument)
     {
    primaryStage.setTitle("Final Project (COSC 1436) - AliZain Charolia");                       //Setting the title of the stage

    Label label = new Label("");                                  //Label class object
    Button button1 = new Button("Help");                          //creating a button object "button1" for Help 
    Button button2 = new Button("Set Parameters");                //creating a button object "button2" for Set Parameters 
    Button button3 = new Button("Fill Array");                    //creating a button object "button3" for Fill Array
    Button button4 = new Button("Display Results");               //creating a button object "button4" for Display Results 
    Button button5 = new Button("Quit");                          //creating a button object "button5" for Quit 
    
    //setting button1 dimensions and setting it's clickAction
    button1.setPrefWidth(160);                       
    button1.setPrefHeight(150);
    button1.setStyle("-fx-background-color: #ADD8E6");            //setting color
    button1.setStyle("-fx-border-color: #9b1c31; -fx-border-width: 3px;");
    button1.setOnAction(event ->
      { 
        //Creating label , pane, setting title, 
        Label t1 = new Label(" Read this carefully to understand how to use this program.\n This help button guides you with the details of this program.\n\n SET PARAM : This button guides you with entering number of students and number of quizzes taken.\n\n FILL ARRAY : This button helps to fill and store the data in the systems.\n\n DISPLAY : This button shows your the results for the class with number of students and their grades in each quiz taken\n\t\t\tand also highest, lowest, avg and median \n\n QUIT : Finally, this button helps you to exit the program.");
        StackPane bp = new StackPane();
	    bp.setAlignment(t1, Pos.CENTER);
        Stage stage1 = new Stage();
        stage1.setTitle("Help");
        stage1.setScene(new Scene(t1,700,190));
		layout.getChildren().add(bp);
        stage1.show(); 
      });
    
    //setting button2 dimensions/position and setting it's clickAction
    button2.setPrefWidth(160);
    button2.setPrefHeight(150);
    button2.setLayoutX(160);
    button2.setLayoutY(0);
    button2.setStyle("-fx-background-color: #00FFFF");             //setting color
    button2.setStyle("-fx-border-color: #0000A0; -fx-border-width: 3px;");
    button2.setOnAction(event -> 
      {
         {
            //Setting the stage and entering all labels and textfields
            Stage stage = new Stage();
            
            Label label1 = new Label("Number Of Students: ");
            Label label2 = new Label("Number Of Quizzes: ");
            
            TextField tf = new TextField();
            TextField tf2 = new TextField();
            
            Button ok = new Button("OK");             //button ok
            ok.setStyle("-fx-border-color: #9b1c31; -fx-border-width: 3px;");
            ok.setOnAction(event2 ->                  //setting the action
            {   
               //Parsing the string to int
               students = Integer.parseInt(tf.getText());
               quiz =Integer.parseInt(tf2.getText());
               if(students > 0 && students < 51 && quiz > 0 && quiz < 6)       //displaying restriction window
               {
                  
                  stage.close();                       //closing the stage
               }
               else
               {
                  //Label warning to warn user to add in limits
                  Label warning = new Label("Number Of Students must be between 1 and 50.\nNumber Of Quizzes between 1 and 5."); 
                  grid.add(warning,1,2);
               }
            });
            
            //adding everything in grids. 
            grid.add(label1, 0,0);
            grid.add(label2, 0,1);
            grid.add(tf, 1,0);
            grid.add(tf2, 1,1);
            grid.add(ok, 0,2);
             
            //creating a window object of class Scene
            Scene window = new Scene(grid,500,100);
            stage.setTitle("Set Parameters");
            stage.setScene(window);
            stage.show();
           }
      });
    
    //setting button3 dimensions/position and setting it's clickAction
    button3.setPrefWidth(160);
    button3.setPrefHeight(150);
    button3.setLayoutX(320);
    button3.setLayoutY(0);
    button3.setStyle("-fx-background-color: #ADD8E6");                 //setting color
    button3.setStyle("-fx-border-color: #9b1c31; -fx-border-width: 3px;");
    button3.setOnAction(event ->                //setting button 3 action 
      {     
            //creating stage 
            Stage stage = new Stage();
            
            //calling the fillAllData() method which fills all the necessary arrays
            fillAllData();
            Label label3 = new Label("Array has been filled.");
            Scene window2 = new Scene(label3,150,50);
            stage.setTitle("FillArray");
            stage.setScene(window2);
            stage.show();      
      });
    
    //setting button4 dimensions/position and setting it's clickAction
    button4.setPrefWidth(160);
    button4.setPrefHeight(150);
    button4.setLayoutX(480);
    button4.setLayoutY(0);
    button4.setStyle("-fx-background-color: #00FFFF");                //setting color
    button4.setStyle("-fx-border-color: #0000A0; -fx-border-width: 3px;");
    button4.setOnAction(event ->                                      //setting button 4 action
      {
              display();                                              //calling the display() method which displays class IDs and quiz's grades
              displayHighestLowestAvgMed();                           //calling the displayHighestLowestAvgMed() method which displays class' Highest, Lowest, Average and Median
      });
    
    //setting button5 dimensions/position and setting it's clickAction
    button5.setPrefWidth(160);
    button5.setPrefHeight(150);
    button5.setLayoutX(640);
    button5.setLayoutY(0);
    button5.setStyle("-fx-background-color: #ADD8E6");                //setting color
    button5.setStyle("-fx-border-color: #9b1c31; -fx-border-width: 3px;");

    button5.setOnAction(event ->                                      //setting button 5 action
      {
           System.exit(0);
      });
    
    Pane layout = new Pane();                                     //Pane class object
    layout.getChildren().add(button1);                            //adding button1 to the layout
    layout.getChildren().add(button2);                            //adding button2 to the layout
    layout.getChildren().add(button3);                            //adding button3 to the layout
    layout.getChildren().add(button4);                            //adding button4 to the layout
    layout.getChildren().add(button5);                            //adding button5 to the layout
    
    //creating a scene object and adding layout and initializing it's dimensions | also setting the scene
    Scene scene = new Scene(layout, 800, 150);         
    primaryStage.setScene(scene);       
    
    primaryStage.show();                                          //showing the primaryStage object of class Stage in the output
    }
    
    public static void main(String[] args)                        //main method
    {
        Application.launch(args);                                 //launching the arguments
    }
      
   public void fillAllData()                                      //Method fillAllData()
   {
      Random rand = new Random();                                 //Random object rand
      int ID = 75678;      
      
      //Loop to add ID numbers in the studentIDs array
      for(int x = 0; x < students; x++)
      {
         studentIDs.add(75678 + x);
      }
      
      //Loop to add quiz1 grades
      for(int x = 0; x < students; x++)
      {
         quiz1Grades.add(rand.nextInt(101));
      }
      
      if(quiz > 1)
      {
         //Loop to add grades for 2 quizzes
         for(int x = 0; x < students; x++)
         {
            quiz2Grades.add(rand.nextInt(101));
         }
         
         if(quiz > 2)
         {
            //Loop to add grades for 3 quizzes
            for(int x = 0; x < students; x++)
            {
               quiz3Grades.add(rand.nextInt(101));
            }
            if(quiz > 3)
            {
               //Loop to add grades for 4 quizzes
               for(int x = 0; x < students; x++)
               {
                  quiz4Grades.add(rand.nextInt(101));
               }
               if(quiz > 4)
               {
                  //Loop to add grades for 5 quizzes
                  for(int x = 0; x < students; x++)
                  {
                     quiz5Grades.add(rand.nextInt(101));
                  }
               }
            }
         }
      }
   }
   
   public void display()                                           //method display()
   {  
      //Creating stage
      Stage stage = new Stage();
      dis.setHgap(55);
      dis.setVgap(30);
      Scene window = new Scene(dis);
      Label id = new Label("Student ID");
      
      dis.add(id,0,0);                   //adding contents to grid "dis"
      
      //Loop to print out First row QUiz1,Quiz2...
      for(int x = 0; x < quiz; x++)
      {
         Label s = new Label("Quiz " + Integer.toString(x + 1)+ "\nGrades");
         dis.add(s,x + 1,0);
      }
      
      //Loop to print out studentsID
      for(int x = 0; x < students; x++)
      {
         Label s = new Label(Integer.toString(studentIDs.get(x)));        //parsing to String
         dis.add(s,0,x + 1);
      }
      
      switch(quiz)                    //switch-case
      {
         case 1:
            //Loop to add quiz1 grades
            for(int x = 0; x < students; x++)
            {
               Label s = new Label(Integer.toString(quiz1Grades.get(x)));
               dis.add(s,1,x + 1);
            }
            break;
            
         case 2:
            //Loop to add quiz1 , quiz2 grades
            for(int x = 0; x < students; x++)
            {
               Label s = new Label(Integer.toString(quiz1Grades.get(x)));
               dis.add(s,1,x + 1);
               Label t = new Label(Integer.toString(quiz2Grades.get(x)));
               dis.add(t,2,x + 1);
            }
            break;
            
         case 3:
            //Loop to add quiz1 , quiz2 , quiz3  grades
            for(int x = 0; x < students; x++)
            {
               Label s = new Label(Integer.toString(quiz1Grades.get(x)));
               dis.add(s,1,x + 1);
               Label t = new Label(Integer.toString(quiz2Grades.get(x)));
               dis.add(t,2,x + 1);
               Label u = new Label(Integer.toString(quiz3Grades.get(x)));
               dis.add(u,3,x + 1);
            }
            break;
            
         case 4:
            //Loop to add quiz1 , quiz2 , quiz3 , quiz4 grades
            for(int x = 0; x < students; x++)
            {
               Label s = new Label(Integer.toString(quiz1Grades.get(x)));
               dis.add(s,1,x + 1);
               Label t = new Label(Integer.toString(quiz2Grades.get(x)));
               dis.add(t,2,x + 1);
               Label u = new Label(Integer.toString(quiz3Grades.get(x)));
               dis.add(u,3,x + 1);
               Label v = new Label(Integer.toString(quiz4Grades.get(x)));
               dis.add(v,4,x + 1);
            }
            break;
            //Loop to add quiz1 , quiz2 , quiz3 , quiz4 , quiz5 grades
         case 5:
            for(int x = 0; x < students; x++)
            {
               Label s = new Label(Integer.toString(quiz1Grades.get(x)));
               dis.add(s,1,x + 1);
               Label t = new Label(Integer.toString(quiz2Grades.get(x)));
               dis.add(t,2,x + 1);
               Label u = new Label(Integer.toString(quiz3Grades.get(x)));
               dis.add(u,3,x + 1);
               Label v = new Label(Integer.toString(quiz4Grades.get(x)));
               dis.add(v,4,x + 1);
               Label w = new Label(Integer.toString(quiz5Grades.get(x)));
               dis.add(w,5,x + 1);
            }
            break;
      }
      //setting the stage title and showing it
      stage.setTitle("Results");
      stage.setScene(window);
      stage.show();
   }

   public void displayHighestLowestAvgMed()             //method displayHighestLowestAvgMed()
   { 
      //setting a new stage
      Stage stageExtra = new Stage();
      GridPane extra = new GridPane(); 
      extra.setHgap(50);
      extra.setVgap(25);
      Scene wExtra = new Scene(extra);
      
      //sorting everything in ascending order so as to find the median accordingly (using the Collections class)
      Collections.sort(quiz1Grades);
      Collections.sort(quiz2Grades);
      Collections.sort(quiz3Grades);
      Collections.sort(quiz4Grades);
      Collections.sort(quiz5Grades);
      
      //Loop to print ou "Quiz1/Quiz2..."
      for(int x = 0; x < quiz; x++)
      {
         Label s = new Label("Quiz " + Integer.toString(x + 1));
         extra.add(s,x + 1,0);
      }
      
      Label low = new Label("Lowest");      //lowest label
      extra.add(low,0,1);
       
      //Adding all grades in the extra GridPane obj 
      //and checking for the number of quizzes user enetered
      Label s = new Label(Integer.toString(quiz1Grades.get(0)));         
      extra.add(s,1,1);
      if(quiz > 1)
      {
         Label t = new Label(Integer.toString(quiz2Grades.get(0)));
         extra.add(t,2,1);
         
         if(quiz > 2)
         {
            Label u = new Label(Integer.toString(quiz3Grades.get(0)));
            extra.add(u,3,1);
         
            if(quiz > 3)
            {
               Label v = new Label(Integer.toString(quiz4Grades.get(0)));
               extra.add(v,4,1);
               
               if(quiz > 4)
               {
                  Label w = new Label(Integer.toString(quiz5Grades.get(0)));
                  extra.add(w,5,1);
               }
            }
         }
      }
       
      Label high = new Label("Highest");         //Highest label
      extra.add(high,0,2); 
      
      //Adding all the quiz grades in to the extra GridPane obj
      //and checking for the number of quizzes user enetered
      Label p = new Label(Integer.toString(quiz1Grades.get(students - 1)));
      extra.add(p,1,2);
      if(quiz > 1)
      {
         Label t = new Label(Integer.toString(quiz2Grades.get(students - 1)));
         extra.add(t,2,2);
         
         if(quiz > 2)
         {
            Label u = new Label(Integer.toString(quiz3Grades.get(students - 1)));
            extra.add(u,3,2);
         
            if(quiz > 3)
            {
               Label v = new Label(Integer.toString(quiz4Grades.get(students - 1)));
               extra.add(v,4,2);
               
               if(quiz > 4)
               {
                  Label w = new Label(Integer.toString(quiz5Grades.get(students - 1)));
                  extra.add(w,5,2);
               }
            }
         }
      }
      
      Label average = new Label("Average");            //Average Label
      extra.add(average,0,3);
      
      correctAverage();                                //calling the correctAverage() method
      //For loop to print out the average according to set of quiz grades for a particular student
      for(int x = 0; x < quiz; x++)
      {
         String temp = String.format("%.2f",Average.get(x));
         Label z = new Label(temp);
         extra.add(z,x + 1,3);
      }
      
      //Label for median
      Label median = new Label("Median");
      extra.add(median,0,4);
      
      correctMedian();                             //calling the correctMedian() method
      //For loop to enter the medians 
      for(int x = 0; x < quiz; x++)
      {
         Label z = new Label(Integer.toString(Median.get(x)));
         extra.add(z,x + 1,4);
      }      
      //stageExtra's title set and show
      stageExtra.setTitle("Results");
      stageExtra.setScene(wExtra);
      stageExtra.show();
   }
   
   public void correctAverage()                    //method correctAverage()
   {
      //variables
      avg = 0;
      for(int x = 0; x < students; x++)
      {
         avg += quiz1Grades.get(x);
      }
      avg /= students;
      Average.add(avg);                            //adding the average to the average array
      
      //checking the number of quizzes and calculating the average accordingly
      if(quiz > 1)
      {
         avg = 0;
         for(int x = 0; x < students; x++)
         {
            avg += quiz2Grades.get(x);
         }
         avg /= students;
         Average.add(avg);
         
         if(quiz > 2)
         {
            avg = 0;
            for(int x = 0; x < students; x++)
            {
               avg += quiz3Grades.get(x);
            }
            avg /= students;
            Average.add(avg);
            
            if(quiz > 3)
            {
               avg = 0;
               for(int x = 0; x < students; x++)
               {
                  avg += quiz4Grades.get(x);
               }
               avg /= students;
               Average.add(avg);
               
               if(quiz > 4)
               {
                  avg = 0;
                  for(int x = 0; x < students; x++)
                  {
                     avg += quiz5Grades.get(x);
                  }
                  avg /= students;
                  Average.add(avg);
               }
            }
         }
      }
   }
   
   public void correctMedian()                                //method correctMedian()
   {
      //variables
      int x = students - 1;
      
      //while loop to check the quiz1Grades array's size and accordingly remove the first and last element of it using it's index
      while(quiz1Grades.size() > 2)
      {
         quiz1Grades.remove(x);
         quiz1Grades.remove(0);
         x -= 2;
      }
      if(quiz1Grades.size() == 1)
      {
         Median.add(quiz1Grades.get(0));
      }
      else if(quiz1Grades.size() == 2)
      {
         Median.add((quiz1Grades.get(0) + quiz1Grades.get(1)) / 2);
      }
      
      //re initializing value of x
      x = students - 1;
      
      //while loop to check the quiz2Grades array's size and accordingly remove the first and last element of it using it's index
      while(quiz2Grades.size() > 2)
      {
         quiz2Grades.remove(x);
         quiz2Grades.remove(0);
         x -= 2;
      }
      if(quiz2Grades.size() == 1)
      {
         Median.add(quiz2Grades.get(0));
      }
      else if(quiz1Grades.size() == 2)
      {
         Median.add((quiz2Grades.get(0) + quiz2Grades.get(1)) / 2);
      }
      
      //re initializing value of x
      x = students - 1;
      
      //while loop to check the quiz3Grades array's size and accordingly remove the first and last element of it using it's index
      while(quiz3Grades.size() > 2)
      {
         quiz3Grades.remove(x);
         quiz3Grades.remove(0);
         x -= 2;
      }
      if(quiz3Grades.size() == 1)
      {
         Median.add(quiz3Grades.get(0));
      }
      else if(quiz3Grades.size() == 2)
      {
         Median.add((quiz3Grades.get(0) + quiz3Grades.get(1)) / 2);
      }
      
      //re initializing value of x
      x = students - 1;
      
      //while loop to check the quiz4Grades array's size and accordingly remove the first and last element of it using it's index
      while(quiz4Grades.size() > 2)
      {
         quiz4Grades.remove(x);
         quiz4Grades.remove(0);
         x -= 2;
      }
      if(quiz4Grades.size() == 1)
      {
         Median.add(quiz4Grades.get(0));
      }
      else if(quiz4Grades.size() == 2)
      {
         Median.add((quiz4Grades.get(0) + quiz4Grades.get(1)) / 2);
      }

      //re initializing value of x
      x = students - 1;
      
      //while loop to check the quiz5Grades array's size and accordingly remove the first and last element of it using it's index
      while(quiz5Grades.size() > 2)
      {
         quiz5Grades.remove(x);
         quiz5Grades.remove(0);
         x -= 2;
      }
      if(quiz5Grades.size() == 1)
      {
         Median.add(quiz5Grades.get(0));
      }
      else if(quiz5Grades.size() == 2)
      {
         Median.add((quiz5Grades.get(0) + quiz5Grades.get(1)) / 2);
      }
   }
  }
 
//                       - - - THE END - - - 
