/*
 * Copyright (c) 2020, Macweese, https//github.com/macweese
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.IOException;
import java.util.HashSet;

/**
 * The game parameters, operations and other conditions
 */
public class Game
{

    static HashSet<Cards> Deck = new HashSet<>();
    static Cards card;

    static public int computerScore;
    static public int playerScore;
    static private int cardsDrawn;
    static boolean gameRunning;
    static boolean playerTurn;

    /**
     * Run on instantiation of the game
     */
    public static void launch()
    {
        gameRunning = true;
        playerTurn = true;
        GameWindow.newGame();
    }

    /**
     * Sets the boolean value indicating who's turn it is
     */
    public static void toggleTurn()
    {
        playerTurn = !playerTurn;
    }

    /**
     * Generates a card
     *
     * @return  Returns a new original card
     */
    public static Cards generateNextCard()
    {
        card = Cards.getRandomCard();

        if (cardWasUsed(card))
        {
            if (deckDepleted())
            {
                gameState();
            }
            generateNextCard();
        }

        Deck.add(card);
        return card;
    }

    /**
     * Checks if there are no more original cards left in the deck
     *
     * @return  Returns true if there are no more cards
     *          Returns false if there are still cards available
     */
    public static boolean deckDepleted()
    {
        return (Deck.size() == 52);
    }

    /**
     * Get the number of original cards left in the deck
     *
     * @return Returns the number of cards left
     */
    public static int cardsLeft()
    {
        return (52 - Deck.size());
    }

    /**
     * Checks if the passed card is a duplicate
     *
     * @param card The card to control
     * @return  Returns true if duplicate
     *          Returns false if original
     */
    public static boolean cardWasUsed(Cards card)
    {
        return Deck.contains(card);
    }

    /**
     * Get a card that was drawn from the deck
     *
     * @return Returns the most recent card
     */
    public static Cards getCard()
    {
        return card;
    }

    /**
     * The value of the recently drawn card
     *
     * Ace counts as either 1 or 14;
     * if the score is 7, Ace will count as 14, landing a victory
     * if the score is 20, Ace will count as 1, landing a victory
     * otherwise it will randomise the value to either 1 or 14
     *
     * @return  Returns the value of the most recent card
     */
    public static int getRecentCardValue(Cards card)
    {
        if (card != null)
        {
            if (card.value == 1)
            {
                if ((!playerTurn && (computerScore == 7)) || (!playerTurn && (computerScore == 20)))
                {
                    return (computerScore == 7 ? 14 : 1);
                }
                else if ((playerTurn && (playerScore == 7)) || (playerTurn && (playerScore == 20)))
                {
                    return (playerScore == 7 ? 14 : 1);
                }
                else
                    return ((int) (Math.random() * 101)) > 50 ? 1 : 14;
            }
            return card.value;
        }
        else
        {
            System.out.println("Something unexpected happened.\n" +
                    "Game.getCard() produced NPE");
            return 0;
        }
    }

    /**
     * Get the opponents score
     *
     * @return Returns the computer (BOT) score
     */
    public static int getComputerScore()
    {
        return computerScore;
    }

    /**
     * Set the opponents score
     */
    public static void setComputerScore(int i)
    {
        computerScore = computerScore + i;
    }

    /**
     * Get the players score
     *
     * @return Returns the players score
     */
    public static int getPlayerScore()
    {
        return playerScore;
    }

    /**
     * Set the players score
     */
    public static void setPlayerScore(int i)
    {
        playerScore = playerScore + i;
    }

    /**
     * Sets the amount of drawn cards to 0
     */
    public static void resetCardsDrawn()
    {
        cardsDrawn = 0;
    }

    /**
     * Adds to the amount of drawn cards
     */
    public static void increaseCardsDrawn()
    {
        cardsDrawn = cardsDrawn + 1;
    }

    /**
     * Gets the amount of drawn cards
     *
     * @return Returns an int
     */
    public static int getCardsDrawn()
    {
        return cardsDrawn;
    }

    /*
     * Checks the state of the game
     */
    public static void gameState()
    {
        if (gameRunning)
        {
            if (playerScore == 21)
            {
                gameRunning = false;
                MessageHandler.victoryMessage();
            }
            if (playerScore > 21)
            {
                gameRunning = false;
                MessageHandler.defeatMessage(2);
            }
            if (computerScore > 21)
            {
                gameRunning = false;
                MessageHandler.victoryMessage();
            }
            if (computerScore == 21)
            {
                gameRunning = false;
                MessageHandler.defeatMessage(2);
            }
            if ((deckDepleted() || cardsLeft() == 0) && (21 - playerScore) > (21 - computerScore))
            {
                gameRunning = false;
                MessageHandler.defeatMessage(1);
            }
            if ((deckDepleted() || cardsLeft() == 0) && (21 - playerScore) < (21 - computerScore))
            {
                gameRunning = false;
                MessageHandler.victoryMessage();
            }
            if ((deckDepleted() || cardsLeft() == 0) && (21 - playerScore) == (21 - computerScore))
            {
                gameRunning = false;
                MessageHandler.defeatMessage(3);
            }
        }
    }

    /**
     * Checks if the games defeat/victory conditions have been met
     *
     * @return  Returns true if the game is still in progress
     *          Returns false if the game is over
     */
    public static boolean getGameState()
    {
        return gameRunning;
    }

    /**
     * Resets everything to instantiation state
     */
    public static void reset()
    {
        card = null;
        Deck.clear();
        computerScore = 0;
        playerScore = 0;
        cardsDrawn = 0;
        playerTurn = true;
        gameRunning = true;
    }
}
