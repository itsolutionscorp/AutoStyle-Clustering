def num_common_letters(goal_word, guess):
    goal_lst, guess_str = get_list(goal_word), get_string(guess)
    total = 0
    no_rep_guess = [x for x in letters if x in guess_str] 
    for elem in no_rep_guess:
        if elem in goal_lst: 
                total += 1
    return total
