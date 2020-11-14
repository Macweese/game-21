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

public class ExitConfirmation extends JDialog
{
    private JPanel contentPane;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel imagePanel;
    private JButton buttonRestart;
    private JButton buttonExit;
    private JButton buttonResume;
    private JLabel leftPanel;
    private JLabel imageLabel;

    public ExitConfirmation() throws IOException
    {
        setContentPane(contentPane);
        setTitle("Confirm Exit");
        imageLabel.setIcon(ImageHandler.ICON_EXIT);
        setModal(true);
        getRootPane().setDefaultButton(buttonResume);
        setIconImage(ImageHandler.getExitImage());

        setLocationRelativeTo(contentPane);

        buttonRestart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onRestart();
            }
        });

        buttonResume.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        buttonExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onExit();
            }
        });

        // resume - do nothing when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        // resume - do nothing on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // call onExit() on Enter
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onRestart()
    {
        System.out.println("User started new session.");
        dispose();
        GameWindow.restart();
        dispose();
    }

    private void onExit()
    {
        System.out.println("User prompted exit - Shutting down.");
        dispose();
        GameWindow.terminate();
    }

    public static void newWindow() throws IOException
    {
        ExitConfirmation window = new ExitConfirmation();
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }

}
