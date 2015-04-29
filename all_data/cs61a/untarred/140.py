def num_common_letters(goal_word, guess):
    gw, g = get_list(goal_word), get_list(guess)
    total = 0
    for item in gw:
        if item in g:
            total += 1
    return total 
