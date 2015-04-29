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
    non_repeated, result = [], 0
    guess_letters, goal = get_list(guess), get_list(goal_word)
    #this function gets rid of the repeated letters in guess
    for letter in range (0, len(guess_letters)):
        if not guess_letters[letter] in non_repeated:
            non_repeated += guess_letters[letter]
        letter += 1

    #Goes through the list goal to see if each letter in non_repeated is present
    #If so, it adds 1 to result 
    for letter in range(0, len(non_repeated)):
        for l in range(0,len(goal)): 
            if non_repeated[letter] == goal[l]:
                result += 1
            l+= 1
        letter += 1
    return result 



