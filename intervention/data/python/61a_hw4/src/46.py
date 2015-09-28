def num_common_letters(goal_word, guess):
    result = 0
    for x in goal_word:
        if x in guess:
            result += 1
    return result
