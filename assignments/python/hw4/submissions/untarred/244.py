def num_common_letters(goal_word, guess):
    list_goal_word = get_list(goal_word)
    list_guess = get_list(guess)
    total = 0
    for i in list_goal_word:
        if i in list_guess:
            total += 1
            list_guess.remove(i)
    return total
