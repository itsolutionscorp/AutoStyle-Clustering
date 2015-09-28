def num_common_letters(goal_word, guess):
    counter = 0
    for i in get_list(goal_word):
        if i in get_list(guess):
            counter += 1
    return counter
