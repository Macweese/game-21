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
import java.awt.*;
import java.io.File;

/**
 * Handles various images and icons
 */
public class ImageHandler extends ImageIcon
{
    public static final String ICONS_PATH = System.getProperty("user.dir") + File.separator + "util" + File.separator + "resources" + File.separator + "general";
    public static final String CARD_IMAGES_PATH = System.getProperty("user.dir") + File.separator + "util" + File.separator + "resources" + File.separator + "cards";
    public static final ImageIcon ICON_GAME = new ImageIcon(ICONS_PATH + File.separator + "21.png");
    public static final ImageIcon ICON_EXIT = new ImageIcon(ICONS_PATH + File.separator + "exit.png");
    public static final ImageIcon ICON_LOSE = new ImageIcon(ICONS_PATH + File.separator + "lose.png");
    public static final ImageIcon ICON_WIN = new ImageIcon(ICONS_PATH + File.separator + "win.png");
    public static ImageIcon CARD_IMAGE = new ImageIcon();

    /**
     * Gets the default program icon
     *
     * @return Returns an image
     */
    public static Image getGameImage()
    {
        return ICON_GAME.getImage();
    }

    /**
     * Gets the exit image
     *
     * @return Returns an image
     */
    public static Image getExitImage()
    {
        return ICON_EXIT.getImage();
    }

    /**
     * Gets the defeat image
     *
     * @return Returns an image
     */
    public static Image getLoseImage()
    {
        return ICON_LOSE.getImage();
    }

    /**
     * Gets the victory image
     *
     * @return Returns an image
     */
    public static Image getWinImage()
    {
        return ICON_WIN.getImage();
    }

    /**
     * Gets the image for the corresponding card
     *
     * @param cards The card to depict
     * @return Returns an ImageIcon of the passed card
     */
    public static ImageIcon getCardImage(Cards cards)
    {
        String cardfile = "";

        switch (cards.suit)
        {
            case 1: cardfile = "SPADE_";
                break;
            case 2: cardfile = "DIAMOND_";
                break;
            case 3: cardfile = "HEART_";
                break;
            case 4: cardfile = "CLUB_";
                break;
            default: cardfile = "HIDDEN";
                break;
        }

        switch (cards.value)
        {
            case 1: cardfile = cardfile + "A";
                break;
            case 2: cardfile = cardfile + "2";
                break;
            case 3: cardfile = cardfile + "3";
                break;
            case 4: cardfile = cardfile + "4";
                break;
            case 5: cardfile = cardfile + "5";
                break;
            case 6: cardfile = cardfile + "6";
                break;
            case 7: cardfile = cardfile + "7";
                break;
            case 8: cardfile = cardfile + "8";
                break;
            case 9: cardfile = cardfile + "9";
                break;
            case 10: cardfile = cardfile + "10";
                break;
            case 11: cardfile = cardfile + "J";
                break;
            case 12: cardfile = cardfile + "Q";
                break;
            case 13: cardfile = cardfile + "K";
                break;
            default: cardfile = cardfile;
                break;
        }

        CARD_IMAGE = new ImageIcon(CARD_IMAGES_PATH + File.separator + cardfile + ".png");
        return new ImageIcon(CARD_IMAGES_PATH + File.separator + cardfile + ".png");
    }

    /**
     * Gets the default card image
     *
     * @return Returns a default ImageIcon
     */
    public static ImageIcon getCardImage()
    {
        return new ImageIcon(CARD_IMAGES_PATH + File.separator + "HIDDEN.png");
    }
}
