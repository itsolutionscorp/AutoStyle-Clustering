def num_common_letters(goal_word, guess):
    x = 0
    common_letters = 0
    for item in get_list(goal_word):
        if item in get_list(guess):
            common_letters += 1
    return common_letters
