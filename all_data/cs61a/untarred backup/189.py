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
    x = goal_word
    y = guess
    if get_string(x) == '' or get_string(y) == '':
        return 0
    new_string = ''
    previous = 0
    for letter1 in get_string(x):
        for letter2 in get_string(y):
            if letter1 == letter2:
                if previous == letter2:
                    new_string = new_string
                    previous = letter1
                else:
                    new_string = new_string + letter2
                    previous = letter2
    return len(new_string)




