def num_common_letters(goal_word, guess):
    n = 0
    total = 0
    while n < len(goal_word):
        if goal_word[n] in guess:
            total += 1
        n += 1
    return total
