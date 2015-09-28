def num_common_letters(goal_word, guess):
    common = 0
    str_guess, str_goal = get_string(guess), get_string(goal_word)
    for i in range(len(str_goal)):
        if str_goal[i] in str_guess:
            common += 1
    return common
