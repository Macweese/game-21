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

import java.util.Random;

/**
 * Represents an enumeration of cards found within a deck
 * Excludes 'Joker' card
 * Aces have the value 1
 */
public enum Cards
{
    SPADE_ACE       (1, 1),
    SPADE_TWO       (2, 1),
    SPADE_THREE     (3, 1),
    SPADE_FOUR      (4, 1),
    SPADE_FIVE      (5, 1),
    SPADE_SIX       (6, 1),
    SPADE_SEVEN     (7, 1),
    SPADE_EIGHT     (8, 1),
    SPADE_NINE      (9, 1),
    SPADE_TEN       (10, 1),
    SPADE_JACK      (11, 1),
    SPADE_QUEEN     (12, 1),
    SPADE_KING      (13, 1),

    DIAMOND_ACE     (1, 2),
    DIAMOND_TWO     (2, 2),
    DIAMOND_THREE   (3, 2),
    DIAMOND_FOUR    (4, 2),
    DIAMOND_FIVE    (5, 2),
    DIAMOND_SIX     (6, 2),
    DIAMOND_SEVEN   (7, 2),
    DIAMOND_EIGHT   (8, 2),
    DIAMOND_NINE    (9, 2),
    DIAMOND_TEN     (10, 2),
    DIAMOND_JACK    (11, 2),
    DIAMOND_QUEEN   (12, 2),
    DIAMOND_KING    (13, 2),

    HEART_ACE       (1, 3),
    HEART_TWO       (2, 3),
    HEART_THREE     (3, 3),
    HEART_FOUR      (4, 3),
    HEART_FIVE      (5, 3),
    HEART_SIX       (6, 3),
    HEART_SEVEN     (7, 3),
    HEART_EIGHT     (8, 3),
    HEART_NINE      (9, 3),
    HEART_TEN       (10, 3),
    HEART_JACK      (11, 3),
    HEART_QUEEN     (12, 3),
    HEART_KING      (13, 3),

    CLUB_ACE        (1, 4),
    CLUB_TWO        (2, 4),
    CLUB_THREE      (3, 4),
    CLUB_FOUR       (4, 4),
    CLUB_FIVE       (5, 4),
    CLUB_SIX        (6, 4),
    CLUB_SEVEN      (7, 4),
    CLUB_EIGHT      (8, 4),
    CLUB_NINE       (9, 4),
    CLUB_TEN        (10, 4),
    CLUB_JACK       (11, 4),
    CLUB_QUEEN      (12, 4),
    CLUB_KING       (13, 4);

    public final int value;
    public final int suit;

    Cards(int Value, int Suit)
    {
        this.value = Value;
        this.suit = Suit;
    }

    /**
     * Generates a random card from the enumeration
     *
     * @return Returns a random card from the deck
     */
    public static Cards getRandomCard()
    {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
