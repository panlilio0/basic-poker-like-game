package proj4;
/*
    @author: Hades Panlilio
    03/09/2023
    CSC 120
    I affirm that I have carried out my academic endeavors with full academic honesty
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
       Deck deck = new Deck();
       deck.shuffle();
       int playerPoints = 0;
       int gameRounds = 11;
       int CARDS_PER_HAND = 5;
       int CARDS_PER_STUD_HAND = 2;
       Scanner input = new Scanner(System.in);

        System.out.println("Welcome to a pretty mediocre game!\n" +
                "You're going to choose which of two poker hands you think is higher.\n" +
                "If you think the first hand is higher, type 1\n" +
                "If you think the second hand is higher, type -1\n" +
                "If you think that the hands are of equal value, type 0\n" +
                "Have fun!\n");

        ArrayList<Card> ccmArrayList = new ArrayList<>();
        for ( int i = 0; i < CARDS_PER_HAND; i++)
        {
            ccmArrayList.add(deck.deal());
        }
        CommunityCardSet ccm = new CommunityCardSet(ccmArrayList);

        while (playerPoints < gameRounds)
        {
           ArrayList<Card> sph1ArrayList = new ArrayList<>();
           for (int j = 0; j < CARDS_PER_STUD_HAND; j++)
           {
               sph1ArrayList.add(deck.deal());
           }
           StudPokerHand sph1 = new StudPokerHand(ccm, sph1ArrayList);

           ArrayList<Card> sph2ArrayList = new ArrayList<>();
           for (int k = 0; k < CARDS_PER_STUD_HAND; k++)
           {
               sph2ArrayList.add(deck.deal());
           }
           StudPokerHand sph2 = new StudPokerHand(ccm, sph2ArrayList);

           int result = sph1.compareTo(sph2);

           System.out.println("Community Cards:\n" + ccm);
           System.out.print("\nChoose the higher hand!\n" + "Hand 1: " + sph1.toStringSph() + "\nHand 2: " + sph2.toStringSph());
           System.out.println("\nCHEAT: " + result);
           System.out.println("\n\nEnter 1, -1, or 0: ");
           String playerChoiceString = input.nextLine();
           int playerChoice = Integer.parseInt(playerChoiceString);

           if (playerChoice == result)
           {
               playerPoints++;
               System.out.println("CORRECT! You got a point.\n");
               if (playerPoints == gameRounds)
               {
                   System.out.println("YOU WIN! Good job!\nYou had " + playerPoints + " points");
               }
           }
           else
           {
               System.out.println("WRONG! Game Over...\nYou had " + playerPoints + " points");
               playerPoints += gameRounds;
           }
       }
    }
}
