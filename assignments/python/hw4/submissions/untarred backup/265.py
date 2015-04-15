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

    counter = 0
    s1, s2 = sorted(goal_word[0]), sorted(guess[0])
    n1, n2 = '', ''

    for el in s1: #remove all repeating letters in the goal_word
        if el not in n1:
            n1 += el

    for el in s2: #remove all repeating letters in the guess
        if el not in n2:
            n2 += el

    for i in range(len(n1)):
        for j in range(len(n2)):
            if s1[i] == s2[j]:
                counter += 1

    return counter

