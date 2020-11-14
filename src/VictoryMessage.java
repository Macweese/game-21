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

/**
 * The victory message
 */
public class VictoryMessage extends JDialog
{
    private JPanel contentPane;
    private JButton buttonNewGame;
    private JButton buttonQuit;
    private JLabel victoryImage;
    private JLabel victoryMessage;

    public VictoryMessage()
    {
        setContentPane(contentPane);
        setTitle("Victory");
        victoryImage.setIcon(ImageHandler.ICON_WIN);
        victoryMessage.setText("<html>Congratulations, You won!<br>Play again?</html>");
        setModal(true);

        getRootPane().setDefaultButton(buttonNewGame);
        setIconImage(ImageHandler.getWinImage());

        setLocationRelativeTo(contentPane);

        buttonNewGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                onNewGame();
            }
        });

        buttonQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Creates a new game session
     */
    private void onNewGame()
    {
        System.out.println("User opted to play again - restarting");
        dispose();
        GameWindow.restart();
    }

    /**
     * Exits the program
     */
    private void onExit()
    {
        System.out.println("User opted to exit the game (victory)");
        dispose();
        GameWindow.terminate();
    }

    /**
     * Creates the message window
     */
    public static void newWindow()
    {
        VictoryMessage window = new VictoryMessage();
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
