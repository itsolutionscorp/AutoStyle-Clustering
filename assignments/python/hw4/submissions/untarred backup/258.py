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
    count,index_goal,index_guess = 0,0,0
    while index_goal < len(goal_lst):
        index_guess = 0
        while index_guess < len(guess_lst):
            if goal_lst[index_goal] == guess_lst[index_guess]:
                k = 0
                judge = 0
                while k < index_guess:
                    if guess_lst[k] == guess_lst[index_guess]:
                        judge = 1
                    k += 1
                if judge == 0:
                    count += 1
            index_guess += 1
        index_goal += 1
    return count



