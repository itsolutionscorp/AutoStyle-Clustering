def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    total = 0
    for letter in goal_lst:
        if letter in guess_lst:
            total += 1
    return total
