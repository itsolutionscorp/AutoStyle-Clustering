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

    goal_string = get_string(goal_word)
    guess_string = get_string(guess)
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)

    goal_scores = [None]*26
    for k in range(26):
        goal_scores[k] = 0
    for i in range(len(goal_list)):
        for j in range(26):
            if letters[j] == goal_list[i]:
                goal_scores[j] = goal_scores[j] + 1

    guess_scores = [None]*26
    for k in range(26):
        guess_scores[k] = 0
    for i in range(len(guess_list)):
        for j in range(26):
            if letters[j] == guess_list[i]:
                guess_scores[j] = guess_scores[j] + 1
                
    shared_letts = 0
    for i in range(26):
        if min(goal_scores[i],guess_scores[i]) != 0:
            shared_letts += 1
    return shared_letts

        

