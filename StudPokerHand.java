package proj4;

import java.util.ArrayList;

public class StudPokerHand
{
    CommunityCardSet communityCardSet;
    ArrayList<Card> studCards;
    ArrayList<Card> contents = new ArrayList<>();

    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList)
    {
        this.communityCardSet = cc;
        this.studCards = cardList;
        this.contents.addAll(cardList);
        this.contents.addAll(cc.contents);
    }

    /**
     * Adds a card to the stud poker hand
     * @param card the card that will be added
     */
    public void addCard(Card card)
    {
        int studCardsLength = 2;
        if (studCards.size() < studCardsLength)
        {
            studCards.add(card);
        }
    }

    /**
     * Gets a card at a given index
     * @param cardIndex the index of the card to get
     * @return a card
     */
    public Card getIthCard(int cardIndex)
    {
        return studCards.get(cardIndex);
    }

    public String toString()
    {
        return contents.toString();
    }

    public String toStringSph()
    {
        return studCards.toString();
    }

    /**
     * Gets all possible combos given a list and the combo length
     * @param chooseFrom a list to make combos from
     * @param targetLen the length of the combos
     * @return a list of all the combos
     */
    private ArrayList<ArrayList<Card>> getCombos(ArrayList<Card> chooseFrom, int targetLen)
    {
        ArrayList<ArrayList<Card>> allFiveCardHands = new ArrayList<>();
        if (targetLen == 1)
        {
            for (int i = 0; i < chooseFrom.size(); i++)
            {
                ArrayList<Card> combo = new ArrayList<>();
                combo.add(chooseFrom.get(i));
                allFiveCardHands.add(combo);
            }
            return allFiveCardHands;
        }
        if (chooseFrom.size() == targetLen)
        {
            ArrayList<Card> combo = new ArrayList<>(chooseFrom);
            allFiveCardHands.add(combo);
            return allFiveCardHands;
        }
        ArrayList<Card> rest = new ArrayList<>(chooseFrom.subList(1, chooseFrom.size()));
        Card firstElement = chooseFrom.get(0);
        ArrayList<ArrayList<Card>> combosWithFirst = new ArrayList<>(getCombos(rest, targetLen-1));
        combosWithFirst = combosWithFirst(combosWithFirst, firstElement);

        ArrayList<ArrayList<Card>> combosWithoutFirst = getCombos(rest, targetLen);
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>(combosWithFirst);
        allCombos.addAll(combosWithoutFirst);
        return allCombos;
    }

    /**
     * Helper to make the combos with the first element by appending the needed element
     * @param combosWithFirst the combos that need help
     * @param firstElement the element to append
     * @return a list of the completed combos
     */
    private ArrayList<ArrayList<Card>> combosWithFirst(ArrayList<ArrayList<Card>>combosWithFirst, Card firstElement)
    {
        for (int i = 0; i < combosWithFirst.size(); i++)
        {
            combosWithFirst.get(i).add(firstElement);
        }
        return combosWithFirst;
    }

    /**
     * Gets the best 5 card hand from a list of hands
     * @return the best hand
     */
    public PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = makePokerHands();
        PokerHand bestSoFar = hands.get(0);

        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }

    /**
     * Helper to make combos into PokerHands
     * @return a list of the PokerHands
     */
    private ArrayList<PokerHand> makePokerHands()
    {
        ArrayList<ArrayList<Card>> hands = getCombos(this.contents, 5);
        ArrayList<PokerHand> pokerHands = new ArrayList<>();
        for(int i = 0; i < hands.size(); i++)
        {
            PokerHand pokerHand = new PokerHand(hands.get(i));
            pokerHands.add(pokerHand);
        }
        return pokerHands;
    }

    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */
    public int compareTo(StudPokerHand other)
    {
        PokerHand thisBestHand = this.getBestFiveCardHand();
        PokerHand otherBestHand = other.getBestFiveCardHand();
        return thisBestHand.compareTo(otherBestHand);
    }

    /**
     * Checks if a Stud Poker Hand is equal to another Stud Poker Hand
     * @param other the other Stud Poker Hand
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
            if (!((StudPokerHand) other).contents.contains(this.contents.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}
