def num_common_letters(goal_word, guess):
    split_goal = get_list(goal_word)
    split_guess = get_list(guess)
    in_common = 0
    for item in split_goal:
        if item in split_guess:
            in_common += 1
    return in_common
