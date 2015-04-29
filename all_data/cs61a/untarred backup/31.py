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
    common_letters = 0
    list_goal = get_list(goal_word)
    list_guess = get_list(guess)

    guesslist_norepeats = [] #creating yet another list of guess but without repeats
    for l in list_guess:
        if l not in guesslist_norepeats:
            guesslist_norepeats.append(l)

    for character in list_goal:
        if character in guesslist_norepeats:
            common_letters += 1 
    return common_letters

