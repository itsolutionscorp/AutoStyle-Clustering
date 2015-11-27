def num_common_letters(goal_word, guess):
    count = 0

    goal_word = set(goal_word)
    guess = set(guess)

    for char in goal_word:
        if char in guess:
            count += 1
    return count
