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

    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    # Turns the guess and goal_word into lists so we can easily search
    # elements by indices in the list.

    def letter_checker(guess_list, goal_list, guess_index, goal_index):
        
        def repeat_checker(guess_list, guess_index, repeat_index):
            # THe idea is, for each letter, to compare with all other
            # previously-checked letters, and if there is an overlap,
            # then stop checking the current letter because you have
            # already decided if it counted as a 0 or a 1.
            if repeat_index < 0:
                return False
                # Base case when you have finished checking all of the 
                # previously-checked letter_checker'd letters.
            elif guess_list[guess_index] == guess_list[repeat_index]:
                return True
                # Identifies if you have an overlapped letter.
            else:
                return repeat_checker(guess_list, guess_index, repeat_index - 1)
                # Recursively checks the next most recently checked letter
                # to see if it matches the current guess_index letter.

        if (guess_index == len(guess_list)):
            return 0
            # When all of the elements in guess_list have been checked,
            # end the recursive call.

        elif repeat_checker(guess_list, guess_index, guess_index - 1) == True:
            return 0 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter is identified as having been checked before,
            # skip this letter and letter_checker the next element
            # in the guess_list.

        elif (goal_index == len(goal_list)):
            return 0 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter has been compared with every element in goal_list
            # without any matches, then you know that that guess_list
            # element is not found in goal_list, so mvoe on to checking
            # the next element in guess_list.

        elif guess_list[guess_index] == goal_list[goal_index]:
            return 1 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter in guess_list matches a letter in goal_list,
            # you know already that the letter is found in both goal and
            # guess words, so you can mark this as a match and move on
            # to checking the next element in guess_list.

        else:
            return letter_checker(guess_list, goal_list, guess_index, goal_index + 1)
            # Recursively compares the current letter in guess_list
            # with all of the letters in goal_list.

    return letter_checker(guess_list, goal_list, 0, 0)
    # Start the recursive call by checking the first letter in 
    # guess_list with the first letter in goal_list.


