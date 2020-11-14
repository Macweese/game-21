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

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Game window
 */
public class GameWindow extends JDialog
{
    private JPanel MainPanel;
    private JPanel PlayerPanel;
    private JPanel ComputerPanel;
    private JPanel PlayerLabelPanel;
    private JPanel ComputerLabelPanel;
    private JPanel PlayerValuePanel;
    private JPanel ComputerValuePanel;
    private JPanel playerImagePanel;
    private JPanel computerImagePanel;
    private JButton ButtonSkip;
    private JButton ButtonDraw;
    private JLabel playerLabel;
    private JLabel computerLabel;
    private JLabel ScoreLabel;
    private JLabel recentActions;
    private JLabel statusLabel;
    private JLabel PlayerValue;
    private JLabel ComputerValue;
    private JLabel playerCardLabel;
    private JLabel computerCardLabel;
    private JLabel playerRecentCards;
    private JLabel computerRecentCards;

    private final String TITLE = "The Game of 21";
    private final String PLAYER_TURN = "Waiting for player...";
    private String recentAction = "";

    private final DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

    //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    MessageHandler messageHandler = new MessageHandler();

    static GameWindow gameWindow;

    /**
     * Game windows properties and functions
     */
    public GameWindow()
    {
        // Apply our custom window title, icon and contents
        setTitle(TITLE);
        setLocationRelativeTo(null);
        setIconImage(ImageHandler.getGameImage());

        playerCardLabel.setIcon(ImageHandler.getCardImage());
        computerCardLabel.setIcon(ImageHandler.getCardImage());
        statusLabel.setText(PLAYER_TURN);

        setContentPane(MainPanel);
        setModal(true);
        getRootPane().setDefaultButton(ButtonDraw);

        setLocationRelativeTo(MainPanel);

        /*
         * Call when button "Skip" is clicked
         */
        ButtonSkip.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    onSkip();
                } catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });

        /*
         * Call drawCard() when button "Draw next card" is clicked
         */
        ButtonDraw.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawCard();
                } catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });

        // call onExit() when X is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try {
                    onExit();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });

        // call onExit() on Esc key
        MainPanel.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    onExit();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // call drawCard() on Enter key
        MainPanel.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawCard();
                } catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // call onSkip() on Backspace key
        MainPanel.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    onSkip();
                } catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Generates a card of random value and suit
     *
     * @throws InterruptedException Thread sleep exception
     */
    private void drawCard() throws InterruptedException
    {
        Cards card = Game.generateNextCard();

        setPlayerCardLabel(card);

        Game.setPlayerScore(Game.getRecentCardValue(card));

        System.out.println(getTime() + "\tPLAYER\t\tDrew " + cardName() + ".\n\t\t\t\t\t\t" + Game.cardsLeft() + " cards available");
        System.out.println("Waiting for player...\n");

        setPlayerRecentCards(cardName());

        updateGameWindow((Game.cardsLeft()) + " Cards left");

        Game.increaseCardsDrawn();

        if (Game.getCardsDrawn() == 1)
            setRecentActions("Player drew " + Game.getCardsDrawn() + " card");
        else if (Game.getCardsDrawn() >= 2)
            setRecentActions("Player drew " + Game.getCardsDrawn() + " cards");

        Game.gameState();
    }

    /**
     * Actions to perform on skipped turn
     *
     * @throws InterruptedException Thread sleep exception
     */
    private void onSkip() throws InterruptedException
    {
        Game.gameState();

        System.out.println(getTime() + "\tPLAYER\t\tskipped turn.");
        System.out.println("Waiting...\n");

        updateGameWindow((Game.cardsLeft()) + " Cards left");

        if (Game.getCardsDrawn() == 0)
            setRecentActions("Player skipped turn");
        else setRecentActions("Player drew " + Game.getCardsDrawn() + " cards before skipping");

        Game.resetCardsDrawn();

        Game.toggleTurn();

        computerAction();
    }

    /**
     * Actions to perform on exit
     *
     * @throws IOException Throws IOException
     */
    private void onExit() throws IOException
    {
        messageHandler.exitMessage();
    }

    /**
     * Various NPC actions
     */
    public void computerAction()
    {
        int decision = (int) (Math.random() * 100);

        if(100 > decision && decision > 50 && Game.getGameState())
        {
            System.out.println(getTime() + "\tCOMPUTER\tskipped turn.");
            System.out.println("Waiting for player...\n");

            if (Game.getCardsDrawn() < 1)
                setRecentActions("Computer skipped turn.");

            updateGameWindow((Game.cardsLeft()) + " Cards left");

            Game.toggleTurn();
        }

        if(50 > decision && Game.getGameState())
        {
            Cards card = Game.generateNextCard();

            System.out.println(getTime() + "\tCOMPUTER\tDrew " + cardName() + ".\n\t\t\t\t\t\t" + Game.cardsLeft() + " cards available");

            setComputerCardLabel(card);

            Game.setComputerScore(Game.getRecentCardValue(card));

            setComputerRecentCards(cardName());

            updateGameWindow((Game.cardsLeft()) + " Cards left");

            Game.increaseCardsDrawn();

            if (Game.getCardsDrawn() == 1)
                setRecentActions("Computer drew " + Game.getCardsDrawn() + " card");
            else if (Game.getCardsDrawn() >= 2)
                setRecentActions("Computer drew " + Game.getCardsDrawn() + " cards");

            Game.gameState();

            computerAction();
        }

        // Toggle game input controls depending on the outcome
        ButtonSkip.setEnabled(Game.getGameState());
        ButtonDraw.setEnabled(Game.getGameState());

        updateGameWindow((Game.cardsLeft()) + " Cards left");

        Game.resetCardsDrawn();
        Game.gameState();
    }

    /**
     * Set the image contents to display for the player
     *
     * @param card The card to display
     */
    public void setPlayerCardLabel(Cards card)
    {
        playerCardLabel.setIcon(ImageHandler.getCardImage(card));
    }

    /**
     * Set the image contents to display for the opponent
     *
     * @param card The card to display
     */
    public void setComputerCardLabel(Cards card)
    {
        computerCardLabel.setIcon(ImageHandler.getCardImage(card));
    }

    /**
     * Sets the text for statusLabel
     */
    public void setStatusLabel(String text)
    {
        statusLabel.setText(text);
    }

    /**
     * Sets the text for PlayerValue
     */
    public void setPlayerValue()
    {
        PlayerValue.setText(Integer.toString(Game.getPlayerScore()));
    }

    /**
     * Sets the text for ComputerValue
     */
    public void setComputerValue()
    {
        ComputerValue.setText(Integer.toString(Game.getComputerScore()));
    }

    /**
     * Sets the text for recentActions
     *
     * @param string The string to display
     */
    public void setRecentActions(String string)
    {
        recentActions.setText(string);
    }

    /**
     * Sets the text for playerRecentCards
     *
     * @param string The string to display
     */
    public void setPlayerRecentCards(String string)
    {
        playerRecentCards.setText(string);
    }

    /**
     * Sets the text for computerRecentCards
     *
     * @param string The string to display
     */
    public void setComputerRecentCards(String string)
    {
        computerRecentCards.setText(string);
    }

    /**
     * The suit of the recently drawn card
     *
     * @return Returns the suit of the most recent card
     */
    public String recentSuit()
    {
        if (Game.getCard().suit == 1)
            return "Spades";
        if (Game.getCard().suit == 2)
            return "Diamonds";
        if (Game.getCard().suit == 3)
            return "Hearts";
        if (Game.getCard().suit == 4)
            return "Clubs";
        else return "Something unexpected happened.";
    }

    /**
     * The name of the recently drawn card
     *
     * @return Returns the name of the most recent card
     * @throws Exception Throws NPE if there is no previously drawn card on skipped turn
     */
    public String cardName()
    {
        String name = "";

        // Safety measure
        if (Game.getCard() == null)
            return name;

        switch (Game.getCard().value)
        {
            case 1: name =  "Ace of ";
                break;
            case 2: name = "2 of ";
                break;
            case 3: name = "3 of ";
                break;
            case 4: name = "4 of ";
                break;
            case 5: name = "5 of ";
                break;
            case 6: name = "6 of ";
                break;
            case 7: name = "7 of ";
                break;
            case 8: name = "8 of ";
                break;
            case 9: name = "9 of ";
                break;
            case 10: name = "10 of ";
                break;
            case 11: name = "Jack of ";
                break;
            case 12: name = "Queen of ";
                break;
            case 13: name = "King of ";
                break;
            default:
                throw new IllegalStateException("Unexpected value: No previous cards");
        }
        return name + recentSuit();
    }

    /**
     * Get the time of the local machine
     *
     * @return Returns the local time formatted by HH:mm:ss
     */
    public String getTime()
    {
        Date date = new Date();
        return "[" + DATEFORMAT.format(date) + "]";
    }

    /**
     * Sets various display elements such as;
     * updates computers score, players score, deck size
     * enables or disables inputs from player
     *
     * @param text The remaining deck size
     */
    public void updateGameWindow(String text)
    {
        setStatusLabel(text);
        setPlayerValue();
        setComputerValue();
        disableGameControls(Game.getGameState());
    }

    /**
     * Launches a new game window
     */
    public static void newGame()
    {
        gameWindow = new GameWindow();
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setVisible(true);
    }

    /**
     * Terminates the program
     */
    public static void terminate()
    {
        gameWindow.dispose();
        System.exit(0);
    }

    /**
     * Restarts the game to a new session
     */
    public static void restart()
    {
        Game.reset();
        System.out.flush();
        gameWindow.dispose();
        newGame();
    }

    /**
     * Disables various game controls such as
     * Keyboard inputs and JFrame buttons
     * when the game is over
     *
     * This is to prevent potential bugs/exploits by
     * sending inputs in the window between disposing
     * popup messages after the game.
     *
     * @param disable The game state
     *                False if the game is over and inputs need to be restricted
     *                True if the game is in progress
     */
    public void disableGameControls(boolean disable)
    {
        if (!disable)
        {
            ButtonDraw.setEnabled(false);
            ButtonSkip.setEnabled(false);
            ButtonDraw.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
            ButtonSkip.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
            setStatusLabel("Game Finished");
        }
    }
}