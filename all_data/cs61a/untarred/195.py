def num_common_letters(goal_word, guess):
    return len(list(set(goal_word) & set(guess)))
