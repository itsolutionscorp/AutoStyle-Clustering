def num_common_letters(goal_word, guess):
    count = 0
    for y in range(len(goal_word)):
        if goal_word[y] in guess:
            count += 1
    return count
