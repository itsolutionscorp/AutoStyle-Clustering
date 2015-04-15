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
    list_goal_word = get_list(goal_word)
    list_guess = get_list(guess)
    string_goal_word = get_string(goal_word)
    string_guess = get_string(guess)
    total = 0
    for i in list_goal_word:
        for r in list_guess:
            if list_goal_word[i] == list_guess[r]:
                total += 1
            return total

    for i in string_goal_word:
        for r in string_guess:
            if string_goal_word[i] == string_guess[r]:
                total += 1
            return total

