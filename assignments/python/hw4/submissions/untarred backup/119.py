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
    goalcount = 0
    if get_list(guess) == get_list(goal_word): #if list == list
        return len(get_list(guess))
    else:
        for item in get_list(guess): #going through every item in guess
            if get_list(goal_word)[goalcount] in get_list(guess):
                common_letters += 1
                #if letter 1 of goal inside guess
            goalcount += 1 
            #start next letter of goal to search in guess while loop
        return common_letters




