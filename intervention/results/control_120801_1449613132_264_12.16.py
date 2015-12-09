def num_common_letters(goal_word, guess):
    common_letters = []
    n = 0
    for c in guess:
        if c in common_letters:
            n += 0
        elif c in goal_word:
            n += 1
        common_letters.append(c)
    return n
