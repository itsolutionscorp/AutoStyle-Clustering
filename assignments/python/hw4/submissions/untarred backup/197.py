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
    goal_length = len(goal_word)
    guess_length = len(guess)
    temp_goal = list(goal_word)
    temp_guess = list(guess)
    indexer_guess = 0
    number_letters = 0
    while indexer_guess < guess_length:
        indexer_goal = 0
        while indexer_goal < goal_length and temp_goal[indexer_goal] != temp_guess[indexer_guess]:
            indexer_goal += 1
        if indexer_goal == goal_length:
            indexer_guess += 1
        else:
            temp_goal.remove(temp_goal[indexer_goal])
            temp_guess.remove(temp_guess[indexer_guess])
            number_letters = number_letters + 1
            goal_length -= 1
            guess_length -= 1            
    return number_letters



