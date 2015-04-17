def num_common_letters(goal_word, guess):
    """Returns the number of letters in goal_word that are also in guess.
    As per the rules of the game, goal_word cannot have any repeated
    letters, but guess is allowed to have repeated letters.
    goal_word and guess are assumed to be of the same length.
    goal_word and guess are both instances of the word ADT.

    >>> mwfs, mwfl = make_word_from_string, make_word_from_list
    >>> num_common_letters(mwfs('steal'), mwfs('least'))
    5
    >>> num_common_letters(mwfs('steal'), mwfl(['s', 't', 'e', 'e', 'l']))
    4
    >>> num_common_letters(mwfl(['s', 't', 'e', 'a', 'l']), mwfs('thief'))
    2
    >>> num_common_letters(mwfl(['c', 'a', 'r']), mwfl(['p', 'e', 't']))
    0
    """
    "*** YOUR CODE HERE ***"
    g_w, g = get_list(goal_word), list(set(get_list(guess)))
    l_g = len(g)
    l_gw = len(goal_word)
    s_l = 0                         #same letters
    while l_g > 0:
        n = 0
        while n <= l_gw - 1:
            if g[l_g -1] == g_w[n]:
                s_l += 1
                n += 1
            else:
                n += 1 
        l_g -= 1
    return s_l


