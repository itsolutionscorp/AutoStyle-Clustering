def num_common_letters(goal_word, guess):
    return len([i for i in get_list(goal_word) if i in get_list(guess)])
