def num_common_letters(goal_word, guess):
    return len([1 for c in set(get_list(goal_word)) if c in get_list(guess)])
