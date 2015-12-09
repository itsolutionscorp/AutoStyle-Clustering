def num_common_letters(goal_word, guess):
    return len(set([letter for letter in guess if letter in goal_word]))