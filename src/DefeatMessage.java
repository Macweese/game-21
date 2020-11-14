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
 * The defeat message
 */
public class DefeatMessage extends JDialog
{
    private JPanel contentPane;
    private JButton buttonNewGame;
    private JButton buttonExit;
    private JLabel defeatImage;
    private JLabel defeatMessage;

    private static String loseText = "";

    public DefeatMessage()
    {
        setContentPane(contentPane);
        setTitle("Defeat");
        defeatImage.setIcon(ImageHandler.ICON_LOSE);
        defeatMessage.setText("<html>You lost" + loseText + "<br>Play again?</html>");
        setModal(true);

        getRootPane().setDefaultButton(buttonNewGame);
        setIconImage(ImageHandler.getLoseImage());

        setLocationRelativeTo(contentPane);

        buttonNewGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onNewGame();
            }
        });

        buttonExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
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
            public void actionPerformed(ActionEvent e)
            {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Creates a new game session
     */
    private void onNewGame()
    {
        // add your code here
        System.out.println("User opted to play again - restarting");
        dispose();
        GameWindow.restart();
    }

    /**
     * Exits the program
     */
    private void onExit()
    {
        // add your code here if necessary
        System.out.println("User opted to exit the game (game over)");
        dispose();
        GameWindow.terminate();
    }

    /**
     * Creates the message window
     *
     * @param defeatType The lose condition
     */
    public static void newWindow(int defeatType)
    {
        if (defeatType == 1)
            loseText = " - No more cards left";

        DefeatMessage window = new DefeatMessage();
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
