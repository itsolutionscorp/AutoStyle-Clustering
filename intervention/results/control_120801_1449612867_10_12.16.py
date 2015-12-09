def num_common_letters(goal_word, guess):
    bank = []
    n = 0
    for c in guess:
        if c in bank:
            n += 0
        elif c in goal_word:
            n += 1
        bank.append(c)
    return n