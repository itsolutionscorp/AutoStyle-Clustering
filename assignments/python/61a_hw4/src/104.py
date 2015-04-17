def num_common_letters(goal_word, guess):
    count = 0
    for item in get_list(goal_word):
        if item in get_list(guess):
            count += 1
    return count
