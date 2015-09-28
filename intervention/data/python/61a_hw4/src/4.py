def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    rest_goal = goal_lst[1:]
    rest_guess = guess_lst[1:]
    num = 0
    for el in goal_lst:
        if el in guess_lst:
            num += 1
    return num
