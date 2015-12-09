def num_common_letters(goal_word, guess):
    used = [letter for letter in goal_word if letter in guess]
    return len(set(used))
