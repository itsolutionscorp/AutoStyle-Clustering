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


    num_common = 0
    counter_goal = 0
    counter_guess = 0
    counter = 0
    counter2 = 0
    switch = 0
    guess_list = get_list(guess)
    nodupe_list = []
    guess_length = len(guess_list)
    goal_list = get_list(goal_word)
    goal_length = len(get_list(goal_word))
    while counter < guess_length:
        counter2 = counter+1
        while counter2 < guess_length:
            if guess_list[counter] == guess_list[counter2]:
                switch+=1
            counter2+= 1
        if switch > 0:
            switch = 0
        else:
            nodupe_list = nodupe_list + [guess_list[counter]]
            switch = 0
        counter+= 1
    nodupe_guess = make_word_from_list(nodupe_list)
    while counter_goal < goal_length:
        while counter_guess < len(nodupe_list):
            if goal_list[counter_goal] == nodupe_list[counter_guess]:
                num_common+= 1
            counter_guess+= 1
        counter_guess = 0
        counter_goal += 1
    return num_common
    

