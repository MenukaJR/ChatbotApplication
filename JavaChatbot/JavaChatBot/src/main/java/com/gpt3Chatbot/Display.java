package com.gpt3Chatbot;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Display {

    private static void readFAQs() throws FileNotFoundException {
        //input FAQ file path
       File faqFile = new File("[Include PATH LEADING TO]FAQs\\FAQ.txt");//enter the path to FAQ file
        BufferedReader BR = new BufferedReader(new FileReader(faqFile));
        String new_Line = "";

        try {
            String line;
            while ((line = BR.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error\n " + e.toString());
        }
    }

    private static void readAnswers(String ansLetter) throws FileNotFoundException {// reads answer file. Need to specify answer letter when calling the method
        final String PATH = "[Include PATH LEADING TO] Ans_"+ansLetter+".txt";//enter the path to FAQ file

        File ansFile = new File(PATH);
        BufferedReader BR = new BufferedReader(new FileReader(ansFile));
        ArrayList<String> faq_Answer = new ArrayList<>();
        String[] paragraph;

        try {
            String line;
            while ((line = BR.readLine()) != null) {
                paragraph = new String[]{line.replace("/b", "\n")};
                for (String l :paragraph) {
                    faq_Answer.add(l);
                }
            }
        } catch (Exception e) {
            System.out.println("Error\n " + e.toString());
        }

        System.out.println(faq_Answer);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean AIFlag = false, flag = true;
        System.out.println("\nWelcome to Tim's Shoes");

        while (flag == true) {

          System.out.println("\nFrequently Asked Questions (Please select an option" +
                  " by typing corresponding lowercase letter and press enter)");
            System.out.println("press 'X' to exit");

            readFAQs();
            String option = sc.nextLine();

          try {

              switch (option) {//switch cases for different options
                  case "a":
                      readAnswers("a");
                      break;
                  case "b":
                      readAnswers("b");
                      break;
                  case "c":
                      readAnswers("c");
                      break;
                  case "d":
                      readAnswers("d");
                      break;

                  case "e":
                      AIFlag = true;

                      while (AIFlag == true) {//loop to run the chatBot sequentially
                          System.out.println("e. I want some help or tips about shoes (Virtual Assistant)");
                          ChatBot chatBot = new ChatBot();
                          System.out.println("Please enter your question, Press x to exit:");
                          String prompt = sc.nextLine();

                          if (prompt.equals("x")) {//exit this while loop
                              AIFlag = false;
                              break;
                          } else {
                              chatBot.VirtualAssistant(prompt);
                          }
                      }
                  case "x"://exit the main loop
                      flag = false;
                      break;
                  default:
                      System.out.println("Sorry, please enter the letter to the corresponding option and press enter.");
                      break;
              }
          } catch (FileNotFoundException e) {
              System.out.println("Error\n" + e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      }
    }

}
