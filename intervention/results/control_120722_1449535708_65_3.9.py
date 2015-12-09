def num_common_letters(goal_word, guess):
    used = [x for x in goal_word if x in guess]
    return len(list(set(used)))