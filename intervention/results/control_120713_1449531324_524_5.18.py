def num_common_letters(goal_word, guess):
    used = [letter for letter in goal_word if letter in guess]
    used = set(used)
    return len(used)