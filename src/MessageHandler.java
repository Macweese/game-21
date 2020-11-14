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

/*
 * Messages to display
 */
public class MessageHandler
{
    ExitConfirmation exitConfirmation;

    {
        try {
            exitConfirmation = new ExitConfirmation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Confirmation message on attempting to exit
     *
     * @throws IOException Exception
     */
    public void exitMessage() throws IOException
    {
        ExitConfirmation.newWindow();
    }

    /**
     * Message to display on defeat
     *
     * @param defeatType The type of defeat that occurred
     *                   1 = defeat because ran out of cards in the deck
     *                   2 = defeat because players score went above 21 or computer score 21
     *                   3 = defeat because both had equal score, computer wins by default
     */
    public static void defeatMessage(int defeatType)
    {
        DefeatMessage.newWindow(defeatType);
    }

    /**
     * Message to display on victory
     */
    public static void victoryMessage()
    {
        VictoryMessage.newWindow();
    }
}
