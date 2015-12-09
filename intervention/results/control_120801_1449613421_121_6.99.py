def num_common_letters(goal_word, guess):
    common_letters = []
    for c in guess:
        if c in goal_word and c not in common_letters:
            common_letters.append(c)
    return len(common_letters)