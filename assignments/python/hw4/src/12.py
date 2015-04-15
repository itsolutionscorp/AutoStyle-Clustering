def num_common_letters(goal_word, guess):
    total = 0
    for elem in get_list(goal_word): 
        if elem in get_list(guess):
            total = total + 1   
    return total
