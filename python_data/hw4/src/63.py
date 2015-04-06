def num_common_letters(goal_word, guess):
    common_letters = 0
    for letter in get_list(goal_word):
        if letter in get_list(guess):
            common_letters += 1
    return common_letters
