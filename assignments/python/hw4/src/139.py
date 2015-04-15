def num_common_letters(goal_word, guess):
    if (len(guess) == 1) and (guess[0] in goal_word):
        return 1
    elif len(guess) == 1:
        return 0
    elif guess[0] in get_list(goal_word) and (not guess[0] in guess[1:]):
        return 1 + num_common_letters(goal_word, guess[1:])
    else:
        return num_common_letters(goal_word, guess[1:])
