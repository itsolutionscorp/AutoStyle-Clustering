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
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    letters, counter = 0, 0
    letters_lst = []
    #compare every letter in guess to goal_word[0], then goal_word[1]
    # to goal_word[len(goal_word)-1]
    for counter in range(len(goal_lst)): 
        for index in range(len(guess_lst)):
            if guess_lst[index] == goal_lst[counter]:
                if guess_lst[index] not in letters_lst:
                    letters += 1
                    letters_lst = letters_lst + list(guess_lst[index])    
        counter += 1
    return letters


