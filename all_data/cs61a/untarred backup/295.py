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
    def remover(lst, index):
        return lst[:index] + lst[index + 1:]
    goalword = get_list(goal_word)
    guesslist = get_list(guess)
    for goalindex in range(len(goalword)):
        for index in range(len(guesslist)):
            if guesslist[index] == goalword[goalindex]:
                goal_word = remover(goalword, goalindex)
                guess = remover(guesslist, index)
                return 1 + num_common_letters(goal_word, guess)
    return 0


