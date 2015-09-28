def num_common_letters(goal_word, guess):
    total = 0
    count = 0
    while count < len(goal_word):
        if goal_word[count] in guess:
            total += 1
        count += 1
    return total
