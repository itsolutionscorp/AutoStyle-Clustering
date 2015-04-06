def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    common = 0
    for i in range (0, len(goal_list)):
        if goal_list[i] in guess_list:
            common += 1
    return common
