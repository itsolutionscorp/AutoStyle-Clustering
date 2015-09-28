def num_common_letters(goal_word, guess):
    common_letter = 0
    for letter in get_list(goal_word):
        if letter in get_list(guess):
            common_letter += 1
    return common_letter
