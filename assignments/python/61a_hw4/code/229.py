def num_common_letters(goal_word, guess):
    lst_guess = get_list(guess)
    lst_goal = get_list(goal_word)
    dup = []
    common_let = 0
    for i in range(len(lst_goal)):
        if (lst_guess[i] in lst_goal) and (lst_guess[i] not in dup):
            common_let += 1
            dup += lst_guess[i]
    return common_let
