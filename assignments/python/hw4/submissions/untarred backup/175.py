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
    letters_checked=[]  #this is unecessary if goal_word alwys gets input
                        #before the guess, but i'll leave it because
                        #if i mess it up, it'll still check properly
    letters_in_common=0
    goal_string=get_string(goal_word)
    guess=get_string(guess)
    for letter in goal_string:
        if letter in guess and not letter in letters_checked:
            letters_in_common+=1
        letters_checked+=[letter]#same comment for letters_checked
    return letters_in_common

