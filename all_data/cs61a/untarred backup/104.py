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
    doubles = 0
    repeat = 0
    common = 0
    user_guess = get_list(guess)
    goal_list = get_list(goal_word)
    user_guess_string = get_string(guess)
    for letter in goal_list:
        for char in user_guess:
            if letter in char:
                doubles +=1
                if doubles > 1:
                    repeat +=1
                    doubles = 0
        doubles = 0
        if letter in user_guess_string:
            common+=1
    return common 



