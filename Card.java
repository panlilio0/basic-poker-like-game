package proj4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card
{
    private final int rank;
    private final String suit;

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank whole cards (2-10) can either be spelled
     *              out like "two" or numeric like "2". Case
     *              insensitive.
     * @param suit "Spades", "Hearts", "Clubs", or "Diamonds"
     */
    public Card(String rank, String suit)
    {
        String[] rankArray = new String[]
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                        "jack", "queen", "king", "ace"};
        List<String> rankArraylist = new ArrayList<>(Arrays.asList(rankArray));
        this.suit = suit;
        if(rank.length() < 3)
        {
            this.rank = Integer.parseInt(rank);
        }
        else
        {
            this.rank = rankArraylist.indexOf(rank.toLowerCase());
        }
    }

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank The rank of the card, which must be between
     *              2 and 14, inclusive.
     * @param suit The suit of the card, which must be
     *              0=SPADES, 1=HEARTS, 2=CLUBS, or 3=DIAMONDS
     */
    public Card(int rank, int suit)
    {
        String[] suitArray = new String[]{"Spades", "Hearts", "Clubs", "Diamonds"};
        List<String> suitArraylist = new ArrayList<>(Arrays.asList(suitArray));
        this.rank = rank;
        this.suit = suitArraylist.get(suit);
    }

    /**
     * Returns the rank of the card
     * @return the rank
     */
    public int getRank()
    {
        return this.rank;
    }

    /**
     * Returns the suit of the card
     * @return the suit
     */
    public String getSuit()
    {
        return this.suit;
    }

    public String toString()
    {
        int cardsWithNoCoolName = 10;
        String[] cardNames = new String[]{"Jack", "Queen", "King", "Ace"};
        if(this.rank <= cardsWithNoCoolName)
        {
            return this.rank+" of "+this.suit;
        }
        else
        {
            return cardNames[this.rank-(cardsWithNoCoolName +1)]+" of "+this.suit;
        }
    }

    /**
     * Checks if a card is equal to another card
     * @param other the other card
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        } else if (other == null)
        {
           return false;
        } else if (other.getClass() != this.getClass())
        {
            return false;
        }

        Card otherCard = (Card) other;
        return otherCard.getSuit().equals(this.getSuit())
                && otherCard.getRank() == this.getRank();
    }
}