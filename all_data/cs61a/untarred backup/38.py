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
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    common_letter = 0
    x = 0
    goal_len = len(goal_lst)
    guess_len = len(guess_lst)
    while x < goal_len:
        y = 0
        while y < guess_len:
            if goal_lst[x] == guess_lst[y]:
                common_letter += 1
                y = guess_len + 1
            else:
                y += 1
        x += 1
    return common_letter



