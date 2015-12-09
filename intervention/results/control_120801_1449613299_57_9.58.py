def num_common_letters(goal_word, guess):
    common_letters = []
    i = 0
    for c in guess:
        if c in goal_word and c not in common_letters:
            i += 1
        common_letters.append(c)
    return i