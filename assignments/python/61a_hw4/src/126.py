def num_common_letters(goal_word, guess):
    goal_word = get_string(goal_word)
    guess = get_string(guess)
    count = 0
    for letter in goal_word:
        if letter in guess:
            count = count + 1
    return count
