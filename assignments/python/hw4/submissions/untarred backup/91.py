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
    list1 = get_list(goal_word)
    list2 = get_list(guess)
    j, y= 0, 0
    count = 0 
    while j < len(goal_word):
        i = 0 
        while i < len(goal_word):
            if list1[i] == list2[y]:
                count +=1 
            i +=1 
        y +=1
        j +=1 
    return count 

