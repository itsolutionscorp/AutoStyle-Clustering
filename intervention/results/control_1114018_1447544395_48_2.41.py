def num_common_letters(goal_word, guess):
    return len([char for char in goal_word if char in guess])