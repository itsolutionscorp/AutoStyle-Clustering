def num_common_letters(goal_word, guess):
    return len(set([char for char in guess if char in goal_word]))