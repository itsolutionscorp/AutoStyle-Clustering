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
    gword=get_list(make_word_from_string(goal_word)) 
    guesser=get_list(make_word_from_string(guess))
    #get_list makes a python list of individual letters
    return checker(gword, guesser)

def checker(lst1, lst2):
        if lst1[1:]==[]:
            if lst1[0] in lst2:
                return 1
            return 0
        if lst1[0] not in lst2:
            return checker(lst1[1:],lst2)
        else:
            return 1+checker(lst1[1:],lst2)