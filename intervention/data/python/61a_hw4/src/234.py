def num_common_letters(goal_word, guess):
    c = 0 
    for i in get_list(goal_word):
        k = False
        for j in get_list(guess):
            if i == j and k == False:
                k = True
                c += 1
    return c
    
