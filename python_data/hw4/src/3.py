def num_common_letters(goal_word, guess):
    same_count = 0
    for x in get_list(goal_word):
        if x in get_list(guess):
            same_count += 1
    return same_count
