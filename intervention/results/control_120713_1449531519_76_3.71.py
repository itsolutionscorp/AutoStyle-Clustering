def num_common_letters(goal_word, guess):
    used = [a for a in goal_word if a in guess]
    return len(set(used))
