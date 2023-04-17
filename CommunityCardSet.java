package proj4;

import java.util.ArrayList;

public class CommunityCardSet
{
    ArrayList<Card> contents;

    public CommunityCardSet(ArrayList<Card> cardList)
    {
        contents = (ArrayList<Card>) cardList.clone();
    }

    /**
     * Adds a card to the Community Card Set
     * @param card the card that will be added
     */
    public void addCard(Card card)
    {
        int ccmLength = 5;
        if (contents.size() < ccmLength)
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
     * Checks if a Community Card Set is equal to another Community Card Set
     * @param other the other Community Card Set
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
            if (!((CommunityCardSet) other).contents.contains(this.contents.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}
