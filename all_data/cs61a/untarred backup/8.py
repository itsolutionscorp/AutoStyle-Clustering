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
    goal_word_lst = get_list(goal_word)


    for i in range(len(goal_word_lst)):

        if i+1 not in range(len(goal_word_lst)):
            pass

        if goal_word_lst[i] in goal_word_lst[i+1:]:
            return "This isn't a superhero movie franchise. No repeats."

    guess_lst_unique = get_list(''.join(set(get_string(guess))) )   
        
    total_same = 0
    for x in range(len(guess_lst_unique)):
        for y in range(len(goal_word_lst)):
            if guess_lst_unique[x] == goal_word_lst[y]:
                total_same +=1

    return total_same



