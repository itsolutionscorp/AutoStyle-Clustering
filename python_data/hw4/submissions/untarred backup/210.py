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
    if get_list(goal_word) == []:
        return 0
    for i in range(len(get_list(guess))):
        if get_list(guess)[i] == get_list(goal_word)[0]:
            return 1 + num_common_letters(make_word_from_list(get_list(goal_word)[1:]), make_word_from_list(get_list(guess)))
    return num_common_letters(make_word_from_list(get_list(goal_word)[1:]), make_word_from_list(get_list(guess)))

