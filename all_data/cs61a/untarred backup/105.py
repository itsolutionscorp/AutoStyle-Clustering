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
    goalword_list = get_list(goal_word)
    guess_list = get_list(guess)
    result = 0
    count_repeats = 0 
    guessed_letters = [] 
    for i in range(len(guess_list)):
        if guess_list[i-1] == guess_list[i]:
            repeated = [guess_list[i]] 
            guessed_letters = guessed_letters + repeated 
            count_repeats += 1
    for letter in goalword_list:
        for letter_1 in guess_list:
            if letter == letter_1:
                result += 1 
    return result - count_repeats 

