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
    total = 0
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    singles = []

    if len(goal_lst) == 0:
        return 0
    else:
        for elem in guess_lst:
            if elem not in singles:
                singles += [elem]
        for elem in singles:
            if elem is goal_lst[0]:
                total += 1
    return total + num_common_letters(make_word_from_list(goal_lst[1:]), guess)




