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
    seen_letters = []
    counter = 0
    goal_word_list  = get_list(goal_word)
    guess_word_list = get_list(guess)

    for letter in guess_word_list:
        if letter in goal_word_list and letter not in seen_letters:
            counter = counter + 1
            seen_letters = [letter] + seen_letters
        elif letter in goal_word_list and letter in seen_letters:
            pass
        else:
            seen_letters = [letter] + seen_letters

    return counter
    



