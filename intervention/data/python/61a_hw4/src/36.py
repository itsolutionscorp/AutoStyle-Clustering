def num_common_letters(goal_word, guess):
    count = 0
    for elem in goal_word[1]:
        if elem in guess[1]:
            count += 1
    return count
