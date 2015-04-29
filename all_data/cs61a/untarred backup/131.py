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
    >>> num_common_letters('cold','cool')
    3
    """
    "*** YOUR CODE HERE ***"
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    for i in range(len(guess_list)):
        n = i-1
        while n >= 0:
            if guess_list[i] == guess_list[n]:
                guess_list[i] = ""
            n -= 1
    num_letters = 0 
    for i in range(len(guess_list)):
        for j in range(len(goal_list)):
            if guess_list[i] == goal_list[j]:
                num_letters += 1
    return num_letters


