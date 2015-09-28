def num_common_letters(goal_word, guess):
    new_goal_word = get_list(goal_word)
    new_guess = get_list(guess)
    counter = 0
    for letters in new_goal_word:
        if letters in new_guess:
            counter += 1
    return counter
