package proj4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Deck
{
    ArrayList<Card> contents = new ArrayList<>();
    private int nextToDeal = 52;

    /**
     * Creates a deck object
     */
    public Deck()
    {
        int cardsPerSuit = 13;
        int suitsPerDeck = 4;

        for (int i = 2; i < cardsPerSuit+2; i++)
        {
            for (int j = 0; j < suitsPerDeck; j++)
            {
                Card c = new Card(i, j);
                contents.add(c);
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffle()
    {
        for (int i = 0; i < nextToDeal; i++)
        {
            Collections.swap(contents, i, ThreadLocalRandom.current().nextInt(nextToDeal));
        }
    }

    /**
     * Deals a card from the deck
     * @return the card that was dealt
     */
    public Card deal()
    {
        nextToDeal = nextToDeal-1;
        return contents.get(nextToDeal);
    }

    /**
     * Returns the current size of the deck
     * @return the siz of the deck
     */
    public int size()
    {
        return nextToDeal;
    }

    /**
     * Gathers the cards back so the deck has all the cards in it
     */
    public void gather()
    {
        nextToDeal = contents.size();
    }

    /**
     * Checks if the deck is empty
     * @return true if it is, false otherwise
     */
    public boolean isEmpty()
    {
        return nextToDeal == 0;
    }

    public String toString()
    {
        return contents.toString();
    }

    /**
     * Checks if a deck is equal to another deck
     * @param other the other deck
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

        for (int i = 0; i < this.contents.size(); i++)
        {
           if (this.contents.get(i).getRank() == ((Deck) other).contents.get(i).getRank()
                   && this.contents.get(i).getSuit().equals(((Deck) other).contents.get(i).getSuit()))
           {
               return true;
           }
        }
        return false;
    }
}