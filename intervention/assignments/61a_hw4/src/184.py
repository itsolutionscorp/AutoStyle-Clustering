def num_common_letters(goal_word, guess):
    count = 0
    goal_word, guess = get_list(goal_word), get_list(guess)
    length_guess = len(guess)
    for i in range(length_guess):
        if goal_word[i] in guess:
            count += 1
    return count
