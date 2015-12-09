def num_common_letters(goal_word, guess):
    return len(list(set([letter for letter in goal_word if letter in guess])))