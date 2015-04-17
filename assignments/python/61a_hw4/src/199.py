def num_common_letters(goal_word, guess):
    trans_goal, trans_guess = get_list(goal_word), get_list(guess)
    num_common = 0
    for i in trans_goal:
        for j in trans_guess:
            if j == i:
                num_common += 1
                break
    return num_common
