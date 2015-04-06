def num_common_letters(goal_word, guess):
    return len(set(get_list(goal_word)).intersection(get_list(guess)))
