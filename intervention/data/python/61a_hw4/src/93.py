def num_common_letters(goal_word, guess):
    guess_lst = get_list(guess)
    goal_lst = get_list(goal_word)
    common_count = 0
    for i in range(len(goal_lst)):
        if goal_lst[i] in guess_lst:
            common_count+=1
        else:
            common_count+=0
    return common_count
