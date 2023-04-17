package proj4;
import java.util.ArrayList;
import java.util.Comparator;


public class PokerHand
{
    private static final int cardsPerHand = 5;
    ArrayList<Card> contents;

    /**
     * Creates a poker hand
     * @param cardList a list of cards that will go in the poker hand
     */
    public PokerHand(ArrayList<Card> cardList)
    {
        contents = (ArrayList<Card>) cardList.clone();
    }

    /**
     * Adds a card to the poker hand
     * @param card the card that will be added
     */
    public void addCard(Card card)
    {
        if (contents.size() < cardsPerHand)
        {
            contents.add(card);
        }
    }

    /**
     * Gets a card at a given index
     * @param cardIndex the index of the card to get
     * @return a card
     */
    public Card getIthCard(int cardIndex)
    {
        return contents.get(cardIndex);
    }

    public String toString()
    {
        return contents.toString();
    }

    /**
     * Checks if the given poker hand is a flush
     * @return True if it is, False if it isn't
     */
    public boolean isFlush()
    {
        for (int i = 0; i < cardsPerHand; i++)
        {
            if (!this.contents.get(0).getSuit().equals(this.contents.get(i).getSuit()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given poker hand is a two pair
     * @return True if it is, False if it isn't
     */
    public boolean isTwoPair()
    {
        int numOfPairs = 0;
        for (int i = 0; i < cardsPerHand; i++)
        {
            for (int j = i+1; j < cardsPerHand; j++)
            {
                if (this.contents.get(i).getRank() == this.contents.get(j).getRank())
                {
                    numOfPairs += 1;
                }
            }
        }
        if (numOfPairs == 2 || numOfPairs == 4 || numOfPairs == 6)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks if the given poker hand is a pair
     * @return True if it is, False if it isn't
     */
    public boolean isPair()
    {
        int numOfPairs = 0;
        for (int i = 0; i < cardsPerHand; i++)
        {
            for (int j = i+1; j < cardsPerHand; j++)
            {
                if (this.contents.get(i).getRank() == this.contents.get(j).getRank())
                {
                    numOfPairs += 1;
                }
            }
        }
        if (numOfPairs == 1 || numOfPairs == 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks if the given poker hand is a high card
     * @return True if it is, False if it isn't
     */
    public boolean isHighCard()
    {
        if (this.isFlush() || this.isTwoPair() || this.isPair())
        {
            return false;
        }
        return true;
    }

    /**
     * Compares a flush hand to another hand
     * @param other the other hand
     * @return -1 if the starting hand is lower, 1 if it is higher, 0 if they are equal
     */
    private int compareFlush(PokerHand other)
    {
        if (other.isFlush())
        {
            ArrayList<Integer> otherRanks = other.sortRank();
            ArrayList<Integer> thisRanks = this.sortRank();
            for (int i = 0; i < cardsPerHand; i++)
            {
                if (!thisRanks.get(i).equals(otherRanks.get(i)))
                {
                    if (thisRanks.get(i) < otherRanks.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            return 0;
        }
        else
        {
            return 1;
        }
    }

    /**
     * Compares a two pair hand to another hand
     * @param other the other hand
     * @return -1 if the starting hand is lower, 1 if it is higher, 0 if they are equal
     */
    private int compareTwoPair(PokerHand other)
    {
        if (other.isFlush())
        {
            return -1;
        }
        else if (other.isTwoPair())
        {
            ArrayList<Integer> thisPairs = this.getPairs();
            ArrayList<Integer> otherPairs = other.getPairs();
            ArrayList<Integer> otherRanks = other.getOtherCards();
            ArrayList<Integer> thisRanks = this.getOtherCards();
            for (int i = 0; i < thisPairs.size(); i++)
            {
                if (!thisPairs.get(i).equals(otherPairs.get(i)))
                {
                    if (thisPairs.get(i) < otherPairs.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            for (int i = 0; i < thisRanks.size(); i++)
            {
                if (!thisRanks.get(i).equals(otherRanks.get(i)))
                {
                    if (thisRanks.get(i) < otherRanks.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            return 0;
        }
        else
        {
           return 1;
        }
    }

    /**
     * Compares a pair hand to another hand
     * @param other the other hand
     * @return -1 if the starting hand is lower, 1 if it is higher, 0 if they are equal
     */
    private int comparePair(PokerHand other)
    {
        if (other.isPair())
        {
            ArrayList<Integer> thisPairs = this.getPairs();
            ArrayList<Integer> otherPairs = other.getPairs();
            ArrayList<Integer> otherRanks = other.getOtherCards();
            ArrayList<Integer> thisRanks = this.getOtherCards();
            for (int i = 0; i < thisPairs.size(); i++)
            {
                if (!thisPairs.get(i).equals(otherPairs.get(i)))
                {
                    if (thisPairs.get(i) < otherPairs.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            for (int i = 0; i < thisRanks.size(); i++)
            {
                if (!thisRanks.get(i).equals(otherRanks.get(i)))
                {
                    if (thisRanks.get(i) < otherRanks.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            return 0;
        }
        else if (other.isHighCard())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Compares a high card hand to another hand
     * @param other the other hand
     * @return -1 if the starting hand is lower, 1 if it is higher, 0 if they are equal
     */
    private int compareHighCard(PokerHand other)
    {
        if (other.isHighCard())
        {
            ArrayList<Integer> otherRanks = other.sortRank();
            ArrayList<Integer> thisRanks = this.sortRank();
            for (int i = 0; i < cardsPerHand; i++)
            {
                if (!thisRanks.get(i).equals(otherRanks.get(i)))
                {
                    if (thisRanks.get(i) < otherRanks.get(i))
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            return 0;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Sorts the ranks of the cards in a hand
     * @return the sorted ranks, highest to lowest
     */
    private ArrayList sortRank()
    {
        ArrayList<Integer> rank = new ArrayList<>();
        for (int i = 0; i < cardsPerHand; i++)
        {
            rank.add(this.contents.get(i).getRank());
            rank.sort(Comparator.reverseOrder());
        }
        return rank;
    }

    /**
     * Gets the values of any pairs in a hand
     * @return the ranks of the pairs
     */
    private ArrayList getPairs()
    {
        ArrayList<Integer> thisRanks = this.sortRank();
        ArrayList<Integer> pairs = new ArrayList<>();
        int i = 0;
        while (i < cardsPerHand -1)
        {
            if (thisRanks.get(i).equals(thisRanks.get(i + 1)))
            {
                pairs.add(thisRanks.get(i));
                i++;
            }
            i++;
        }
        return pairs;
    }

    /**
     * Gets the values of the non-pair cards in a hand
     * @return the ranks of the non-pair cards
     */
    private ArrayList getOtherCards()
    {
        ArrayList<Integer> thisRanks = this.sortRank();
        ArrayList<Integer> thisPairs = this.getPairs();
        for (int i = 0; i < thisPairs.size(); i++)
        {
            thisRanks.remove(thisPairs.get(i));
        }
        return thisRanks;
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    public int compareTo(PokerHand other)
    {
        if (this.isFlush())
        {
            return this.compareFlush(other);
        }
        else if (this.isTwoPair())
        {
            return this.compareTwoPair(other);
        }
        else if (this.isPair())
        {
            return this.comparePair(other);
        }
        else
        {
           return this.compareHighCard(other);
        }
    }

    /**
     * Checks if a PokerHand is equal to another PokerHand
     * @param other the other Pokerhand
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
            if (!((PokerHand) other).contents.contains(this.contents.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}