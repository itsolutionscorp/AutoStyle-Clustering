def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    in_common = 0
    for element in goal_lst:
        if element in guess_lst:
            in_common += 1
    return in_common
    
