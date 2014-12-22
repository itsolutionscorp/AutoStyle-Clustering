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
    lst_goal_word= get_list(goal_word)
    lst_guess=get_list(guess)
    string_goal_word=get_string(goal_word)
    string_guess= get_string(guess)
    total=0
    for letter in lst_goal_word:
        if len(lst_goal_word)=len(lst_guess):
            total+=num_common_letters()
    return total

    total=0 
    for letter in string_goal_word:
        if len(string_goal_word)=len(string_guess):
            total+=num_common_letters()
    return total 

