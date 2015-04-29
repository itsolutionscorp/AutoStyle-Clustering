def num_common_letters(goal_word, guess):
    total = 0
    for letter in goal_word:
        if letter in guess:
            total += 1
    return total
