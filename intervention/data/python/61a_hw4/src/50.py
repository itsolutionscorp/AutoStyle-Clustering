def num_common_letters(goal_word, guess):
    num = 0
    for i in get_list(goal_word):
        if i in get_list(guess):
            num += 1
    return num
