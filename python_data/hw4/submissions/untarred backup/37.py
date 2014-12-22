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
    goal_word_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    correct_letters = []
    for i in range(len(goal_word_lst)):
        correct_letters += [0]
        for j in range(len(guess_lst)):
            if (goal_word_lst[i] == guess_lst[j]):
                correct_letters[i] = 1
    sum_correct = 0
    for i in range(len(correct_letters)):
        if correct_letters[i] == 1:
            sum_correct += 1
    return sum_correct




