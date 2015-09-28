def num_common_letters(goal_word, guess):
    in_common = 0
    for i in get_list(goal_word):
        if i in get_list(guess):
            in_common += 1
    return in_common
