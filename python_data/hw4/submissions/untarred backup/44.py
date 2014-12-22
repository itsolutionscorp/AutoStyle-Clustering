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
    #Get Length of word
    length=len(get_string(goal_word))
    
    #Letters I've Seen
    letters_seen=[]
    for el in letters:
        for i in range(length):
            if el in get_string(guess)[i] and el not in letters_seen:
                letters_seen=letters_seen+[get_string(guess)[i]]
    
    num=0
    for i in range(len(letters_seen)):
        if letters_seen[i] in get_string(goal_word):
            num+=1
        else:
            num=num

    return num

