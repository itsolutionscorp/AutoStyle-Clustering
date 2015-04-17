def num_common_letters(goal_word, guess):
    counter = 0
    for n in goal_word:
        for m in guess:
            if n == m:
                counter += 1
                break
    return counter
