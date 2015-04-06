def num_common_letters(goal_word, guess):
    num_cl = 0
    for goal_letter in get_list(goal_word):
        if goal_letter in get_list(guess): 
            num_cl += 1 
    return num_cl
