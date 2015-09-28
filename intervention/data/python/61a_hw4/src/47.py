def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    num = 0
    for i in range(len(goal_list)):
        if goal_list[i] in guess_list: 
                num += 1
    return num
