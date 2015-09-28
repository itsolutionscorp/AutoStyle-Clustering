def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    total = 0
    for each in goal_lst:
        if each in guess:
            total += 1
    return total 
