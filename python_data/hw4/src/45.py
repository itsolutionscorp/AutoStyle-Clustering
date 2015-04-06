def num_common_letters(goal_word, guess):
    total = 0
    for i in goal_word:
        for k in guess:
            if i == k:
                total += 1
                break 
    return total
